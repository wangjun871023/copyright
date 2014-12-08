package com.copyright.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.common.base.BaseForm;
import com.copyright.dao.CourseDao;
import com.copyright.pojo.CourseEntity;
import com.copyright.service.CourseService;
/**
 *课程Service接口实现 
 */
@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseDao courseDao;
	
	/**
	 * 返回所有的课程信息
	 */
	@Override
	public BaseForm findAllCourses() {
		BaseForm baseForm = new BaseForm();
		List<CourseEntity> courses = courseDao.findAllCourses();
		if (courses!=null && courses.size()>0){
			baseForm.setItems(courses);
		}
		baseForm.setSuccess(true);
		return baseForm;
	}
}
