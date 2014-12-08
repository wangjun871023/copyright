package com.copyright.service.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.common.base.BaseForm;
import com.copyright.common.base.JsonTreeUtil;
import com.copyright.dao.ClassHeadDao;
import com.copyright.dao.CourseDao;
import com.copyright.dao.FileDao;
import com.copyright.dao.StudentDao;
import com.copyright.dao.TeacherDao;
import com.copyright.dao.TempDao;
import com.copyright.dao.UserDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.StudentEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.pojo.UserEntity;
import com.copyright.service.CourseHeadService;
import com.copyright.service.CourseService;
import com.copyright.service.FileService;
import com.copyright.util.ReadRequestPayloadUtil;
import com.copyright.util.StringUtils;

/**
 * 课头Service接口实现
 */
@Service
public class CourseHeadServiceImpl implements CourseHeadService {
	@Autowired
	private ClassHeadDao classHeadDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private TempDao tempDao;

	/**
	 * 当前教师下的所有课头信息Service
	 */
	@Override
	public BaseForm findCourseHeads(String teacherId,String classHeadStatus,String courseId) {
		BaseForm baseForm = new BaseForm();
		List<ClassHeadEntity> courses = classHeadDao
				.findCourseHeads(teacherId,classHeadStatus,courseId);
		if (courses != null && courses.size() > 0) {
			baseForm.setItems(courses);
		}
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 当前教师下的所有课头信息Service 返回树JSON
	 */
	@Override
	public BaseForm findCourseHeadsTree(String teacherId) {
		BaseForm baseForm = new BaseForm();
		List<ClassHeadEntity> courses = classHeadDao
				.findCourseHeads(teacherId,null,null);
		JSONArray jsonArray = new JSONArray();
		if (courses != null && courses.size() > 0) {
			for (ClassHeadEntity classHeadEntity : courses) {
				classHeadEntity.setLeaf(true);
				classHeadEntity.setText(classHeadEntity.getClassHeadName());
				jsonArray.add(classHeadEntity);
			}
		}
		baseForm.setJsonArray(jsonArray);
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	/**
	 * 创建课头且导入Excel学生表Service
	 */
	@Override
	public BaseForm createCourseHead(File courseHeadExcel,
			String courseHeadExcelContentType, String courseHeadExcelFileName,
			String classHeadName, String teacherId, String startDate,
			String endDate, HttpServletRequest request, String courseId,
			FileService fileService) throws Exception {
		BaseForm baseForm = new BaseForm();
		// 查检文件的大小和类型
		baseForm = fileService.checkUploadInfo(courseHeadExcelFileName,
				courseHeadExcel, Constants.FILE_EXCEL_TYPE,
				Constants.FILE_UPLOAD_SIZE);
		if (!baseForm.getSuccess()) {
			return baseForm;
		}
		// 写入课头表
		ClassHeadEntity classHeadEntity = createClassHead(classHeadName,
				teacherId, startDate, endDate, request);
		// 写入文件表
		FileEntity fileEntity = fileDao.createIntoFileTable(courseHeadExcel,
				Constants.FILE_EXCEL_TYPE, courseHeadExcelFileName, null,
				request);
		// 写入临时表
		tempDao.createIntoTempTable(courseHeadExcel, courseHeadExcelFileName,
				fileEntity.getId());
		// 检查临时表中的数据有效性
		tempDao.checkTempTable(fileEntity.getId(),courseId,classHeadEntity.getId());
		// 写入学生表
		studentDao.createIntoStudentTable(fileEntity.getId(),
				classHeadEntity.getId(), courseId);
		// 写入用户表
		userDao.createIntoUserTable(fileEntity.getId(),
				classHeadEntity.getId(), courseId);
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 增加课头
	 */
	private ClassHeadEntity createClassHead(String classHeadName,
			String teacherId, String startDate, String endDate,
			HttpServletRequest request) {
		UserEntity userEntity = (UserEntity) request.getSession().getAttribute(
				Constants.LOGIN_USER_ENTITY);
		if (userEntity != null) {
			ClassHeadEntity classHeadEntity = new ClassHeadEntity();
			classHeadEntity.setClassHeadName(classHeadName);
			classHeadEntity.setTeacherId(teacherId);
			classHeadEntity.setStartDate(startDate);
			classHeadEntity.setEndDate(endDate);
			classHeadEntity.setCreateTime(System.currentTimeMillis() + "");
			classHeadEntity.setUpdateTime(System.currentTimeMillis() + "");
			classHeadEntity.setCreateUserId(userEntity.getId());
			classHeadEntity.setClassHeadStatus(Constants.VALID_CONSTANT);
			classHeadDao.createCourseHead(classHeadEntity);
			return classHeadEntity;
		}
		return null;
	}

	/**
	 * 修改课头
	 * @throws Exception 
	 */
	@Override
	public BaseForm updateCourseHead(File courseHeadExcel,
			String courseHeadExcelContentType, String courseHeadExcelFileName,
			String classHeadName, String classHeadId, String startDate,
			String endDate, HttpServletRequest request, String courseId,
			FileService fileService) throws Exception {
		BaseForm baseForm = new BaseForm();
		// 更新课头表
		ClassHeadEntity classHeadEntity = updateClassHead(classHeadName,
				classHeadId, startDate, endDate, request);
		if (classHeadEntity==null){
			baseForm.setInfo("数据错误！");
			return baseForm;
		}
		if (!StringUtils.isEmpty(courseHeadExcelFileName)){
			// 查检文件的大小和类型
			baseForm = fileService.checkUploadInfo(courseHeadExcelFileName,
					courseHeadExcel, Constants.FILE_EXCEL_TYPE,
					Constants.FILE_UPLOAD_SIZE);
			if (!baseForm.getSuccess()) {
				return baseForm;
			}
			// 写入文件表
			FileEntity fileEntity = fileDao.createIntoFileTable(courseHeadExcel,
					Constants.FILE_EXCEL_TYPE, courseHeadExcelFileName, null,
					request);
			// 写入临时表
			tempDao.createIntoTempTable(courseHeadExcel, courseHeadExcelFileName,
					fileEntity.getId());
			// 检查临时表中的数据有效性
			tempDao.checkTempTable(fileEntity.getId(),courseId,classHeadEntity.getId());
			// 写入学生表
			studentDao.createIntoStudentTable(fileEntity.getId(),
					classHeadEntity.getId(), courseId);
			// 写入用户表
			userDao.createIntoUserTable(fileEntity.getId(),
					classHeadEntity.getId(), courseId);
		}
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	/**
	 * 更新课头信息
	 */
	private ClassHeadEntity updateClassHead(String classHeadName,
			String classHeadId, String startDate, String endDate,
			HttpServletRequest request) {
		UserEntity userEntity = (UserEntity) request.getSession().getAttribute(
				Constants.LOGIN_USER_ENTITY);
		if (userEntity != null) {
			ClassHeadEntity classHeadEntity = classHeadDao.findClassHeadById(classHeadId);
			if (classHeadEntity!=null){
				classHeadEntity.setClassHeadName(classHeadName);
				classHeadEntity.setStartDate(startDate);
				classHeadEntity.setEndDate(endDate);
				classHeadEntity.setUpdateTime(System.currentTimeMillis() + "");
				classHeadEntity.setCreateUserId(userEntity.getId());
				classHeadDao.saveClassHead(classHeadEntity);
			}
			return classHeadEntity;
		}
		return null;
	}

	/**
	 * 删除课头
	 */
	@Override
	public BaseForm deleteCourseHeads(HttpServletRequest request)
			throws Exception {
		BaseForm baseForm = new BaseForm();
		List<ClassHeadEntity> list = ReadRequestPayloadUtil
				.getEntityListByReadRequestPayload(request,
						ClassHeadEntity.class);
		classHeadDao.deleteCourseHeads(list);
		// 删除该课头下的所有学生和用户
		studentDao.deleteStudentsByClassHead(list);
		userDao.deleteStudentUsers(list);
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 当前课程的全部教师与课头--管理员学生用户管理
	 */
	@Override
	public BaseForm findTeacherCourseHeads(String courseId, String node) {
		BaseForm baseForm = new BaseForm();
		JSONArray jsonArray = new JSONArray();
		if (node.equals(Constants.TREE_ROOT_ID)) {
			List<TeacherEntity> teachers = teacherDao.findAllTeachers(courseId,
					null, null);
			if (teachers != null && teachers.size() > 0) {
				for (TeacherEntity teacherEntity : teachers) {
					TreeNode treeNode = new TreeNode();
					treeNode.setId(teacherEntity.getId());
					treeNode.setText(teacherEntity.getTeacherName());
					treeNode.setExpandable(true);
					treeNode.setParentId(node);
					jsonArray.add(treeNode);
				}
			}
		} else {
			List<ClassHeadEntity> classHeads = classHeadDao
					.findCourseHeads(node,null,null);
			if (classHeads != null && classHeads.size() > 0) {
				for (ClassHeadEntity classHeadEntity : classHeads) {
					classHeadEntity.setLeaf(true);
					classHeadEntity.setText(classHeadEntity.getClassHeadName());
					TeacherEntity teacher = teacherDao.findTeacherById(node);
					if (teacher != null) {
						classHeadEntity
								.setTeacherName(teacher.getTeacherName());
					}
					jsonArray.add(classHeadEntity);
				}
			}
		}
		baseForm.setJsonArray(jsonArray);
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	/**
	 * 更新课头信息
	 * @throws Exception 
	 */
	@Override
	public BaseForm updateCourseHeadInfo(HttpServletRequest request) throws Exception {
		BaseForm baseForm = new BaseForm();
		List<ClassHeadEntity> list = ReadRequestPayloadUtil.getEntityListByReadRequestPayload(request, ClassHeadEntity.class); 
		classHeadDao.updateCourseHeads(list);
		baseForm.setSuccess(true);
		return baseForm;
	}




}
