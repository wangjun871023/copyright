package com.copyright.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;

public interface HomeworkTemplateService {
	/**
	 * 作业信息显示
	 * @param homeworkStatus 
	 */
	BaseForm findAllHomeworkTemplates(String courseId, String homeworkStatus);

	/**
	 * 新增作业
	 * @param fileService 
	 * @throws Exception 
	 */
	BaseForm createHomeworkTemplate(String homeworkName,
			String homeworkContent, File uploadFile, String uploadFileFileName,
			String courseId, HttpServletRequest request, FileService fileService) throws Exception;

	/**
	 * 更新作业
	 * @param fileService 
	 */
	BaseForm updateHomeworkTemplate(String homeworkTemplateId, String homeworkName,
			String homeworkContent,
			File uploadFile, String uploadFileFileName,
			HttpServletRequest httpServletRequest, FileService fileService) throws Exception;
	
	/**
	 * 更新作业
	 */
	BaseForm updateHomeworkTemplates(HttpServletRequest request) throws Exception;

	
}
