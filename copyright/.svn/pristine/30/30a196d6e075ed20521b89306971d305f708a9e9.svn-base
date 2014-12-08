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
import com.copyright.dao.HomeworkTemplateDao;
import com.copyright.dao.StudentDao;
import com.copyright.dao.TeacherDao;
import com.copyright.dao.TempDao;
import com.copyright.dao.UserDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkStudentEntity;
import com.copyright.pojo.HomeworkTemplateEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.pojo.UserEntity;
import com.copyright.service.CourseHeadService;
import com.copyright.service.CourseService;
import com.copyright.service.FileService;
import com.copyright.service.HomeworkService;
import com.copyright.service.HomeworkTemplateService;
import com.copyright.util.ReadRequestPayloadUtil;
import com.copyright.util.StringUtils;

@Service
public class HomeworkTemplateServiceImpl implements HomeworkTemplateService{
	@Autowired
	HomeworkTemplateDao homeworkTemplateDao;
	
	/**
	 * 显示所有的作业模板
	 */
	@Override
	public BaseForm findAllHomeworkTemplates(String courseId,String homeworkStatus) {
		BaseForm baseForm = new BaseForm();
		List homeworkTempaltes = homeworkTemplateDao.findAllHomeworkTemplates(courseId,homeworkStatus);
		baseForm.setItems(homeworkTempaltes);
		baseForm.setSuccess(true);
		return baseForm;
	}
	
	/**
	 * 新增作业模板
	 * @throws Exception 
	 */
	@Override
	public BaseForm createHomeworkTemplate(String homeworkName,
			String homeworkContent, File uploadFile, String uploadFileFileName,
			String courseId, HttpServletRequest request, FileService fileService) throws Exception {
		BaseForm baseForm = new BaseForm();
		String fileId = "";
		if (uploadFile!=null){
			//文件有效性校验
			baseForm = fileService.checkUploadInfo(uploadFileFileName, uploadFile, Constants.FILE_ALL_TYPE, Constants.FILE_UPLOAD_SIZE);
			if(!baseForm.getSuccess()){
				return baseForm;
			}
			//上传文件
			fileId = fileService.uploadFile(uploadFile,uploadFileFileName,null,request);
		}
		//添加作业
		HomeworkTemplateEntity homeworkTemplateEntity = 
				writeIntoHomeworkTemplateTable(homeworkName,homeworkContent,courseId,fileId);
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 写入作业数据表
	 */
	private HomeworkTemplateEntity writeIntoHomeworkTemplateTable(
			String homeworkName, String homeworkContent, String courseId,
			String fileId) {
		HomeworkTemplateEntity entity = 
				homeworkTemplateDao.createHomeworkTemplate(homeworkName,homeworkContent,courseId,fileId);
		return entity;
	}
	
	/**
	 * 修改作业数据表
	 */
	@Override
	public BaseForm updateHomeworkTemplate(String homeworkTemplateId,
			String homeworkName, String homeworkContent, File uploadFile,
			String uploadFileFileName, HttpServletRequest httpServletRequest,
			FileService fileService) throws Exception {
		BaseForm baseForm = new BaseForm();
		String fileId ="";
		if (uploadFile!=null){
			//文件有效性校验
			baseForm = fileService.checkUploadInfo(uploadFileFileName, uploadFile, Constants.FILE_ALL_TYPE, Constants.FILE_UPLOAD_SIZE);
			if(!baseForm.getSuccess()){
				return baseForm;
			}
			String oldFileId = homeworkTemplateDao.getHomeworkTemplateById(homeworkTemplateId).getFileId();
			fileId = fileService.uploadFile(uploadFile,uploadFileFileName,oldFileId,httpServletRequest);
		}
		//更新homework表
		homeworkTemplateDao.updateHomeworkTemplate(homeworkTemplateId,  homeworkName,
				 homeworkContent,fileId);
		baseForm.setSuccess(true);
		return baseForm;
	}
	/**
	 * 更新作业有效性
	 */
	@Override
	public BaseForm updateHomeworkTemplates(HttpServletRequest request)
			throws Exception {
		BaseForm baseForm = new BaseForm();
		List<HomeworkTemplateEntity> list = ReadRequestPayloadUtil.getEntityListByReadRequestPayload(request, HomeworkTemplateEntity.class); 
		homeworkTemplateDao.updateHomeworkTemplates(list);
		baseForm.setSuccess(true);
		return baseForm;
	}
}
