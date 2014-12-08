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
import com.copyright.service.CourseHeadService;
import com.copyright.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 课头Action
 */
@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/courseHead")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class CourseHeadAction extends BaseAction {
	@Autowired
	private CourseHeadService courseHeadService;
	@Autowired
	private FileService fileService;

	// 课头信息
	private String teacherId;
	private String classHeadName;
	private String startDate;
	private String endDate;
	private String courseId;
	private String classHeadId;
	private String classHeadStatus;
	// 上传文件
	private File courseHeadExcel;
	private String courseHeadExcelContentType;
	private String courseHeadExcelFileName;
	// 树
	private String node;

	/**
	 * 当前教师下的所有课头Action
	 */
	@Action(value = "findCourseHeadsTree", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findCourseHeadsTree() {
		try {
			baseForm = courseHeadService.findCourseHeadsTree(teacherId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 所有课头Action
	 */
	@Action(value = "findCourseHeads", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findCourseHeads() {
		try {
			baseForm = courseHeadService.findCourseHeads(teacherId,classHeadStatus,courseId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 创建课头且导入Excel学生表Action
	 */
	@Action(value = "createCourseHead", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String uploadCourseHeadExcel() {
		try {
			baseForm = courseHeadService.createCourseHead(courseHeadExcel,
					courseHeadExcelContentType, courseHeadExcelFileName,
					classHeadName, teacherId, startDate, endDate, getRequest(),
					courseId, fileService);
		
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
	 * 修改课头
	 */
	@Action(value = "updateCourseHead", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm"}) })
	public String updateCourseHeads() {
		try {
			baseForm = courseHeadService.updateCourseHead(courseHeadExcel,
					courseHeadExcelContentType, courseHeadExcelFileName,
					classHeadName, classHeadId, startDate, endDate, getRequest(),
					courseId, fileService);
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
	 * 修改课头信息
	 */
	@Action(value = "updateCourseHeadInfo", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String updateCourseHeadInfo() {
		try {
			baseForm = courseHeadService.updateCourseHeadInfo(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 删除课头
	 */
	@Action(value = "deleteCourseHeads", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String deleteCourseHeads() {
		try {
			baseForm = courseHeadService.deleteCourseHeads(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 当前课程的全部教师与课头--管理员学生用户管理
	 */
	@Action(value = "findTeacherCourseHeads", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findTeacherCourseHeads() {
		try {
			baseForm = courseHeadService.findTeacherCourseHeads(courseId, node);
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

	public String getClassHeadName() {
		return classHeadName;
	}

	public void setClassHeadName(String classHeadName) {
		this.classHeadName = classHeadName;
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

	public File getCourseHeadExcel() {
		return courseHeadExcel;
	}

	public void setCourseHeadExcel(File courseHeadExcel) {
		this.courseHeadExcel = courseHeadExcel;
	}

	public String getCourseHeadExcelContentType() {
		return courseHeadExcelContentType;
	}

	public void setCourseHeadExcelContentType(String courseHeadExcelContentType) {
		this.courseHeadExcelContentType = courseHeadExcelContentType;
	}

	public String getCourseHeadExcelFileName() {
		return courseHeadExcelFileName;
	}

	public void setCourseHeadExcelFileName(String courseHeadExcelFileName) {
		this.courseHeadExcelFileName = courseHeadExcelFileName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getClassHeadId() {
		return classHeadId;
	}

	public void setClassHeadId(String classHeadId) {
		this.classHeadId = classHeadId;
	}

	public String getClassHeadStatus() {
		return classHeadStatus;
	}

	public void setClassHeadStatus(String classHeadStatus) {
		this.classHeadStatus = classHeadStatus;
	}
}
