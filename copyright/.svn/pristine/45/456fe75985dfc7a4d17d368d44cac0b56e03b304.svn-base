package com.copyright.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copyright.common.base.BaseForm;

public interface ActiveCodeService {
	/**
	 * 所有的激活码
	 * @param limit 
	 * @param start 
	 * @param page 
	 * @param key 
	 * @param baseForm 
	 */
	BaseForm findAllActiveCodes(String courseId, String activeStatus, String key, String page, String start, String limit);
	/**
	 * 生成激活码
	 * @return 
	 * @throws IOException 
	 */
	InputStream createActiveCodes(String courseId, String activeCodeCount) throws IOException;

	
	
}
