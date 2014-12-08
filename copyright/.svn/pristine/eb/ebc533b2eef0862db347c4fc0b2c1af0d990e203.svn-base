package com.copyright.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.StudentEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.UserEntity;

public interface UserDao {

	UserEntity findUserBy4Info(String username, String password,
			String coureValue, String identityValue);

	List<UserEntity> findUsersBy3Info(String username, String questionOfPassword,
			String answerOfPassword);

	UserEntity findUserByUserName(String username);

	void save(UserEntity user);

	void createIntoUserTable(String fileId, String classHeadId, String courseId);
	void createUsersByTeachers(List<TeacherEntity> list, HttpServletRequest request);
	void createUsersByStudents(List<StudentEntity> list);

	
	void updateResetPassword(UserEntity user);

	void deleteTeacherUsers(List<TeacherEntity> list);
	void deleteStudentUsers(List<ClassHeadEntity> list);
	void deleteUsersByStudents(List<StudentEntity> list);

	void createPassWordBackInfo(String username, String questionOfPassword,
			String answerOfPassword);

	List<UserEntity> findUserByUsernameAndPassword(String username, String password);

	void updateUser(UserEntity user);

}
