package com.copyright.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;

/**
 * 课头Service接口
 */
public interface CourseHeadService {
	/**
	 * 当前教师下的所有课头信息Service
	 * @param classHeadStatus 
	 * @param courseId 
	 */
	BaseForm findCourseHeads(String teacherId, String classHeadStatus, String courseId);

	/**
	 * 新增课头
	 */
	BaseForm createCourseHead(File courseHeadExcel,
			String courseHeadExcelContentType, String courseHeadExcelFileName,
			String classHeadName, String teacherId, String startDate,
			String endDate, HttpServletRequest request, String courseId,
			FileService fileService) throws Exception;

	/**
	 * 删除课头
	 */
	BaseForm deleteCourseHeads(HttpServletRequest request) throws Exception;

	/**
	 * 当前课程的全部教师与课头--管理员学生用户管理
	 */
	BaseForm findTeacherCourseHeads(String courseId, String node);
	/**
	 * 修改课头
	 * @throws Exception 
	 */
	BaseForm updateCourseHead(File courseHeadExcel,
			String courseHeadExcelContentType, String courseHeadExcelFileName,
			String classHeadName, String classHeadId, String startDate,
			String endDate, HttpServletRequest request, String courseId,
			FileService fileService) throws Exception;
	/**
	 * 当前教师下的所有课头信息Service 返回树JSON
	 */
	BaseForm findCourseHeadsTree(String teacherId);
	/**
	 * 修改课头信息
	 * @throws Exception
	 */
	BaseForm updateCourseHeadInfo(HttpServletRequest request) throws Exception;
}
