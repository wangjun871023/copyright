package com.copyright.dao;

import java.util.List;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.CourseEntity;
import com.copyright.pojo.UserEntity;

/**
 *课程Dao接口
 */
public interface CourseDao {
	/**
	 * 返回全部的课程信息
	 */
	List<CourseEntity> findAllCourses();
	/**
	 * 通过id找课程实体
	 */
	CourseEntity findCourseById(String courseId);
}
