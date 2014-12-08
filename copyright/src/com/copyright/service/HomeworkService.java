package com.copyright.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;

public interface HomeworkService {
	/**
	 * 作业信息显示
	 * @param classHeadIds 
	 * @param homeworkStatus 
	 * @param teacherId 
	 */
	BaseForm findAllHomeworks(String classHeadId, String classHeadIds, String homeworkStatus, String teacherId);

	/**
	 * 新增作业
	 * @param fileService 
	 */
	BaseForm createHomework(String homeworkName, String homeworkContent,
			File uploadFile, String studentIds, String startDate,
			String endDate, String uploadFileFileName, String classHeadIds,
			HttpServletRequest httpServletRequest, FileService fileService) throws Exception;

	/**
	 * 更新作业
	 * @param fileService 
	 */
	BaseForm updateHomework(String homeworkId, String homeworkName,
			String homeworkContent, String studentIds, String startDate,
			String endDate, File uploadFile, String uploadFileFileName,
			HttpServletRequest httpServletRequest, FileService fileService) throws Exception;

	BaseForm findAllHomeworkStudent(String homeworkId, String studentId,
			String studentHomeworkStatus);

	BaseForm editStudentHomework(String homeworkStudentId, File uploadFile,
			String uploadFileFileName, String homeworkStudentContent,
			String studentFileId, HttpServletRequest httpServletRequest, FileService fileService)
			throws Exception;
	/**
	 * 更新作业
	 * @throws Exception 
	 */
	BaseForm updateHomeworks(HttpServletRequest request) throws Exception;

}
