package com.copyright.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.pojo.UserEntity;

public interface TeacherDao {
	List<TeacherEntity> findAllTeachers(String courseId,String teacherCode, String validStatus);
	void createTeachers(List<TeacherEntity> list, HttpServletRequest request) throws Exception;
	void updateTeachers(List<TeacherEntity> list, HttpServletRequest request);
	TeacherEntity findTeacherById(String teacherId);
}
