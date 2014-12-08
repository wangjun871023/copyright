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
@Namespace("/manager/desktop/homeworkStudent")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class HomeworkStudentAction extends BaseAction {
	@Autowired
	private HomeworkService homeworkService;
	@Autowired
	FileService fileService;

	// 课头信息
	private String classHeadId;
	private String teacherId;
	private String startDate;
	private String endDate;

	// 作业信息
	private String homeworkName;
	private String homeworkContent;
	private File uploadFile;
	private String uploadFileFileName;
	private String studentIds;
	private String homeworkId;

	// 课程号
	private String courseId;

	private String studentId;
	private String studentHomeworkStatus;

	private String studentHomeworkId;
	private String studentHomeworkContent;
	private String studentFileId;

	/**
	 * 学生作业显示
	 */
	@Action(value = "findAllHomeworkStudent", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findAllHomeworkStudent() {
		try {
			baseForm = homeworkService.findAllHomeworkStudent(homeworkId,
					studentId, studentHomeworkStatus);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 修改学生作业
	 */
	@Action(value = "editStudentHomework", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String createStudentHomework() {
		try {
			baseForm = homeworkService.editStudentHomework(studentHomeworkId,
					uploadFile, uploadFileFileName, studentHomeworkContent,
					studentFileId, getRequest(), fileService);
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

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
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

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentHomeworkStatus() {
		return studentHomeworkStatus;
	}

	public void setStudentHomeworkStatus(String studentHomeworkStatus) {
		this.studentHomeworkStatus = studentHomeworkStatus;
	}

	public String getStudentHomeworkId() {
		return studentHomeworkId;
	}

	public void setStudentHomeworkId(String studentHomeworkId) {
		this.studentHomeworkId = studentHomeworkId;
	}

	public String getStudentHomeworkContent() {
		return studentHomeworkContent;
	}

	public void setStudentHomeworkContent(String studentHomeworkContent) {
		this.studentHomeworkContent = studentHomeworkContent;
	}

	public String getStudentFileId() {
		return studentFileId;
	}

	public void setStudentFileId(String studentFileId) {
		this.studentFileId = studentFileId;
	}

}
