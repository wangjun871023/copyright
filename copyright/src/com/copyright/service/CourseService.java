package com.copyright.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.UserEntity;

/**
 *课程Service接口
 */
public interface CourseService {
	/**
	 * 返回所有的课程信息
	 */
	BaseForm findAllCourses();
}
