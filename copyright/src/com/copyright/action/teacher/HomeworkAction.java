package com.copyright.action.teacher;

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
import com.copyright.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/homework")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class HomeworkAction extends BaseAction {
	@Autowired
	private HomeworkService homeworkService;
	@Autowired
	FileService fileService;
	
	// 课头信息
	private String classHeadId;
	private String classHeadIds;
	private String startDate;
	private String endDate;

	// 作业信息
	private String homeworkName;
	private String homeworkStatus;
	private String homeworkContent;
	private File uploadFile;
	private String uploadFileFileName;
	private String studentIds;
	private String homeworkId;
	private String teacherId;
	

	/**
	 * 作业信息显示
	 */
	@Action(value = "findAllHomeworks", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findAllHomeworks() {
		try {
			baseForm = homeworkService.findAllHomeworks(classHeadId,classHeadIds,homeworkStatus,teacherId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 新增作业
	 */
	@Action(value = "createHomework", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String createHomework() {
		try {
			baseForm = homeworkService.createHomework(homeworkName,
					homeworkContent, uploadFile, studentIds, startDate,
					endDate, uploadFileFileName, classHeadIds, getRequest(),fileService);
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
	@Action(value = "updateHomework", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String updateHomework() {
		try {
			baseForm = homeworkService.updateHomework(homeworkId, homeworkName,
					homeworkContent, studentIds, startDate, endDate,
					uploadFile, uploadFileFileName, getRequest(),fileService);
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
	 * 删除作业
	 */
	@Action(value = "updateHomeworks", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String deleteHomeworks() {
		try {
			baseForm = homeworkService.updateHomeworks(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	
	
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getClassHeadId() {
		return classHeadId;
	}

	public void setClassHeadId(String classHeadId) {
		this.classHeadId = classHeadId;
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

	public String getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getClassHeadIds() {
		return classHeadIds;
	}

	public void setClassHeadIds(String classHeadIds) {
		this.classHeadIds = classHeadIds;
	}

	public String getHomeworkStatus() {
		return homeworkStatus;
	}

	public void setHomeworkStatus(String homeworkStatus) {
		this.homeworkStatus = homeworkStatus;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
}
