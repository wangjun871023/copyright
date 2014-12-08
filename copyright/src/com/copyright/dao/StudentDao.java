package com.copyright.dao;

import java.util.List;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.StudentEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.UserEntity;

public interface StudentDao {

	void createIntoStudentTable(String fileId,String classHeadId, String courseId);

	List findAllStudents(BaseForm baseForm, String classHeadId, String page, String start, String limit, String key,
			String studentCode, String studentStatus, String classHeadIds, String teacherId, String courseId);

	void deleteStudentsByClassHead(List<ClassHeadEntity> list);

	void createStudents(List<StudentEntity> list);

	void updateStudents(List<StudentEntity> list);

	void deleteStudents(List<StudentEntity> list);

	StudentEntity findStudentById(String studentId);

	StudentEntity findStudentByStudentNoAndCourseId(String studentNo,String courseId);

	void saveStudent(StudentEntity studentEntity);

	List<StudentEntity> findByClassHeadId(String classHeadId);
	List<StudentEntity> findByCourseId(String courseId);

	List<StudentEntity> findByClassHeadIdAndStudentIds(String classHeadId,
			String studentIds);
}
