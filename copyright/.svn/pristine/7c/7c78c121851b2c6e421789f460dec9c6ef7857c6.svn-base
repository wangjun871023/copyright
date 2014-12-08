package com.copyright.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copyright.common.hibernate.HibernateDao;
import com.copyright.common.hibernate.HqlCondition;
import com.copyright.dao.ClassHeadDao;
import com.copyright.dao.FileDao;
import com.copyright.dao.HomeworkDao;
import com.copyright.dao.HomeworkTemplateDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkTemplateEntity;
import com.copyright.util.StringUtils;

@Repository
public class HomeworkDaoImpl extends HibernateDao<HomeworkEntity, String> implements HomeworkDao{
	@Autowired
	ClassHeadDao classHeadDao;
	@Autowired
	HomeworkTemplateDao homeworkTemplateDao;
	@Autowired
	FileDao fileDao;
	
	/**
	 * Homeworks显示
	 */
	@Override
	public List findAllHomeworks(String classHeadId,String classHeadIds,String homeworkStatus,String teacherId) {
		List param = new ArrayList();
		HqlCondition condition = new HqlCondition();
		condition.setSelStr("select entity "); 
		condition.setFromStr("from HomeworkEntity entity ");
		condition.setWhereStr("where 1=1 ");
		if (!StringUtils.isEmpty(classHeadId)){
			condition.setWhereStr("and entity.classHeadId = ? ");
			param.add(classHeadId);
		}
		if (!StringUtils.isEmpty(homeworkStatus)){
			condition.setWhereStr("and entity.homeworkStatus = ? ");
			param.add(homeworkStatus);
		}
		if (!StringUtils.isEmpty(classHeadIds)){
			classHeadIds = classHeadIds.substring(0,classHeadIds.length()-1);
			condition.setWhereStr("and entity.classHeadId in ("+classHeadIds+") ");
		}
		
		if (!StringUtils.isEmpty(teacherId)){
			condition.setFromStr(",ClassHeadEntity classHead ");
			condition.setWhereStr("and classHead.id =  entity.classHeadId and classHead.teacherId = ? ");
			param.add(teacherId);
		}
		
		condition.setOrderStr(" order by entity.updateTime desc");
		List<HomeworkEntity> list = find(condition.toString(), param);
		if (list!=null && list.size()>0){
			for (HomeworkEntity homeworkEntity : list) {
				String clazzHeadId = homeworkEntity.getClassHeadId();
				ClassHeadEntity classHeadEntity = classHeadDao.findClassHeadById(clazzHeadId);
				if(classHeadEntity!=null){
					homeworkEntity.setClassHeadName(classHeadEntity.getClassHeadName());
				}
				if (!StringUtils.isEmpty(homeworkEntity.getFileId())){
					FileEntity fileEntity = fileDao.getFileById(homeworkEntity.getFileId());
					if(fileEntity!=null){
						homeworkEntity.setHomeworkFileName(fileEntity.getFileName());
						homeworkEntity.setHomeworkFilePath(fileEntity.getFilePath());
					}
				}
			}
			return list;
		}
		return null;
	}
	/**
	 * 添加homework
	 */
	@Override
	public List<HomeworkEntity> createHomework(String homeworkName, String homeworkContent,
			String startDate,
			String endDate,String classHeadIds,String fileId) {
		List<HomeworkEntity> list = new ArrayList<HomeworkEntity>(); 
		HomeworkTemplateEntity homeworkTemplateEntity =  homeworkTemplateDao.findHomeworkTemplateById(homeworkName);
		if(homeworkTemplateEntity!=null){
			if(!StringUtils.isEmpty(classHeadIds)){
				String[] ids =  classHeadIds.split(",");
				for (String classHeadId : ids) {
					HomeworkEntity homeworkEntity = new HomeworkEntity();
					homeworkEntity.setHomeworkName(homeworkTemplateEntity.getHomeworkName());
					homeworkEntity.setHomeworkContent(homeworkTemplateEntity.getHomeworkContent());
					homeworkEntity.setStartDate(startDate);
					homeworkEntity.setEndDate(endDate);
					homeworkEntity.setHomeworkStatus(Constants.VALID_CONSTANT);
					homeworkEntity.setStudentNumFinish("0");
					homeworkEntity.setClassHeadId(classHeadId.replace("'", ""));
					homeworkEntity.setFileId(homeworkTemplateEntity.getFileId());
					save(homeworkEntity);
					list.add(homeworkEntity);
				}
			}
		}else{
			if(!StringUtils.isEmpty(classHeadIds)){
				String[] ids =  classHeadIds.split(",");
				for (String classHeadId : ids) {
					HomeworkEntity homeworkEntity = new HomeworkEntity();
					homeworkEntity.setHomeworkName(homeworkName);
					homeworkEntity.setHomeworkContent(homeworkContent);
					homeworkEntity.setStartDate(startDate);
					homeworkEntity.setEndDate(endDate);
					homeworkEntity.setStudentNumFinish("0");
					homeworkEntity.setHomeworkStatus(Constants.VALID_CONSTANT);
					homeworkEntity.setClassHeadId(classHeadId.replace("'", ""));
					homeworkEntity.setFileId(fileId);
					save(homeworkEntity);
					list.add(homeworkEntity);
				}
			}
		}
		return list;
	}

	/**
	 * 更新homework
	 */
	@Override
	public void updateHomework(String id, String homeworkName,
			String homeworkContent, String startDate, String endDate,String studentNum,String fileId) {
		HomeworkEntity entity = getHomeworkById(id);
		entity.setHomeworkName(homeworkName);
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		entity.setStudentNum(studentNum);
		if(!StringUtils.isEmpty(fileId)){
			entity.setFileId(fileId);
		}
		entity.setHomeworkContent(homeworkContent);
		save(entity);
	}
	
	/**
	 * 通过id查找
	 */
	@Override
	public HomeworkEntity getHomeworkById(String id) {
		HomeworkEntity entity = getById(id);
		if (entity!=null){
			return entity;
		}
		return null;
	}
	/**
	 * 删除
	 */
	@Override
	public void deleteHomeworks(List<HomeworkEntity> list) {
		if (list!=null && list.size()>0){
			for (HomeworkEntity homeworkEntity : list) {
				fileDao.deleteFileById(homeworkEntity.getFileId());
				delete (homeworkEntity);
			}
		}
	}
	@Override
	public void saveHomework(HomeworkEntity homeworkEntity) {
		save(homeworkEntity);
	}
	@Override
	public void updateHomeworks(List<HomeworkEntity> list) {
		if(list!=null&&list.size()>0){
			for (HomeworkEntity homeworkEntity : list) {
				save(homeworkEntity);
			}
		}
	}
}
