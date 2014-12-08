package com.copyright.dao;

import java.util.List;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkTemplateEntity;
import com.copyright.pojo.UserEntity;

public interface HomeworkTemplateDao {

	HomeworkTemplateEntity createHomeworkTemplate(String homeworkName,
			String homeworkContent, String courseId, String fileId);

	List findAllHomeworkTemplates(String courseId, String homeworkStatus);

	HomeworkTemplateEntity getHomeworkTemplateById(String homeworkTemplateId);

	void updateHomeworkTemplate(String homeworkTemplateId, String homeworkName,
			String homeworkContent, String fileId);

	void updateHomeworkTemplates(List<HomeworkTemplateEntity> list);

	HomeworkTemplateEntity findHomeworkTemplateById(String id);
 
}
