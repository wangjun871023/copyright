package com.copyright.action.teacher;

import java.io.File;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.copyright.common.base.BaseAction;
import com.copyright.service.TeacherService;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/teacher")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class TeacherAction extends BaseAction {
	@Autowired
	private TeacherService teacherService;
    
    //课程号
    private String courseId;
    private String teacherCode;
    private String validStatus;
    
	/**
	 * 当前课程的全部教师Action
	 * @return
	 */
	@Action(value = "findAllTeachers", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String findAllTeachers() {
		try {
			baseForm = teacherService.findAllTeachers(courseId,teacherCode,validStatus);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	/**
	 * 添加课程的教师Action
	 * @return
	 */
	@Action(value = "createTeachers", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String createTeachers() {
		try {
			baseForm = teacherService.createTeachers(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	/**
	 * 修改课程的教师Action
	 * @return
	 */
	@Action(value = "updateTeachers", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String updateTeachers() {
		try {
			baseForm = teacherService.updateTeachers(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 教师树的显示Action
	 */
	@Action(value = "findTeacherTree", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findTeacherCourseHeads() {
		try {
			baseForm = teacherService.findTeacherTree(courseId);
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
	public String getTeacherCode() {
		return teacherCode;
	}
	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
}
