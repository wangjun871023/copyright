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
import com.copyright.service.StudentService;
import com.copyright.service.TeacherService;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/student")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class StudentAction extends BaseAction {
	@Autowired
	private StudentService studentService;
	//课头信息
	private String classHeadId;
	private String classHeadIds;
    private String key;
    private String page;
    private String start;
    private String limit;
	
    private String studentCode;
    private String studentStatus;
    private String studentId;
    private String courseId;
    private String studentActiveCode;
    private String teacherId;
    
    
	/**
	 * 显示学生列表
	 */
	@Action(value = "findAllStudents", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String findAllStudents() {
		try {
			baseForm = studentService.findAllStudents(classHeadId,page,start,limit,key,studentCode,studentStatus,classHeadIds,teacherId,courseId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 增加学生
	 */
	@Action(value = "createStudents", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String createStudents() {
		try {
			baseForm = studentService.createStudents(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 更新学生
	 */
	@Action(value = "updateStudents", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String updateStudents() {
		try {
			baseForm = studentService.updateStudents(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 删除学生
	 */
	@Action(value = "deleteStudents", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String deleteStudents() {
		try {
			baseForm = studentService.deleteStudents(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	 
	/**
	 * 激活学生
	 */
	@Action(value = "activeStudent", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String activeStudent() {
		try {
			baseForm = studentService.activeStudent(courseId,studentId,studentActiveCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	
	public String getClassHeadId() {
		return classHeadId;
	}
	public void setClassHeadId(String classHeadId) {
		this.classHeadId = classHeadId;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public String getClassHeadIds() {
		return classHeadIds;
	}

	public void setClassHeadIds(String classHeadIds) {
		this.classHeadIds = classHeadIds;
	}

	 

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentActiveCode() {
		return studentActiveCode;
	}

	public void setStudentActiveCode(String studentActiveCode) {
		this.studentActiveCode = studentActiveCode;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	
}
