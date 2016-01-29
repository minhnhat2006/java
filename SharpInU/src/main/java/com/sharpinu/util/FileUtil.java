package com.sharpinu.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sharpinu.config.ConfigManager;

public class FileUtil {
	public static final Logger log = Logger.getLogger(FileUtil.class);
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static int MAX_INDEX = 1000;
	
	public static File getCurrentDateFolderInRepository() {
		String currentDate = DateUtil.currentDateToString(DateUtil.FOLDER_DATE_FORMAT);
		File repository = ConfigManager.getInstance().getRepositoryFolder();
		String dateFolderPath = repository.getAbsolutePath() + File.separator + currentDate;
		File dateFolder = createFolders(dateFolderPath, true);
		return dateFolder;
	}
	
	public static File getUUIDFolder() {
		File dateFolder = getCurrentDateFolderInRepository();
		String uniqueFolderPath = dateFolder.getAbsolutePath() + File.separator + UUID.randomUUID();
		File uniqueFolder = createFolders(uniqueFolderPath, true);
		return uniqueFolder;
	}
	
	public static File createFolders(String path, boolean checkCreated) {
		File folder = new File(path);
		return createFolders(folder, checkCreated);
	}

	public static File createFolders(File folder, boolean checkCreated) {
		if(!folder.exists()) {
			boolean isCreated = folder.mkdirs();
			if(checkCreated && !isCreated && !folder.exists()) {
				throw new RuntimeException("Failed to create folder [" + folder.getAbsolutePath() + "]. Please check permission/symlink.");
			}
		}
		return folder;
	}
	
	/**
	 * This method checks if a file existed, if yes, add an index to the end of the name
	 * ie, D:\Test\
	 * @param parent
	 * @param name
	 * @return
	 */
	public synchronized static File getIndexedFileIfExisted(String parent, String name) {
		File parentFolder = new File(parent);
		createFolders(parentFolder, true);
		
		String filePath = parent + File.separator + name;
		File file = new File(filePath);
		if (file.exists() == false) {
			return file;
		}
		/**
		 * Already existed, we add an index to the end
		 */
		String extension = "";
		boolean isFile = file.isFile();
		if(isFile) {
			String fileName = file.getName();
			int offset = fileName.lastIndexOf(".");
			if(offset >= 0) {
				filePath = parent + File.separator + fileName.substring(0, offset);
				extension = fileName.substring(offset);
			}
		}
		for (int i = 1; i < MAX_INDEX; i++) {
			String index = Integer.toString(1000 + i);
			String indexedFilePath = filePath + "_" + index.substring(1) + extension;
			file = new File(indexedFilePath);
			if (file.exists() == false) {
				return file;
			}
		}
		throw new RuntimeException("Could not create folder for '" + filePath + "': exceed maximum index " + MAX_INDEX);
	}
	
	public static boolean sendStreamToUser(InputStream inputStream, HttpServletRequest request,
			HttpServletResponse response, String fileName) {
		BufferedInputStream biStream = null;
		OutputStream outStream = null;
		boolean result = false;

		try {
			outStream = response.getOutputStream();
			response.setContentType("application/x-download");
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE")) {
				response.setHeader("Content-Disposition", "attachment;filename=\""
						+ URLEncoder.encode(fileName, "UTF-8").replace("+", " ") + "\"");
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				String codedfilename = MimeUtility.encodeText(fileName, "UTF8", "B");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + codedfilename + "\"");
			} else {
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition", "attachment;filename=\""
						+ URLEncoder.encode(fileName, "UTF-8") + "\"");
			}

			biStream = new BufferedInputStream(inputStream);
			int fileLength = 0;
			byte[] buf = new byte[100000];
			int len;
			while ((len = biStream.read(buf)) > 0) {
				outStream.write(buf, 0, len);
				fileLength += len;
			}
			response.setContentLength(fileLength);
			result = true;
		} catch (Exception e) {
			log.error("Exception while sending file '" + fileName + "' to user", e);
		} finally {
			try {
				IOUtils.closeQuietly(biStream);
				IOUtils.closeQuietly(inputStream);

				if (outStream != null) {
					outStream.flush();
					IOUtils.closeQuietly(outStream);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
