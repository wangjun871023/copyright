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
import com.copyright.dao.StudentDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ActiveCodeEntity;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.StudentEntity;
import com.copyright.pojo.UserEntity;
import com.copyright.util.StringUtils;

@Repository
public class StudentDaoImpl extends HibernateDao<StudentEntity, String> implements StudentDao{
	@Autowired
	ClassHeadDao classHeadDao;
	
	/**
	 * 临时表，写入学生表
	 */
	@Override
	public void createIntoStudentTable(String fileId,String classHeadId,String courseId) {
		String sql = "insert into student_table" +
				"(id,student_no,student_name,student_gender," +
				"student_specialty,student_class," +
				"state_selected_class,student_grade,student_again,class_head_id," +
				"student_code,student_status,course_id) " +
				"select temp_table.id,temp_table.column1,temp_table.column2," +
				"temp_table.column3,temp_table.column4,temp_table.column5,temp_table.column6,temp_table.column8,temp_table.column9,?,temp_table.column1,? ,?" +
				"from temp_table " +
				"where temp_table.file_id = ?";
		int results = getSession().createSQLQuery(sql).
				setString(0,classHeadId).
				setString(1, courseId).
				setString(2, Constants.VALID_CONSTANT).
				setString(3, fileId).
				executeUpdate();
	}

	/**
	 * 显示学生列表
	 */
	@Override
	public List findAllStudents(BaseForm baseForm,String classHeadId,
			String page, String start, String limit,String key,
			String studentCode,String studentStatus,String classHeadIds,String teacherId,String courseId) {
		List param = new ArrayList();
		HqlCondition condition = new HqlCondition();
		condition.setSelStr("select entity "); 
		condition.setFromStr("from StudentEntity entity ");
		condition.setWhereStr("where 1=1 ");
		if (!StringUtils.isEmpty(classHeadId)){
			condition.setWhereStr("and entity.classHeadId = ? ");
			param.add(classHeadId);
		}
		if (!StringUtils.isEmpty(studentCode)){
			condition.setWhereStr("and entity.studentCode = ? ");
			param.add(studentCode);
		}
		if (!StringUtils.isEmpty(key)){
			condition.setWhereStr("and (entity.studentNo like ? or entity.studentName like ?) ");
			param.add("%"+key+"%");
			param.add("%"+key+"%");
		}
		if (!StringUtils.isEmpty(studentStatus)){
			if(studentStatus.equals(Constants.ACTIVE_CONSTANT)){
				condition.setFromStr(",ActiveCodeEntity active ");
				condition.setWhereStr("and active.studentId = entity.id and active.activeStatus = ? ");
				param.add(studentStatus);
			}else{
				condition.setWhereStr("and entity.studentActiveCode IS NULL ");
			}
		}
		if (!StringUtils.isEmpty(teacherId)){
			condition.setFromStr(",ClassHeadEntity classHead ");
			condition.setWhereStr("and classHead.id =  entity.classHeadId and classHead.teacherId = ? ");
			param.add(teacherId);
		}
		
		if (!StringUtils.isEmpty(courseId)){
			condition.setFromStr(",UserEntity user ");
			condition.setWhereStr("and user.userName =  entity.studentNo and user.courseId = ? ");
			param.add(courseId);
		}
		
		if (!StringUtils.isEmpty(classHeadIds)){
			classHeadIds = classHeadIds.substring(0,classHeadIds.length()-1);
			condition.setWhereStr("and entity.classHeadId in ("+classHeadIds+")");
		}
		condition.setOrderStr(" order by entity.updateTime desc");
		List<StudentEntity> list = null;
		if (StringUtils.isEmpty(page)){
			page ="1";
		}
		if (!StringUtils.isEmpty(limit)){
			Page pageVar = findPage(new Page<StudentEntity>(Integer.parseInt(page),Integer.parseInt(limit)), condition.toString(), param);
			list = pageVar.getData();
			baseForm.setTotalRows(pageVar.getTotalCount()+"");
		}else{
			list = find(condition.toString(), param);
		}
		if (list!=null && list.size()>0){
			//写入ClassHeadName
			for (StudentEntity studentEntity : list) {
				if(!StringUtils.isEmpty(studentEntity.getStudentActiveCode())){
					studentEntity.setActiveStatus(Constants.ACTIVE_CONSTANT);
				}else{
					studentEntity.setActiveStatus(Constants.INACTIVE_CONSTANT);
				}
				ClassHeadEntity classHeadEntity = classHeadDao.findClassHeadById(studentEntity.getClassHeadId());
				if(classHeadEntity!=null){
					studentEntity.setClassHeadName(classHeadEntity.getClassHeadName());
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 增加学生
	 */
	@Override
	public void createStudents(List<StudentEntity> list) {
		if (list!=null &&list.size()>0){
			for (StudentEntity studentEntity : list) {
				studentEntity.setId(null);
				studentEntity.setCreateTime(System.currentTimeMillis()+"");
				studentEntity.setUpdateTime(System.currentTimeMillis()+"");
				save(studentEntity);
			}
		}
	}
	/**
	 *通过课头删除学生 
	 */
	@Override
	public void deleteStudentsByClassHead(List<ClassHeadEntity> list) {
		StringBuilder stringBuilder = new StringBuilder("");
		for (ClassHeadEntity classHeadEntity : list) {
			String hql  = "select entity from StudentEntity entity where entity.classHeadId = ?";
			List<StudentEntity> students = find(hql,classHeadEntity.getId());
			if (students!=null && students.size()>0){
				for (StudentEntity studentEntity : students) {
					delete(studentEntity);
				}
			}
		}
	}
	/**
	 *修改学生 
	 */
	@Override
	public void updateStudents(List<StudentEntity> list) {
		if (list!=null &&list.size()>0){
			for (StudentEntity studentEntity : list) {
				studentEntity.setUpdateTime(System.currentTimeMillis()+"");
				save(studentEntity);
			}
		}
	}
	/**
	 *删除学生 
	 */
	@Override
	public void deleteStudents(List<StudentEntity> list) {
		if (list!=null &&list.size()>0){
			for (StudentEntity studentEntity : list) {
				delete(studentEntity);
			}
		}
	}
	/**
	 * 通过id
	 */
	@Override
	public StudentEntity findStudentById(String studentId) {
		return getById(studentId);
	}
	
	/**
	 * 通过学号与课程号
	 */
	@Override
	public StudentEntity findStudentByStudentNoAndCourseId(String studentNo,
			String courseId) {
		String hql  = "select entity from StudentEntity entity where entity.studentNo = ? and entity.courseId = ? ";
		List<StudentEntity> students = find(hql,studentNo,courseId);
		if (students!=null && students.size()>0){
			return students.get(0);
		}
		return null;
	}
	
	/**
	 * 更新学生信息
	 */
	@Override
	public void saveStudent(StudentEntity studentEntity) {
		save(studentEntity);
	}
	/**
	 * 通过classHeadId找学生list
	 */
	@Override
	public List<StudentEntity> findByClassHeadId(String classHeadId) {
		String hql  = "select entity from StudentEntity entity where entity.classHeadId = ?  ";
		List<StudentEntity> students = find(hql,classHeadId);
		if (students!=null&&students.size()>0){
			return students;
		}
		return null;
	}
	/**
	 * 当前课程下的所有学生
	 */
	@Override
	public List<StudentEntity> findByCourseId(String courseId) {
		String hql  = "select entity from StudentEntity entity where entity.courseId = ?  ";
		List<StudentEntity> students = find(hql,courseId);
		if (students!=null&&students.size()>0){
			return students;
		}
		return null;
	}
	/**
	 * 通过studentIds和ClassHeadid
	 */
	@Override
	public List<StudentEntity> findByClassHeadIdAndStudentIds(
			String classHeadId, String studentIds) {
		if (!StringUtils.isEmpty(studentIds)){
			studentIds = studentIds.substring(0, studentIds.length()-1);
		}
		String hql  = "select entity from StudentEntity entity " +
				"where entity.classHeadId = ? and entity.id in ("+studentIds+")";
		List<StudentEntity> students = find(hql,classHeadId);
		if (students!=null&&students.size()>0){
			return students;
		}
		return null;
	}
}
