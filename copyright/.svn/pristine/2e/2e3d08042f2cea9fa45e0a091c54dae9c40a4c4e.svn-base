package com.copyright.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copyright.common.hibernate.HibernateDao;
import com.copyright.common.hibernate.HqlCondition;
import com.copyright.dao.ClassHeadDao;
import com.copyright.dao.TeacherDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.pojo.UserEntity;
import com.copyright.util.StringUtils;

@Repository
public class TeacherDaoImpl extends HibernateDao<TeacherEntity, String>
		implements TeacherDao {
	@Autowired
	ClassHeadDao classHeadDao;

	/**
	 * 当前课程下的所有教师dao
	 */
	@Override
	public List<TeacherEntity> findAllTeachers(String courseId,
			String teacherCode, String validStatus) {
		HqlCondition condition = new HqlCondition();
		condition.setSelStr("select entity ");
		condition.setFromStr("from TeacherEntity entity ");
		condition.setWhereStr("where 1=1 ");
		List params = new ArrayList();
		if (!StringUtils.isEmpty(courseId)) {
			condition.setWhereStr("and entity.courseId = ? ");
			params.add(courseId);
		}
		if (!StringUtils.isEmpty(teacherCode)) {
			condition.setWhereStr("and entity.teacherCode = ? ");
			params.add(teacherCode);
		}
		if (!StringUtils.isEmpty(validStatus)) {
			condition.setWhereStr("and entity.teacherStatus = ? ");
			params.add(validStatus);
		}
		List<TeacherEntity> items = find(condition.toString(), params);
		if (items != null && items.size() > 0) {
			return items;
		}
		return null;
	}

	/**
	 * 新增教师dao
	 */
	@Override
	public void createTeachers(List<TeacherEntity> list,
			HttpServletRequest request) throws Exception {
		if (list != null && list.size() > 0) {
			UserEntity userEntity = (UserEntity) request.getSession()
					.getAttribute(Constants.LOGIN_USER_ENTITY);
			if (userEntity != null) {
				for (TeacherEntity teacherEntity : list) {
					teacherEntity.setId(null);
					teacherEntity
							.setCreateTime(System.currentTimeMillis() + "");
					teacherEntity
							.setUpdateTime(System.currentTimeMillis() + "");
					teacherEntity.setCreateUserId(userEntity.getId());
					teacherEntity.setTeacherStatus(Constants.VALID_CONSTANT);
					create(teacherEntity);
				}
			}
		}
	}

	/**
	 * 更新教师dao
	 */
	@Override
	public void updateTeachers(List<TeacherEntity> list,
			HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			UserEntity userEntity = (UserEntity) request.getSession()
					.getAttribute(Constants.LOGIN_USER_ENTITY);
			if (userEntity != null) {
				for (TeacherEntity teacherEntity : list) {
					teacherEntity
							.setUpdateTime(System.currentTimeMillis() + "");
					teacherEntity.setUpdateUserId(userEntity.getId());
					save(teacherEntity);
				}
			}
		}
	}

	/**
	 * 当前id的教师
	 */
	@Override
	public TeacherEntity findTeacherById(String teacherId) {
		TeacherEntity entity = getById(teacherId);
		if(entity!=null){
			return entity;
		}
		return null;
	}
}
