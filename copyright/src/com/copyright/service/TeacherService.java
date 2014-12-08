package com.copyright.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.UserEntity;

public interface TeacherService {
	BaseForm findAllTeachers(String courseId, String teacherCode, String validStatus);
	BaseForm createTeachers(HttpServletRequest request) throws Exception;
	BaseForm updateTeachers(HttpServletRequest request) throws Exception;
	BaseForm findTeacherTree(String courseId)throws Exception;
}
