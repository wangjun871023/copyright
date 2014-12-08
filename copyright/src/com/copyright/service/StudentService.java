package com.copyright.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.UserEntity;

public interface StudentService {
	BaseForm findAllStudents(String classHeadId, String page, String start, String limit, String key, String studentCode, String studentStatus, String classHeadIds, String teacherId, String courseId);
	BaseForm createStudents(HttpServletRequest httpServletRequest) throws Exception;
	BaseForm updateStudents(HttpServletRequest request) throws Exception;
	BaseForm deleteStudents(HttpServletRequest request)throws Exception;
	BaseForm activeStudent(String courseId, String studentId, String studentActiveCode); 
}
