package com.sharpinu.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sharpinu.web.form.FileMetaForm;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

	LinkedList<FileMetaForm> files = new LinkedList<FileMetaForm>();
	FileMetaForm fileMeta = null;

	/*
	 * Receives files
	 * 
	 * @param request : MultipartHttpServletRequest auto passed
	 * 
	 * @param response : HttpServletResponse auto passed
	 * 
	 * @return LinkedList<FileMetaForm> as json format
	 */
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMetaForm> uploadImage(MultipartHttpServletRequest request, HttpServletResponse response) {

		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// 2.3 create new fileMeta
			String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			fileMeta = new FileMetaForm();
			fileMeta.setFileName(request.getContextPath() + "/upload/" + timestamp + "_" + mpf.getOriginalFilename() + "/get.in");
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {
				fileMeta.setBytes(mpf.getBytes());

				// copy file to local disk (make sure the path
				// "e.g. D:/temp/files" exists)
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(System.getProperty("java.io.tmpdir") + timestamp + "_" + mpf.getOriginalFilename()));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2.4 add to files
			files.add(fileMeta);
		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files;
	}

	/*
	 * Get file as an attachment
	 * 
	 * @param response : passed by the server
	 * 
	 * @param value : value from the URL
	 * 
	 * @return void
	 */
	@RequestMapping(value = "/{value}/get", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable String value) {
		String path = System.getProperty("java.io.tmpdir") + value;
		try {
			File file = new File(path);
			response.setContentType("image/png");
			response.setHeader("Content-disposition", "attachment; filename=\"" + value + "\"");
			response.setContentLength((int) file.length());

			ServletOutputStream out = response.getOutputStream();
			FileInputStream fis = new FileInputStream(file);
			int i = 0;

			while ((i = fis.read()) != -1) {
				out.write(i);
			}

			fis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
