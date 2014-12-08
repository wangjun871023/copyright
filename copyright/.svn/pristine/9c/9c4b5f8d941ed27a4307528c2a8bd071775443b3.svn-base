package com.copyright.service.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.common.base.BaseForm;
import com.copyright.dao.ClassHeadDao;
import com.copyright.dao.HomeworkDao;
import com.copyright.dao.HomeworkStudentDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkStudentEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.service.FileService;
import com.copyright.service.HomeworkCheckService;
import com.copyright.util.ReadRequestPayloadUtil;
import com.copyright.util.StringUtils;

@Service
public class HomeworkCheckServiceImpl implements HomeworkCheckService {
	@Autowired
	ClassHeadDao classHeadDao;
	@Autowired
	HomeworkDao homeworkDao;
	@Autowired
	HomeworkStudentDao homeworkStudentDao;

	/**
	 * 课头作业树显示
	 */
	@Override
	public BaseForm findCourseHeadHomework(String teacherId, String node) {
		BaseForm baseForm = new BaseForm();
		JSONArray jsonArray = new JSONArray();
		if (node.equals(Constants.TREE_ROOT_ID)) {
			List<ClassHeadEntity> classHeads = classHeadDao
					.findCourseHeads(teacherId,null,null);
			if (classHeads != null && classHeads.size() > 0) {
				for (ClassHeadEntity classHeadEntity : classHeads) {
					TreeNode treeNode = new TreeNode();
					treeNode.setId(classHeadEntity.getId());
					treeNode.setText(classHeadEntity.getClassHeadName());
					treeNode.setExpandable(true);
					treeNode.setParentId(node);
					jsonArray.add(treeNode);
				}
			}
		} else {
			List<HomeworkEntity> homeworks = homeworkDao
					.findAllHomeworks(node,null,null,null);
			if (homeworks != null && homeworks.size() > 0) {
				for (HomeworkEntity homeworkEntity : homeworks) {
					homeworkEntity.setLeaf(true);
					homeworkEntity.setText(homeworkEntity.getHomeworkName());
					homeworkEntity.setParentId(node);
					jsonArray.add(homeworkEntity);
				}
			}
		}
		baseForm.setJsonArray(jsonArray);
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 检查学生作业
	 */
	@Override
	public BaseForm findAllHomeworkToCheck(String homeworkId, String key,
			String page, String start, String limit,
			String studentHomeworkStatus, String teacherId) {
		BaseForm baseForm = new BaseForm();
		List list = homeworkStudentDao.findAllHomeworkStudents(baseForm,
				homeworkId, key, page, start, limit, studentHomeworkStatus,teacherId);
		baseForm.setItems(list);
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 给定学生成绩
	 */
	@Override
	public BaseForm updateHomeworkStudentScore(String studentHomeworkId,
			String studentScore) {
		BaseForm baseForm = new BaseForm();
		HomeworkStudentEntity entity = homeworkStudentDao
				.findById(studentHomeworkId);
		if (entity != null) {
			entity.setStudentScore(studentScore);
			entity.setStudentHomeworkStatus(Constants.HOMEWORK_STATUS_READ);
			homeworkStudentDao.saveHomeworkStudent(entity);
		}
		baseForm.setSuccess(true);
		return baseForm;
	}
}
