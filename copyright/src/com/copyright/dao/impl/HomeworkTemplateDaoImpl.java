package com.copyright.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copyright.common.hibernate.HibernateDao;
import com.copyright.common.hibernate.HqlCondition;
import com.copyright.dao.CourseDao;
import com.copyright.dao.FileDao;
import com.copyright.dao.HomeworkTemplateDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.CourseEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkTemplateEntity;
import com.copyright.util.StringUtils;

@Repository
public class HomeworkTemplateDaoImpl extends HibernateDao<HomeworkTemplateEntity, String> implements HomeworkTemplateDao{
	@Autowired
	CourseDao courseDao;
	@Autowired
	FileDao fileDao;
	
	/**
	 * 增加作业模板
	 */
	@Override
	public HomeworkTemplateEntity createHomeworkTemplate(String homeworkName,
			String homeworkContent, String courseId, String fileId) {
		HomeworkTemplateEntity homeworkTemplateEntity = new HomeworkTemplateEntity();
		homeworkTemplateEntity.setHomeworkName(homeworkName);
		homeworkTemplateEntity.setHomeworkContent(homeworkContent);
		homeworkTemplateEntity.setCourseId(courseId);
		homeworkTemplateEntity.setCreateTime(System.currentTimeMillis()+"");
		homeworkTemplateEntity.setUpdateTime(System.currentTimeMillis()+"");
		homeworkTemplateEntity.setFileId(fileId);
		homeworkTemplateEntity.setHomeworkStatus(Constants.VALID_CONSTANT);
		save(homeworkTemplateEntity);
		return homeworkTemplateEntity;
	}
	
	/**
	 * 显示所有的作业模板
	 */
	@Override
	public List findAllHomeworkTemplates(String courseId,String homeworkStatus) {
		List param = new ArrayList();
		HqlCondition condition = new HqlCondition();
		condition.setSelStr("select entity "); 
		condition.setFromStr("from HomeworkTemplateEntity entity ");
		condition.setWhereStr("where 1=1 ");
		if (!StringUtils.isEmpty(courseId)){
			condition.setWhereStr("and entity.courseId = ? ");
			param.add(courseId);
		}
		if (!StringUtils.isEmpty(homeworkStatus)){
			condition.setWhereStr("and entity.homeworkStatus = ? ");
			param.add(homeworkStatus);
		}
		condition.setOrderStr(" order by entity.updateTime desc");
		List<HomeworkTemplateEntity> list = find(condition.toString(), param);
		if (list!=null && list.size()>0){
			for (HomeworkTemplateEntity homeworkTemplateEntity : list) {
				String courseIdVar = homeworkTemplateEntity.getCourseId();
				CourseEntity courseEntity = courseDao.findCourseById(courseIdVar);
				if(courseEntity!=null){
					homeworkTemplateEntity.setCourseName(courseEntity.getCourseName());
				}
				if (!StringUtils.isEmpty(homeworkTemplateEntity.getFileId())){
					FileEntity fileEntity = fileDao.getFileById(homeworkTemplateEntity.getFileId());
					homeworkTemplateEntity.setHomeworkFileName(fileEntity.getFileName());
					homeworkTemplateEntity.setHomeworkFilePath(fileEntity.getFilePath());
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 通过id找作业模板实体
	 */
	@Override
	public HomeworkTemplateEntity getHomeworkTemplateById(String homeworkTemplateId) {
		HomeworkTemplateEntity entity = getById(homeworkTemplateId);
		if(entity!=null){
			return entity;
		}
		return null;
	}
	
	/**
	 * 更新作业信息
	 */
	@Override
	public void updateHomeworkTemplate(String homeworkTemplateId,
			String homeworkName, String homeworkContent, String fileId) {
		HomeworkTemplateEntity homeworkTemplateEntity = getById(homeworkTemplateId);
		if(homeworkTemplateEntity!=null){
			homeworkTemplateEntity.setHomeworkName(homeworkName);
			homeworkTemplateEntity.setHomeworkContent(homeworkContent);
			homeworkTemplateEntity.setUpdateTime(System.currentTimeMillis()+"");
			if(!StringUtils.isEmpty(fileId)){
				homeworkTemplateEntity.setFileId(fileId);
			}
			save(homeworkTemplateEntity);
		}
	}
	/**
	 * 更新作业信息s
	 */
	@Override
	public void updateHomeworkTemplates(List<HomeworkTemplateEntity> list) {
		if(list!=null&&list.size()>0){
			for (HomeworkTemplateEntity homeworkTemplateEntity : list) {
				homeworkTemplateEntity.setUpdateTime(System.currentTimeMillis()+"");
				save(homeworkTemplateEntity);
			}
		}
	}
	/**
	 * 通过id
	 */
	@Override
	public HomeworkTemplateEntity findHomeworkTemplateById(String id) {
		HomeworkTemplateEntity entity = getById(id);
		if(entity!=null){
			return entity;
		}
		return null;
	}
}
