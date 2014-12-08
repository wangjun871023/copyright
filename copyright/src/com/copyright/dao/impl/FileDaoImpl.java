package com.copyright.dao.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copyright.common.hibernate.HibernateDao;
import com.copyright.dao.FileDao;
import com.copyright.dao.TeacherDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.UserEntity;
import com.copyright.service.FileService;
import com.copyright.util.FileUtils;

@Repository
public class FileDaoImpl extends HibernateDao<FileEntity, String> implements FileDao{
	@Autowired
	FileService fileService;

	@Override
	public FileEntity getFileById(String fileId) {
		return getById(fileId);
	}
	/**
	 * 写入文件表
	 */
	@Override
	public FileEntity createIntoFileTable(File file, String type,
			String courseHeadExcelFileName, String filePath,
			HttpServletRequest request) {
		UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constants.LOGIN_USER_ENTITY);
		if (userEntity!=null){
			String size = FileUtils.formetFileSize(file.length());
			FileEntity fileEntity = new FileEntity();
			fileEntity.setCreateTime(System.currentTimeMillis()+"");
			fileEntity.setUpdateTime(System.currentTimeMillis()+"");
			fileEntity.setFileName(courseHeadExcelFileName);
			fileEntity.setFileType(type);
			fileEntity.setFileSize(size);
			fileEntity.setFilePath(filePath);
			fileEntity.setCreateUserId(userEntity.getId());
			save(fileEntity);
			return fileEntity;
		}
		return null;
	}
	@Override
	public void deleteFile(FileEntity fileEntity) {
		delete(fileEntity);
	}
	@Override
	public void deleteFileById(String id) {
		FileEntity fileEntity = getFileById(id);
		if (fileEntity!=null){
			fileService.deleteUploadFile(fileEntity.getId());
			delete(fileEntity);
		}
	}
}
