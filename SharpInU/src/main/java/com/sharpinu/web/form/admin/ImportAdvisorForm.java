package com.sharpinu.web.form.admin;

import org.springframework.web.multipart.MultipartFile;

public class ImportAdvisorForm {

	private MultipartFile csvFile;

	public MultipartFile getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(MultipartFile csvFile) {
		this.csvFile = csvFile;
	}
}
