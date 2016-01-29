package com.sharpinu.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.sharpinu.util.FileUtil;

/**
 * 
 * @author Mark
 *
 */
@Service
public class RepositoryService implements IRepositoryService {
	public File saveUploadFileToRepositoty(MultipartFile uploadFile) {
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream buffStream = null;
		try {
			String fileName = uploadFile.getOriginalFilename();
			byte[] bytes = uploadFile.getBytes();
			File uniqueFolder = FileUtil.getUUIDFolder();
			File uniqueFile = FileUtil.getIndexedFileIfExisted(uniqueFolder.getAbsolutePath(), fileName);
			fileOutputStream = new FileOutputStream(uniqueFile);
			buffStream = new BufferedOutputStream(fileOutputStream);
			buffStream.write(bytes);
			buffStream.close();
			return uniqueFile;
		} catch (Exception e) {
			throw new RuntimeException("Failed to save upload file to repository", e);
		} finally {
			IOUtils.closeQuietly(fileOutputStream);
			IOUtils.closeQuietly(buffStream);
		}
	}
}
