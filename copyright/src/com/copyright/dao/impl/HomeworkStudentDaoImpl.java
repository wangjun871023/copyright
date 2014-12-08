package com.copyright.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copyright.common.base.BaseForm;
import com.copyright.common.core.Page;
import com.copyright.common.hibernate.HibernateDao;
import com.copyright.common.hibernate.HqlCondition;
import com.copyright.dao.ClassHeadDao;
import com.copyright.dao.FileDao;
import com.copyright.dao.HomeworkDao;
import com.copyright.dao.HomeworkStudentDao;
import com.copyright.dao.StudentDao;
import com.copyright.dao.TeacherDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkStudentEntity;
import com.copyright.pojo.StudentEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.util.StringUtils;

@Repository
public class HomeworkStudentDaoImpl extends HibernateDao<HomeworkStudentEntity, String> implements HomeworkStudentDao{
	@Autowired 
	HomeworkDao homeworkDao;
	@Autowired 
	FileDao fileDao;
	@Autowired 
	StudentDao studentDao;
	@Autowired 
	ClassHeadDao classHeadDao;
	@Autowired 
	TeacherDao teacherDao;
	/**
	 * 增加作业
	 */
	@Override
	public void createHomeworkStudents(List<HomeworkEntity> homeworks, String studentIds) {
		if(homeworks!=null&&homeworks.size()>0){
			for (HomeworkEntity homeworkEntity : homeworks) {
				String homeworkId = homeworkEntity.getId();
				List<StudentEntity> students = studentDao.findByClassHeadIdAndStudentIds(homeworkEntity.getClassHeadId(),studentIds);
				if(students!=null &&students.size()>0){
					for (StudentEntity studentEntity : students) {
						HomeworkStudentEntity homeworkStudentEntity = new HomeworkStudentEntity();
						homeworkStudentEntity.setHomeworkId(homeworkId);
						homeworkStudentEntity.setStudentId(studentEntity.getId());
						homeworkStudentEntity.setStudentHomeworkStatus(Constants.HOMEWORK_STATUS_UNDO);
						save(homeworkStudentEntity);
					}
					homeworkEntity.setStudentNum(students.size()+"");
					homeworkEntity.setStudentNumUndo(students.size()+"");
					homeworkDao.saveHomework(homeworkEntity);
				}
			}
		}
	}
	/**
	 * 作业，学生显示
	 */
	@Override
	public List findAllHomeworkStudents(BaseForm baseForm,String homeworkId, String key,
			String page, String start, String limit,String studentHomeworkStatus,String teacherId) {
		List param = new ArrayList();
		HqlCondition condition = new HqlCondition();
		condition.setSelStr("select entity "); 
		condition.setFromStr("from HomeworkStudentEntity entity ");
		condition.setWhereStr("where 1=1 ");
		if (!StringUtils.isEmpty(homeworkId)){
			condition.setWhereStr("and entity.homeworkId = ? ");
			param.add(homeworkId);
		}
		if (!StringUtils.isEmpty(studentHomeworkStatus)){
			condition.setWhereStr("and entity.studentHomeworkStatus = ? ");
			param.add(studentHomeworkStatus);
		}
		if (!StringUtils.isEmpty(teacherId)){
			condition.setFromStr(",ClassHeadEntity classHead,HomeworkEntity homework ");
			
			condition.setWhereStr("and classHead.id =  homework.classHeadId and classHead.teacherId = ? and homework.id = entity.homeworkId ");
			param.add(teacherId);
			
		}
		if (!StringUtils.isEmpty(key)){
			condition.setFromStr(", StudentEntity student ");
			condition.setWhereStr("and entity.studentId = student.id  and (student.studentNo like ? or student.studentName like ?)");
			param.add("%"+key+"%");
			param.add("%"+key+"%");
		}
		condition.setOrderStr(" order by entity.updateTime desc");
		List<HomeworkStudentEntity> list = null;
		if (!StringUtils.isEmpty(limit)){
			Page pageVar = findPage(new Page<HomeworkStudentEntity>(Integer.parseInt(page),Integer.parseInt(limit)), condition.toString(), param);
			list = pageVar.getData();
			baseForm.setTotalRows(pageVar.getTotalCount()+"");
		}else{
			list = find(condition.toString(), param);
		}
		if (list!=null && list.size()>0){
			if (list!=null && list.size()>0){
				for (HomeworkStudentEntity homeworkStudentEntity : list) {
					String studentId = homeworkStudentEntity.getStudentId();
					StudentEntity student =  studentDao.findStudentById(studentId);
					HomeworkEntity homework = homeworkDao.getHomeworkById(homeworkStudentEntity.getHomeworkId());
					homeworkStudentEntity.setHomeworkName(homework.getHomeworkName());
					if (student!=null){
						homeworkStudentEntity.setStudentHomeworkId(homeworkStudentEntity.getId());
						homeworkStudentEntity.setStudentNo(student.getStudentNo());
						homeworkStudentEntity.setStudentName(student.getStudentName());
						homeworkStudentEntity.setStudentGender(student.getStudentGender());
						homeworkStudentEntity.setStudentSpecialty(student.getStudentSpecialty());
						homeworkStudentEntity.setStudentClass(student.getStudentClass());
						homeworkStudentEntity.setStudentGrade(student.getStudentGrade());
						ClassHeadEntity classHead = classHeadDao.findClassHeadById(student.getClassHeadId());
						if (classHead!=null){
							homeworkStudentEntity.setClassHeadName(classHead.getClassHeadName());
							TeacherEntity entity = teacherDao.findTeacherById(classHead.getTeacherId());
							if (entity!=null){
								homeworkStudentEntity.setTeacherName(entity.getTeacherName());
							}
						}
					}
					if (!StringUtils.isEmpty(homeworkStudentEntity.getStudentFileId())){
						FileEntity file = fileDao.getFileById(homeworkStudentEntity.getStudentFileId());
						if (file!=null){
							homeworkStudentEntity.setStudentHomeworkFileName(file.getFileName());
							homeworkStudentEntity.setStudentHomeworkFilePath(file.getFilePath());
						}
					}
				}
			}
			return list;
		}
		return null;
	}
	
	@Override
	public List findAllHomeworkStudentByStudentId(String studentId,String studentHomeworkStatus) {
		HqlCondition condition =new HqlCondition();
		String selStr = "select entity ";
		String fromStr = "from HomeworkStudentEntity entity ";
		String whereStr = "where 1=1 ";
		condition.setSelStr(selStr);
		condition.setFromStr(fromStr);
		condition.setWhereStr(whereStr);
		List params = new ArrayList();
		if (!StringUtils.isEmpty(studentId)){
			condition.setWhereStr("and entity.studentId = ? ");
			params.add(studentId);
		}
		if (!StringUtils.isEmpty(studentHomeworkStatus)){
			condition.setWhereStr("and entity.studentHomeworkStatus = ? ");
			params.add(studentHomeworkStatus);
		}
		List<HomeworkStudentEntity> list = find(condition.toString(),params);
		for (HomeworkStudentEntity homeworkStudentEntity : list) {
			HomeworkEntity homework = homeworkDao.getHomeworkById(homeworkStudentEntity.getHomeworkId());
			homeworkStudentEntity.setHomeworkName(homework.getHomeworkName());
			homeworkStudentEntity.setHomeworkContent(homework.getHomeworkContent());
			homeworkStudentEntity.setStartDate(homework.getStartDate());
			homeworkStudentEntity.setEndDate(homework.getEndDate());
			if (!StringUtils.isEmpty(homework.getFileId())){
				FileEntity file = fileDao.getFileById(homework.getFileId());
				if (file!=null){
					homeworkStudentEntity.setHomeworkFileId(file.getId());
					homeworkStudentEntity.setHomeworkFileName(file.getFileName());
					homeworkStudentEntity.setHomeworkFilePath(file.getFilePath());
				}
			}
			if (!StringUtils.isEmpty(homeworkStudentEntity.getStudentFileId())){
				FileEntity file = fileDao.getFileById(homeworkStudentEntity.getStudentFileId());
				if (file!=null){
					homeworkStudentEntity.setStudentHomeworkFileName(file.getFileName());
					homeworkStudentEntity.setStudentHomeworkFilePath(file.getFilePath());
				}
			}
			if (!StringUtils.isEmpty(homeworkStudentEntity.getStudentId())){
				StudentEntity studentEntity = studentDao.findStudentById(homeworkStudentEntity.getStudentId());
				if (studentEntity!=null){
					homeworkStudentEntity.setStudentName(studentEntity.getStudentName());
				}
				ClassHeadEntity classHeadEntity = classHeadDao.findClassHeadById(studentEntity.getClassHeadId());
				if (classHeadEntity!=null){
					homeworkStudentEntity.setClassHeadName(classHeadEntity.getClassHeadName());
					TeacherEntity teacherEntity = teacherDao.findTeacherById(classHeadEntity.getTeacherId());
					if(teacherEntity!=null){
						homeworkStudentEntity.setTeacherName(teacherEntity.getTeacherName());
					}
				}
			}
		}
		if (list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	

	@Override
	public void updateHomeworkStudent(String homeworkId, String studentIds) {
		String hql = "select entity from HomeworkStudentEntity entity where entity.homeworkId = ?";
		List<HomeworkStudentEntity> list = find(hql,homeworkId);
		//有bug
		
		//先删除
		if (list!=null && list.size()>0){
			for (HomeworkStudentEntity entity : list) {
				delete (entity);
			}
		}
		//后插入
		String[] stuIds =  studentIds.split(",");
		for (String stuId : stuIds) {
			HomeworkStudentEntity entity = new HomeworkStudentEntity();
			entity.setStudentId(stuId);
			entity.setHomeworkId(homeworkId);
			entity.setCreateTime(System.currentTimeMillis()+"");
			entity.setUpdateTime(System.currentTimeMillis()+"");
			entity.setStudentHomeworkStatus(Constants.HOMEWORK_STATUS_UNDO);
			save(entity);
		}
	}

	@Override
	public void deleteHomeworkStudent(List<HomeworkEntity> list) {
		for (HomeworkEntity homeworkEntity : list) {
			String hql = "select entity from HomeworkStudentEntity entity where entity.homeworkId = ?";
			List<HomeworkStudentEntity> tmpList = find(hql,homeworkEntity.getId());
			if (tmpList!=null && tmpList.size()>0){
				for (HomeworkStudentEntity homeworkStudentEntity : tmpList) {
					delete (homeworkStudentEntity);
				}
			}
		}
	}

	@Override
	public void updateStudentHomework(String homeworkStudentId,
			String homeworkStudentContent, String fileId) {
		HomeworkStudentEntity entity = getById(homeworkStudentId);
		if (entity!=null){
			entity.setStudentHomeworkStatus(Constants.HOMEWORK_STATUS_DO);
			entity.setStudentHomeworkContent(homeworkStudentContent);
			if(!fileId.equals(""))
			entity.setStudentFileId(fileId);
			save(entity);
		}
	}

	@Override
	public HomeworkStudentEntity findById(String id) {
		HomeworkStudentEntity entity = getById(id);
		
		return entity;
	}

	@Override
	public void saveHomeworkStudent(HomeworkStudentEntity entity) {
		save(entity);
	}
}
