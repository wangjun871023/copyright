package com.copyright.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.common.base.BaseForm;
import com.copyright.common.base.JsonTreeUtil;
import com.copyright.dao.ActiveCodeDao;
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
import com.copyright.pojo.ActiveCodeEntity;
import com.copyright.pojo.ActiveCodeExcelEntity;
import com.copyright.pojo.ClassHeadEntity;
import com.copyright.pojo.FileEntity;
import com.copyright.pojo.HomeworkEntity;
import com.copyright.pojo.HomeworkStudentEntity;
import com.copyright.pojo.HomeworkTemplateEntity;
import com.copyright.pojo.TeacherEntity;
import com.copyright.pojo.TreeNode;
import com.copyright.pojo.UserEntity;
import com.copyright.service.ActiveCodeService;
import com.copyright.service.CourseHeadService;
import com.copyright.service.CourseService;
import com.copyright.service.FileService;
import com.copyright.service.HomeworkService;
import com.copyright.service.HomeworkTemplateService;
import com.copyright.util.ExcelUtil;
import com.copyright.util.ReadRequestPayloadUtil;
import com.copyright.util.StringUtils;

@Service
public class ActiveCodeServiceImpl implements ActiveCodeService {
	@Autowired
	ActiveCodeDao activeCodeDao;

	/**
	 * 显示激活码
	 */
	@Override
	public BaseForm findAllActiveCodes(String courseId, String activeStatus,String key, String page, String start, String limit) {
		BaseForm baseForm = new BaseForm();
		List<ActiveCodeEntity> items = activeCodeDao.findAllActiveCodes(
				courseId, activeStatus, key,  page,  start,  limit,baseForm);

		if (items != null && items.size() > 0) {
			baseForm.setItems(items);
		}
		baseForm.setSuccess(true);
		return baseForm;
	}

	/**
	 * 新增激活码
	 * 
	 * @throws IOException
	 */
	@Override
	public InputStream createActiveCodes(String courseId, String activeCodeCount)
			throws IOException {
		List<ActiveCodeExcelEntity> activeCodes = activeCodeDao
				.createActiveCodes(courseId, activeCodeCount);
		String[] headers = { "编号", "激活码", "课程名称" };
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ExcelUtil.exportExcel("激活码", headers, activeCodes, out);
		byte[] ba = out.toByteArray();
		InputStream inputStream = null;
		inputStream = new ByteArrayInputStream(ba);
		out.flush();
		out.close();
		return inputStream;
	}
}
