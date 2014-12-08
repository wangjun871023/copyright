package com.copyright.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.common.base.BaseForm;
import com.copyright.common.base.JsonTreeUtil;
import com.copyright.dao.ClassHeadDao;
import com.copyright.dao.FileDao;
import com.copyright.dao.HomeworkDao;
import com.copyright.dao.HomeworkStudentDao;
import com.copyright.dao.StudentDao;
import com.copyright.dao.TeacherDao;
import com.copyright.dao.TempDao;
import com.copyright.dao.UserDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkStudentEntity;
import com.copyright.pojo.StudentEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.service.FileService;
import com.copyright.service.TeacherService;
import com.copyright.util.ReadRequestPayloadUtil;
import com.copyright.util.StringUtils;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private UserDao userDao;

	/**
	 * 当前课程下的所有教师Service
	 */
	@Override
	public BaseForm findAllTeachers(String courseId, String teacherCode,
			String validStatus) {
		BaseForm baseForm = new BaseForm();
		List<TeacherEntity> items = teacherDao.findAllTeachers(courseId,
				teacherCode, validStatus);
		if (items != null && items.size() > 0) {
			baseForm.setItems(items);
		}
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 新增教师Service
	 */
	@Override
	public BaseForm createTeachers(HttpServletRequest request) throws Exception {
		BaseForm baseForm = new BaseForm();
		List<TeacherEntity> list = ReadRequestPayloadUtil
				.getEntityListByReadRequestPayload(request, TeacherEntity.class);
		if (list != null && list.size() > 0) {
			// 写入teacher_table
			teacherDao.createTeachers(list, request);
			// 写入user_table
			userDao.createUsersByTeachers(list, request);
			baseForm.setSuccess(true);
		}
		return baseForm;
	}

	/**
	 * 更新教师Service
	 */
	@Override
	public BaseForm updateTeachers(HttpServletRequest request) throws Exception {
		BaseForm baseForm = new BaseForm();
		List<TeacherEntity> list = ReadRequestPayloadUtil
				.getEntityListByReadRequestPayload(request, TeacherEntity.class);
		// 更新teacher_table
		teacherDao.updateTeachers(list, request);
		baseForm.setSuccess(true);
		return baseForm;
	}
	/**
	 * 显示教师树
	 */
	@Override
	public BaseForm findTeacherTree(String courseId) throws Exception {
		BaseForm baseForm = new BaseForm();
		JSONArray jsonArray = new JSONArray();
		List<TeacherEntity> teachers = teacherDao.findAllTeachers(courseId,
				null, null);
		if (teachers != null && teachers.size() > 0) {
			for (TeacherEntity teacherEntity : teachers) {
				teacherEntity.setText(teacherEntity.getTeacherName());
				teacherEntity.setLeaf(true);
				teacherEntity.setParentId(Constants.TREE_ROOT_ID);
				jsonArray.add(teacherEntity);
			}
		}
		baseForm.setJsonArray(jsonArray);
		baseForm.setSuccess(true);
		return baseForm;
	}
}
