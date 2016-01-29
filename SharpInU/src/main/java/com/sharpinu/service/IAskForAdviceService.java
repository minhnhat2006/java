package com.sharpinu.service;

import com.sharpinu.web.form.CareerForm;
import com.sharpinu.web.form.ContactUsForm;
import com.sharpinu.web.form.CompanyForm;
import com.sharpinu.web.form.ResumeForm;
import org.springframework.web.multipart.MultipartFile;
import com.sharpinu.persist.domain.Resume;

/**
 * 
 * @author Mark
 *
 */
public interface IAskForAdviceService extends IBaseService {

	String saveResumeForm(ResumeForm resumeForm);

	String saveContactUsForm(ContactUsForm contactUsForm);

	String saveCompanyForm(CompanyForm companyForm);

	String saveCareerForm(CareerForm careerForm);

	int saveUploadCv(MultipartFile fileAttachment,Resume resume) throws Exception;
}
