package com.copyright.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.UserEntity;

public interface TempDao {
	/**
	 * 写入临时表
	 */
	void createIntoTempTable(File courseHeadExcel, String courseHeadExcelFileName, String fileId) throws  Exception;
	/**
	 * 检查临时表的完整性
	 */
	void checkTempTable(String fileId, String courseId, String classHeadId);
}
