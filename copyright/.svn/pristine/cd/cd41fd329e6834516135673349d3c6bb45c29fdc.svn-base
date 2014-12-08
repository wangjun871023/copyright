package com.copyright.dao;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.UserEntity;

public interface FileDao {

	FileEntity createIntoFileTable(File courseHeadExcel, String string,
			String courseHeadExcelFileName,String filePath, HttpServletRequest request);

	FileEntity getFileById(String fileId);

	void save(FileEntity file);

	void deleteFile(FileEntity fileEntity);

	void deleteFileById(String id);
}
