package com.copyright.dao;

import java.util.List;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkStudentEntity;
import com.copyright.pojo.UserEntity;

public interface HomeworkStudentDao {

	void updateHomeworkStudent(String id, String studentIds);

	void deleteHomeworkStudent(List<HomeworkEntity> list);

	List findAllHomeworkStudents(BaseForm baseForm, String homeworkId, String key, String page, String start, String limit, String studentHomeworkStatus, String teacherId);

	List findAllHomeworkStudentByStudentId(String studentId, String studentHomeworkStatus);

	void updateStudentHomework(String homeworkStudentId,
			String homeworkStudentContent, String fileId);


	HomeworkStudentEntity findById(String id);


	void saveHomeworkStudent(HomeworkStudentEntity entity);


	void createHomeworkStudents(List<HomeworkEntity> homeworks,String studentIds);


 
}
