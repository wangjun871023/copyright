package com.copyright.dao;

import java.util.List;

import com.copyright.common.base.BaseForm;
import com.copyright.pojo.ActiveCodeEntity;
import com.copyright.pojo.ActiveCodeExcelEntity;

public interface ActiveCodeDao {

	List<ActiveCodeEntity> findAllActiveCodes(String courseId,
			String activeStatus, String key, String page, String start, String limit, BaseForm baseForm);

	List<ActiveCodeExcelEntity> createActiveCodes(String courseId,
			String activeCodeCount);

	void checkActiveCodes(String courseId, String studentNo,
			String studentActiveCode, BaseForm baseForm);



}
