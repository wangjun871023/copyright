package com.copyright.dao;

import java.util.List;

import com.copyright.pojo.HomeworkEntity;


public interface HomeworkDao {

	List<HomeworkEntity> createHomework(String homeworkName, String homeworkContent,
			 String startDate, String endDate, String classHeadIds, String fileId);

	HomeworkEntity getHomeworkById(String id);

	void updateHomework(String id, String homeworkName, String homeworkContent,
			String startDate, String endDate, String studentNum, String fileId);

	void deleteHomeworks(List<HomeworkEntity> list);

	void saveHomework(HomeworkEntity homeworkEntity);

	List findAllHomeworks(String classHeadId, String classHeadIds, String homeworkStatus, String teacherId);

	void updateHomeworks(List<HomeworkEntity> list);
}
