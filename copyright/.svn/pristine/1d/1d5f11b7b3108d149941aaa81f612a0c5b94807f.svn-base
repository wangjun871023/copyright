package com.copyright.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;


public interface FileService {
	/**
	 * 查检文件的大小和类型
	 */
	BaseForm checkUploadInfo(String filename, File file, String typeInfo,
			String size);

	String uploadFile(File uploadFile, String uploadFileFileName, String fileId, HttpServletRequest httpServletRequest) throws Exception;
	public void deleteUploadFile(String fileId);
}
