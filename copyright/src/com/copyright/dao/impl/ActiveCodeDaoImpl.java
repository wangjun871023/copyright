package com.copyright.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copyright.common.base.BaseForm;
import com.copyright.common.core.Page;
import com.copyright.common.hibernate.HibernateDao;
import com.copyright.common.hibernate.HqlCondition;
import com.copyright.dao.ActiveCodeDao;
import com.copyright.dao.CourseDao;
import com.copyright.dao.StudentDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ActiveCodeEntity;
import com.copyright.pojo.ActiveCodeExcelEntity;
import com.copyright.pojo.CourseEntity;
import com.copyright.pojo.StudentEntity;
import com.copyright.util.Md5Util;
import com.copyright.util.StringUtils;

@Repository
public class ActiveCodeDaoImpl extends HibernateDao<ActiveCodeEntity, String> implements ActiveCodeDao{
	@Autowired
	StudentDao studentDao;
	@Autowired
	CourseDao courseDao;
	
	/**
	 * 激活码显示
	 */
	@Override
	public List<ActiveCodeEntity> findAllActiveCodes(String courseId,
			String activeStatus,String key, String page, String start, String limit,BaseForm baseForm) {
		List param = new ArrayList();
		HqlCondition condition = new HqlCondition();
		condition.setSelStr("select entity "); 
		condition.setFromStr("from ActiveCodeEntity entity ");
		condition.setWhereStr("where 1=1 ");
		if (!StringUtils.isEmpty(courseId)){
			condition.setWhereStr("and entity.courseId = ? ");
			param.add(courseId);
		}
		if (!StringUtils.isEmpty(activeStatus)){
			condition.setWhereStr("and entity.activeStatus = ? ");
			param.add(activeStatus);
		}
		if (!StringUtils.isEmpty(key)){
			
			condition.setFromStr(" , StudentEntity stu ");
			condition.setWhereStr("and stu.studentNo like ? and stu.id = entity.studentId ");
			param.add("%"+key+"%");
		}
		condition.setOrderStr(" order by entity.updateTime desc");
		
		if (StringUtils.isEmpty(page)){
			page ="1";
		}
		List<ActiveCodeEntity> list = null;
		if (!StringUtils.isEmpty(limit)){
			Page pageVar = findPage(new Page<ActiveCodeEntity>(Integer.parseInt(page),Integer.parseInt(limit)), condition.toString(), param);
			list = pageVar.getData();
			baseForm.setTotalRows(pageVar.getTotalCount()+"");
		}else{
			list = find(condition.toString(), param);
		}
		if (list != null && list.size() > 0) {
			for (ActiveCodeEntity activeCodeEntity : list) {
				StudentEntity studentEntity = studentDao.findStudentById(activeCodeEntity.getStudentId());
				if (studentEntity!=null){
					activeCodeEntity.setStudentName(studentEntity.getStudentName());
					activeCodeEntity.setStudentNo(studentEntity.getStudentNo());
					activeCodeEntity.setStudentActiveCode(studentEntity.getStudentActiveCode());
					activeCodeEntity.setStudentActiveTime(studentEntity.getStudentActiveTime());
				}
				CourseEntity courseEntity = courseDao.findCourseById(courseId);
				if (courseEntity!=null){
					activeCodeEntity.setCourseName(courseEntity.getCourseName());
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 新增
	 */
	@Override
	public List<ActiveCodeExcelEntity> createActiveCodes(String courseId,
			String activeCodeCount) {
		List<ActiveCodeExcelEntity> excelEntitys = new ArrayList<ActiveCodeExcelEntity>();
		if(!StringUtils.isEmpty(activeCodeCount)){
			int count = Integer.valueOf(activeCodeCount);
			String courseName = "";
			if(!StringUtils.isEmpty(courseId)){
				CourseEntity courseEntity = courseDao.findCourseById(courseId);
				if (courseEntity!=null){
					courseName = courseEntity.getCourseName();
				}
			}
			for (int i = 0; i < count; i++) {
				String activeCode = Md5Util.md5(System.currentTimeMillis()+i+"",16);
				ActiveCodeEntity activeCodeEntity = new ActiveCodeEntity();
				activeCodeEntity.setCourseId(courseId);
				activeCodeEntity.setActiveCode(Md5Util.md5(activeCode));
				activeCodeEntity.setCreateTime(System.currentTimeMillis()+"");
				activeCodeEntity.setUpdateTime(System.currentTimeMillis()+"");
				activeCodeEntity.setActiveStatus(Constants.INACTIVE_CONSTANT);
				save(activeCodeEntity);
				ActiveCodeExcelEntity activeCodeExcelEntity = new ActiveCodeExcelEntity(activeCodeEntity.getId(), activeCode, courseName);
				excelEntitys.add(activeCodeExcelEntity);
			}
		}
		return excelEntitys;
	}
	/**
	 * 校验一个激活码是否可用
	 */
	@Override
	public void checkActiveCodes(String courseId, String studentId,
			String studentActiveCode, BaseForm baseForm) {
		String hql = "select entity from ActiveCodeEntity entity where entity.activeStatus != ? and entity.activeCode = ?   ";
		List<ActiveCodeEntity> list = find(hql.toString(),Constants.ACTIVE_CONSTANT,Md5Util.md5(studentActiveCode));
		if(list!=null&&list.size()>0){
			//可以用
			ActiveCodeEntity entity = list.get(0);
			entity.setStudentId(studentId);
			entity.setCourseId(courseId);
			entity.setActiveStatus(Constants.ACTIVE_CONSTANT);
			save(entity);
			StudentEntity student = studentDao.findStudentById(studentId);
			student.setStudentActiveCode(studentActiveCode);
			java.text.DateFormat format1 = new java.text.SimpleDateFormat(  
		                "yyyy-MM-dd hh:mm:ss");
			String dateStr =format1.format(new Date(System.currentTimeMillis()));
			student.setStudentActiveTime(dateStr);
			studentDao.saveStudent(student);
			baseForm.setSuccess(true);
			baseForm.setInfo("激活成功！");
		}else{
			//不可以用
			baseForm.setSuccess(false);
			baseForm.setInfo("激活失败！");
		}
	}
}
