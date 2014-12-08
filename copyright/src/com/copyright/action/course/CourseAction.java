package com.copyright.action.course;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.copyright.common.base.BaseAction;
import com.copyright.service.CourseService;

/**
 * 课程信息Action
 */
@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/course")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class CourseAction extends BaseAction {
	@Autowired
	private CourseService courseService;

	/**
	 * 所有课程
	 */
	@Action(value = "findAllCourses", results = { @Result(name = "success", type = "json", location = "", params = {
			"root", "baseForm" }) })
	public String findAllCourses() {
		try {
			baseForm = courseService.findAllCourses();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
}
