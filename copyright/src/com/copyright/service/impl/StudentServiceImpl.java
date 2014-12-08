package com.copyright.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.common.base.BaseForm;
import com.copyright.dao.ActiveCodeDao;
import com.copyright.dao.StudentDao;
import com.copyright.dao.UserDao;
import com.copyright.pojo.StudentEntity;
import com.copyright.service.StudentService;
import com.copyright.util.ReadRequestPayloadUtil;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentDao studentDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ActiveCodeDao activeCodeDao;
	
	/**
	 * 显示学生列表
	 */
	@Override
	public BaseForm findAllStudents(String classHeadId,String page, String start, 
			String limit,String key,String studentCode,
			String studentStatus,String classHeadIds,
			String teacherId,String courseId) {
		BaseForm baseForm = new BaseForm();
		List students = studentDao.findAllStudents(baseForm,classHeadId,page,start,limit,key,studentCode,studentStatus,classHeadIds,teacherId,courseId);
		if(students!=null && students.size()>0){
			baseForm.setItems(students);
		}
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	
	/**
	 * 增加学生
	 */
	@Override
	public BaseForm createStudents(HttpServletRequest request) throws Exception {
		BaseForm baseForm = new BaseForm();
		List<StudentEntity> list = ReadRequestPayloadUtil.getEntityListByReadRequestPayload(request, StudentEntity.class); 
		studentDao.createStudents(list);
		//添加用户
		userDao.createUsersByStudents(list);
		baseForm.setSuccess(true);
		return baseForm;
	}
	/**
	 * 更新学生
	 */
	@Override
	public BaseForm updateStudents(HttpServletRequest request) throws Exception {
		BaseForm baseForm = new BaseForm();
		List<StudentEntity> list = ReadRequestPayloadUtil.getEntityListByReadRequestPayload(request, StudentEntity.class); 
		studentDao.updateStudents(list);
		baseForm.setSuccess(true);
		return baseForm;
	}
	/**
	 * 删除学生
	 */
	@Override
	public BaseForm deleteStudents(HttpServletRequest request) throws Exception {
		BaseForm baseForm = new BaseForm();
		List<StudentEntity> list = ReadRequestPayloadUtil.getEntityListByReadRequestPayload(request, StudentEntity.class); 
		studentDao.deleteStudents(list);
		userDao.deleteUsersByStudents(list);
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 激活
	 */
	@Override
	public BaseForm activeStudent(String courseId, String studentNo,String studentActiveCode) {
		BaseForm baseForm = new BaseForm();
		activeCodeDao.checkActiveCodes(courseId,studentNo,studentActiveCode,baseForm);
		return baseForm;
	}
}
