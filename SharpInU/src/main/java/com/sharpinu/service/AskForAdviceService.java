package com.sharpinu.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sharpinu.email.IMailSender;
import com.sharpinu.persist.domain.Career;
import com.sharpinu.persist.domain.Company;
import com.sharpinu.persist.domain.ContactUs;
import com.sharpinu.persist.domain.Resume;
import com.sharpinu.persist.repositories.CareerRepo;
import com.sharpinu.persist.repositories.CompanyRepo;
import com.sharpinu.persist.repositories.ContactUsRepo;
import com.sharpinu.persist.repositories.ResumeRepo;
import com.sharpinu.processor.email.MailProcessor;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.form.CareerForm;
import com.sharpinu.web.form.CompanyForm;
import com.sharpinu.web.form.ContactUsForm;
import com.sharpinu.web.form.ResumeForm;

/**
 *
 * @author Mark
 *
 */
@Service
public class AskForAdviceService extends BaseService implements IAskForAdviceService {

	@Autowired
	ResumeRepo resumeRepo;

	@Autowired
	ContactUsRepo contactUsRepo;

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	CareerRepo careerRepo;

	@Autowired
	IRepositoryService repositoryService;

	@Autowired
	@Qualifier("mailSender")
	IMailSender sender;

	public String saveResumeForm(ResumeForm resumeForm) {
		try {
			Resume resume = new Resume(resumeForm);

			MultipartFile cvAttachmentUpload = resumeForm.getCvAttachment();

			if (cvAttachmentUpload != null && !cvAttachmentUpload.getOriginalFilename().isEmpty()) {
				File cvFile = repositoryService.saveUploadFileToRepositoty(cvAttachmentUpload);
				resume.setCv(cvFile.getAbsolutePath());
			} else {
				resume.setCv("");
			}

			MultipartFile uploadPhoto = resumeForm.getUploadPhoto();
			if (uploadPhoto != null) {
				File uploadPhotoFile = repositoryService.saveUploadFileToRepositoty(uploadPhoto);
				resume.setImage(uploadPhotoFile.getAbsolutePath());
			}

			resumeRepo.save(resume);
			resumeForm.setResumeId(resume.getResumeId());
			sendMailByResume(resume);
			sendMailByResumeToUser(resume);
		} catch (Exception e) {
			log.error("Failed to save Resume Form", e);
			throw new RuntimeException("Failed to save Resume Form", e);
		}

		return null;
	}

	public int saveUploadCv(MultipartFile fileAttachment, Resume resume) throws Exception {
		try {
			if (fileAttachment != null && !fileAttachment.getOriginalFilename().isEmpty()) {
				File cvFile = repositoryService.saveUploadFileToRepositoty(fileAttachment);
				resume.setCv(cvFile.getAbsolutePath());
				resumeRepo.save(resume);
				return resume.getResumeId();
			}
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
		return -1;
	}

	private void sendMailByResume(Resume resume) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.NEW_RESUME, resume, sender);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of resume[resumeId= %s]", resume.getResumeId()), e);
		}
	}

	private void sendMailByResumeToUser(Resume resume) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.NEW_RESUME_USER, resume, sender);
			mailProcessor.setCurrentUser(SecurityUtil.getCurrentUser());
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of resume[resumeId= %s] to user", resume.getResumeId()), e);
		}
	}

	public String saveContactUsForm(ContactUsForm contactUsForm) {
		try {
			ContactUs contactUs = new ContactUs(contactUsForm);
			contactUsRepo.save(contactUs);
			contactUsForm.setContactUsId(contactUs.getContactId());
			sendMailByContactUs(contactUsForm);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save Resume Form", e);
		}

		return null;
	}

	private void sendMailByContactUs(ContactUsForm contactUsForm) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.CONTACT_US, contactUsForm, sender);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of ContactUs[contactUsId= %s]", contactUsForm.getContactUsId()), e);
		}
	}

	public String saveCompanyForm(CompanyForm companyForm) {
		try {
			Company company = new Company(companyForm);
			MultipartFile uploadAdditionalFile = companyForm.getAdditionInfoFile();
			if (uploadAdditionalFile != null && !uploadAdditionalFile.getOriginalFilename().isEmpty()) {
				File additionalFile = repositoryService.saveUploadFileToRepositoty(uploadAdditionalFile);
				company.setAdditionalInfo(additionalFile.getAbsolutePath());
			} else {
				company.setAdditionalInfo("");
			}

			companyRepo.save(company);
			companyForm.setCompanyId(company.getCompanyId());

			sendMailByCompany(company);
			sendMailByCompanyToUser(company);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save Company Form", e);
		}

		return null;
	}

	private void sendMailByCompany(Company company) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.NEW_COMPANY, company, sender);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Company[companyId= %s]", company.getCompanyId()), e);
		}
	}

	private void sendMailByCompanyToUser(Company company) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.NEW_COMPANY_USER, company, sender);
			mailProcessor.setCurrentUser(SecurityUtil.getCurrentUser());
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Company[companyId= %s] to user", company.getCompanyId()), e);
		}
	}

	public String saveCareerForm(CareerForm careerForm) {
		try {
			Career career = new Career(careerForm);
			MultipartFile uploadAdditionalFile = careerForm.getAdditionInfoFile();
			if (uploadAdditionalFile != null && !uploadAdditionalFile.getOriginalFilename().isEmpty()) {
				File additionalFile = repositoryService.saveUploadFileToRepositoty(uploadAdditionalFile);
				career.setAdditionalInfo(additionalFile.getAbsolutePath());
			} else {
				career.setAdditionalInfo("");
			}
			careerRepo.save(career);
			careerForm.setCareerId(career.getCareerId());

			sendMailbyCareer(career);
			sendMailbyCareerToUser(career);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save Career Form", e);
		}

		return null;
	}

	private void sendMailbyCareer(Career career) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.NEW_CAREER, career, sender);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Career[careerId= %s]", career.getCareerId()), e);
		}
	}

	private void sendMailbyCareerToUser(Career career) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.NEW_CAREER_USER, career, sender);
			mailProcessor.setCurrentUser(SecurityUtil.getCurrentUser());
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Career[careerId= %s] to user", career.getCareerId()), e);
		}
	}
}
