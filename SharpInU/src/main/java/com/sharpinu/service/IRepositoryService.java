package com.sharpinu.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Mark
 *
 */
public interface IRepositoryService extends IBaseService {
	File saveUploadFileToRepositoty(MultipartFile uploadFile);
}
