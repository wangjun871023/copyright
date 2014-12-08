package com.copyright.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copyright.common.base.BaseForm;
import com.copyright.common.hibernate.HibernateDao;
import com.copyright.common.hibernate.HqlCondition;
import com.copyright.dao.ClassHeadDao;
import com.copyright.dao.CourseDao;
import com.copyright.dao.HomeworkDao;
import com.copyright.dao.TeacherDao;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.CourseEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.util.StringUtils;

/**
 * 课头Dao实现
 */
@Repository
public class ClassHeadDaoImpl extends HibernateDao<ClassHeadEntity, String>
		implements ClassHeadDao {
	@Autowired
	HomeworkDao homeworkDao;
	@Autowired
	TeacherDao teacherDao;

	/**
	 * 当前教师下的所有课头信息Dao
	 */
	@Override
	public List findCourseHeads(String teacherId,String classHeadStatus,String courseId) {
		List param = new ArrayList();
		HqlCondition condition = new HqlCondition();
		condition.setSelStr("select entity "); 
		condition.setFromStr("from ClassHeadEntity entity ");
		condition.setWhereStr("where 1=1 ");
		if (!StringUtils.isEmpty(teacherId)){
			condition.setWhereStr("and entity.teacherId = ? ");
			param.add(teacherId);
		}
		if (!StringUtils.isEmpty(classHeadStatus)){
			condition.setWhereStr("and entity.classHeadStatus = ? ");
			param.add(classHeadStatus);
		}
		if (!StringUtils.isEmpty(courseId)){
			condition.setFromStr(",TeacherEntity teacher ");
			condition.setWhereStr("and teacher.id = entity.teacherId and teacher.courseId = ? ");
			param.add(courseId);
		}
		condition.setOrderStr(" order by entity.updateTime desc");
		
		List<ClassHeadEntity> list = find(condition.toString(), param);
		if (list != null && list.size() > 0) {
			for (ClassHeadEntity classHeadEntity : list) {
				TeacherEntity teacherEntity = teacherDao.findTeacherById(classHeadEntity.getTeacherId());
				if (teacherEntity!=null){
					classHeadEntity.setTeacherName(teacherEntity.getTeacherName());
				}
			}
			return list;
		}
		return null;
	}

	/**
	 * 通过clazzHeadId找ClassHeadEntity
	 */
	@Override
	public ClassHeadEntity findClassHeadById(String clazzHeadId) {
		ClassHeadEntity classHeadEntity = getById(clazzHeadId);
		if (classHeadEntity != null) {
			return classHeadEntity;
		}
		return null;
	}

	/**
	 * 添加课头通过 List
	 */
	@Override
	public void createCourseHeads(List<ClassHeadEntity> list) throws Exception {
		for (ClassHeadEntity classHeadEntity : list) {
			classHeadEntity.setId(null);
			save(classHeadEntity);
		}
	}

	/**
	 * 修改课头
	 */
	@Override
	public void updateCourseHeads(List<ClassHeadEntity> list) {
		for (ClassHeadEntity classHeadEntity : list) {
			save(classHeadEntity);
		}
	}

	/**
	 * 删除课头
	 */
	@Override
	public void deleteCourseHeads(List<ClassHeadEntity> list) {
		for (ClassHeadEntity classHeadEntity : list) {
			delete(classHeadEntity);
		}
	}

	/**
	 * 添加课头
	 */
	@Override
	public void createCourseHead(ClassHeadEntity classHeadEntity) {
		save(classHeadEntity);
	}

	@Override
	public void saveClassHead(ClassHeadEntity classHeadEntity) {
		save(classHeadEntity);
	}
}
