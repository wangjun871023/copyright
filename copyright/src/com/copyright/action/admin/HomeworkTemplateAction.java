package com.copyright.action.admin;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.copyright.common.base.BaseAction;
import com.copyright.service.FileService;
import com.copyright.service.HomeworkService;
import com.copyright.service.HomeworkTemplateService;
import com.copyright.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/homeworkTemplate")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class HomeworkTemplateAction extends BaseAction {
	@Autowired
	private HomeworkTemplateService homeworkTemplateService;
	@Autowired
	FileService fileService;

	// 作业信息
	private String homeworkName;
	private String homeworkContent;
	private String homeworkStatus;

	private String courseId;
	private String homeworkTemplateId;
	
	private File uploadFile;
	private String uploadFileFileName;

	/**
	 * 作业信息显示
	 */
	@Action(value = "findAllHomeworkTemplates", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findAllHomeworks() {
		try {
			baseForm = homeworkTemplateService.findAllHomeworkTemplates(courseId,homeworkStatus);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 新增作业
	 */
	@Action(value = "createHomeworkTemplate", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String createHomework() {
		try {
			baseForm = homeworkTemplateService.createHomeworkTemplate
					(homeworkName, homeworkContent, uploadFile
							, uploadFileFileName, courseId, getRequest(), fileService);
			HttpServletResponse response = getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String str = new ObjectMapper().writeValueAsString(baseForm);  
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 更新作业
	 */
	@Action(value = "updateHomeworkTemplate", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String updateHomeworkTemplate() {
		try {
			baseForm = homeworkTemplateService.updateHomeworkTemplate
					(homeworkTemplateId, homeworkName, homeworkContent, uploadFile, uploadFileFileName, getRequest(), fileService);
			HttpServletResponse response = getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String str = new ObjectMapper().writeValueAsString(baseForm);  
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 更新作业s
	 */
	@Action(value = "updateHomeworkTemplates", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String updateHomeworkTemplates() {
		try {
			baseForm = homeworkTemplateService.updateHomeworkTemplates(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}



	public String getHomeworkName() {
		return homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}

	public String getHomeworkContent() {
		return homeworkContent;
	}

	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getHomeworkTemplateId() {
		return homeworkTemplateId;
	}

	public void setHomeworkTemplateId(String homeworkTemplateId) {
		this.homeworkTemplateId = homeworkTemplateId;
	}

	public String getHomeworkStatus() {
		return homeworkStatus;
	}

	public void setHomeworkStatus(String homeworkStatus) {
		this.homeworkStatus = homeworkStatus;
	}
	
}
