package com.copyright.dao;

import java.util.List;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.pojo.UserEntity;

/**
 * 课头Dao接口
 */
public interface ClassHeadDao {
	/**
	 * 当前教师下的所有课头信息Dao
	 * @param classHeadStatus 
	 * @param courseId 
	 */
	List findCourseHeads(String teacherId, String classHeadStatus, String courseId);

	/**
	 * 添加课头通过 List
	 */
	void createCourseHeads(List<ClassHeadEntity> list) throws Exception;

	/**
	 * 修改课头
	 */
	void updateCourseHeads(List<ClassHeadEntity> list);

	/**
	 * 删除课头
	 */
	void deleteCourseHeads(List<ClassHeadEntity> list);

	/**
	 * 添加课头
	 */
	void createCourseHead(ClassHeadEntity classHeadEntity);

	/**
	 * 通过id查找课头
	 */
	ClassHeadEntity findClassHeadById(String clazzHeadId);
	/**
	 * 更新课头信息
	 */
	void saveClassHead(ClassHeadEntity classHeadEntity);
}
