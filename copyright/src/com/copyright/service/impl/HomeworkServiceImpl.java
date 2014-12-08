package com.copyright.service.impl;

import java.io.File;
import java.io.IOException;
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
import com.copyright.dao.HomeworkDao;
import com.copyright.dao.HomeworkStudentDao;
import com.copyright.dao.StudentDao;
import com.copyright.dao.TeacherDao;
import com.copyright.dao.TempDao;
import com.copyright.dao.UserDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkStudentEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.pojo.UserEntity;
import com.copyright.service.CourseHeadService;
import com.copyright.service.CourseService;
import com.copyright.service.FileService;
import com.copyright.service.HomeworkService;
import com.copyright.util.ReadRequestPayloadUtil;
import com.copyright.util.StringUtils;

@Service
public class HomeworkServiceImpl implements HomeworkService{
	@Autowired
	HomeworkDao homeworkDao;
	@Autowired
	HomeworkStudentDao homeworkStudentDao;

	
	 /**
     * 作业信息显示
     */
	@Override
	public BaseForm findAllHomeworks(String classHeadId,String classHeadIds, String homeworkStatus,String teacherId) {
		BaseForm baseForm = new BaseForm();
		List homeworks = homeworkDao.findAllHomeworks(classHeadId,classHeadIds,homeworkStatus,teacherId);
		baseForm.setItems(homeworks);
		baseForm.setSuccess(true);
		return baseForm;
	}
	/**
	 * 新增作业
	 * @throws Exception 
	 */
	@Override
	public BaseForm createHomework(String homeworkName, String homeworkContent,
		 File uploadFile, String studentIds,String startDate,String endDate,
		 String uploadFileFileName,String classHeadIds,HttpServletRequest httpServletRequest, FileService fileService) throws Exception {
		BaseForm baseForm = new BaseForm();
		String fileId = "";
		if (uploadFile!=null){
			//文件有效性校验
			baseForm = fileService.checkUploadInfo(uploadFileFileName, uploadFile, Constants.FILE_ALL_TYPE, Constants.FILE_UPLOAD_SIZE);
			if(!baseForm.getSuccess()){
				return baseForm;
			}
			//上传文件
			fileId = fileService.uploadFile(uploadFile,uploadFileFileName,null,httpServletRequest);
		}
		//添加作业
		List<HomeworkEntity> homeworks = homeworkDao.createHomework(homeworkName,homeworkContent,startDate,endDate,classHeadIds,fileId);;
		//添加学生作业关系表
		homeworkStudentDao.createHomeworkStudents(homeworks,studentIds);
		
		
		
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	/**
	 *修改homework 
	 */
	@Override
	public BaseForm updateHomework(String homeworkId, String homeworkName,
			String homeworkContent, String studentIds, String startDate,
			String endDate, File uploadFile, String uploadFileFileName, HttpServletRequest httpServletRequest, FileService fileService) throws Exception {
		BaseForm baseForm = new BaseForm();
		String fileId ="";
		if (uploadFile!=null){
			//文件有效性校验
			baseForm = fileService.checkUploadInfo(uploadFileFileName, uploadFile, Constants.FILE_ALL_TYPE, Constants.FILE_UPLOAD_SIZE);
			if(!baseForm.getSuccess()){
				return baseForm;
			}
			String oldFileId = homeworkDao.getHomeworkById(homeworkId).getFileId();
			fileId = fileService.uploadFile(uploadFile,uploadFileFileName,oldFileId,httpServletRequest);
		}
		//上传文件
		String studentNum = "";
		if (!StringUtils.isEmpty(studentIds)){
			String[] ids = studentIds.split(",");
			studentNum = ids.length+"";
		}
		//更新homework表
		homeworkDao.updateHomework(homeworkId,  homeworkName,
				 homeworkContent,  startDate,
				 endDate,studentNum,fileId);
		//更新homework_student表
		homeworkStudentDao.updateHomeworkStudent(homeworkId,studentIds);
		baseForm.setSuccess(true);
		return baseForm;
	}
	/**
	 * 更新homeworks
	 */
	@Override
	public BaseForm updateHomeworks(HttpServletRequest request) throws Exception {
		BaseForm baseForm = new BaseForm();
		List<HomeworkEntity> list = ReadRequestPayloadUtil.getEntityListByReadRequestPayload(request, HomeworkEntity.class); 
		homeworkDao.updateHomeworks(list);
		
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	/**
	 * 当前作业学生
	 */
	@Override
	public BaseForm findAllHomeworkStudent(String homeworkId,String studentId,String studentHomeworkStatus) {
		BaseForm baseForm = new BaseForm();
		List items = null ;
		if (!StringUtils.isEmpty(homeworkId)){
			items = homeworkStudentDao.findAllHomeworkStudents(baseForm,homeworkId,null, null, null, null,null,null);
		}
		if (!StringUtils.isEmpty(studentId)){
			items = homeworkStudentDao.findAllHomeworkStudentByStudentId(studentId,studentHomeworkStatus);
		}
		baseForm.setItems(items);
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	/**
	 * 修改学生作业
	 */
	@Override
	public BaseForm editStudentHomework(String homeworkStudentId,
			File uploadFile, String uploadFileFileName,
			String homeworkStudentContent,String fileId,HttpServletRequest httpServletRequest, FileService fileService) throws Exception {
		BaseForm baseForm = new BaseForm();
		if(uploadFile!=null){
			//上传文件
			baseForm = fileService.checkUploadInfo(uploadFileFileName, uploadFile, Constants.FILE_ALL_TYPE, Constants.FILE_UPLOAD_SIZE);
			if(!baseForm.getSuccess()){
				return baseForm;
			}
			String newFileId = fileService.uploadFile(uploadFile,uploadFileFileName,fileId,httpServletRequest);
			//添加学生作业关系表
			homeworkStudentDao.updateStudentHomework(homeworkStudentId,homeworkStudentContent,newFileId);
		}else{
			//添加学生作业关系表
			homeworkStudentDao.updateStudentHomework(homeworkStudentId,homeworkStudentContent,"");
		}
		baseForm.setSuccess(true);
		return baseForm;
	}
	
}
