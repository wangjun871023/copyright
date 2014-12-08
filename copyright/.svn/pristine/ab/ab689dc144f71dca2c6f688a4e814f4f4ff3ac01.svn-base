package com.copyright.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.copyright.common.base.BaseForm;
import com.copyright.common.hibernate.HibernateDao;
import com.copyright.dao.CourseDao;
import com.copyright.pojo.CourseEntity;

/**
 *课程Dao接口实现
 */
@Repository
public class CourseDaoImpl extends HibernateDao<CourseEntity, String> implements CourseDao{
	/**
	 * 返回全部的课程
	 */
	@Override
	public List<CourseEntity> findAllCourses() {
		List<CourseEntity> list = getAll();
		if (list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	/**
	 * 通过id找课程
	 */
	@Override
	public CourseEntity findCourseById(String courseId) {
		CourseEntity courseEntity = getById(courseId);
		if(courseEntity!=null){
			return courseEntity;
		}
		return null;
	}
}
