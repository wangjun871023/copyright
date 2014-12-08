package com.copyright.action.admin;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.copyright.common.base.BaseAction;
import com.copyright.service.ActiveCodeService;
import com.copyright.service.FileService;
import com.copyright.service.HomeworkService;
import com.copyright.service.HomeworkTemplateService;
import com.copyright.service.TeacherService;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/activeCode")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class ActiveCodeAction extends BaseAction {
	@Autowired
	private ActiveCodeService activeCodeService;
	@Autowired
	FileService fileService;

	private String courseId;
	private String activeCodeCount;
	private String activeStatus;
	private InputStream inputStream;
	
    private String key;
    private String page;
    private String start;
    private String limit;

	/**
	 * 激活码信息显示
	 */
	@Action(value = "findAllActiveCodes", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findAllHomeworks() {
		try {
			baseForm = activeCodeService.findAllActiveCodes(courseId,activeStatus,key,page,start,limit);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 新增激活码
	 */
	@Action(value = "createActiveCodes", results = { @Result(name = "success", type = "stream" ,params = {
			"contentType","application/vnd.ms-excel",
			"contentDisposition","attachment;filename=ActiveCode.xls",
			"bufferSize","1024",
			"inputName", "inputStream" }) })
	public String createHomework() {
		try {
			inputStream = activeCodeService.createActiveCodes(courseId,activeCodeCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

 
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getActiveCodeCount() {
		return activeCodeCount;
	}

	public void setActiveCodeCount(String activeCodeCount) {
		this.activeCodeCount = activeCodeCount;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
	
}
