package com.copyright.action.teacher;

import java.io.File;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.copyright.common.base.BaseAction;
import com.copyright.service.CourseHeadService;
import com.copyright.service.HomeworkCheckService;
import com.copyright.service.TeacherService;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/homeworkCheck")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class HomeworkCheckAction extends BaseAction {
	@Autowired
	private HomeworkCheckService homeworkCheckService;

	private String teacherId;

	// 树
	private String node;

	private String homeworkId;
	private String studentHomeworkStatus;

	private String key;
	private String page;
	private String start;
	private String limit;

	private String studentHomeworkId;
	private String studentScore;

	/**
	 * 当前作业下的信息显示
	 */
	@Action(value = "findAllHomeworkToCheck", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findAllHomeworkToCheck() {
		try {
			baseForm = homeworkCheckService.findAllHomeworkToCheck(homeworkId,
					key, page, start, limit, studentHomeworkStatus,teacherId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}

	/**
	 * 当前教师所有作业
	 */
	@Action(value = "findCourseHeadHomework", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findCourseHeadHomework() {
		try {
			baseForm = homeworkCheckService.findCourseHeadHomework(teacherId,
					node);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	/**
	 * 提交学生分数
	 */
	@Action(value = "updateHomeworkStudentScore", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String updateHomeworkStudentScore() {
		try {
			baseForm = homeworkCheckService.updateHomeworkStudentScore(
					studentHomeworkId, studentScore);
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

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
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

	public String getStudentScore() {
		return studentScore;
	}

	public void setStudentScore(String studentScore) {
		this.studentScore = studentScore;
	}
}
