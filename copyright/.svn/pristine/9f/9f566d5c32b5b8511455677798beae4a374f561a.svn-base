package com.copyright.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.copyright.common.base.BaseAction;
import com.copyright.i18n.Constants;
import com.copyright.pojo.UserEntity;
import com.copyright.service.UserService;
import com.copyright.util.StringUtils;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/user")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class UserEntityAction extends BaseAction {
	@Autowired
	private UserService userService;
	//登录页面属性
	private String username;
	private String password;
	//code = username
	private String teacherCode;
	private String studentCode;
	//密码信息
	private String questionOfPassword;
	private String answerOfPassword;
	private String passwordOrig;
	

	/**
	 * 用户名是否可用检查Action
	 * @return
	 */
	@Action(value = "userNameCheck", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String userNameCheck() {
		try {
			String teacherCode = getTeacherCode();
			String studentCode = getStudentCode();
			if (!StringUtils.isEmpty(teacherCode)){
				username = teacherCode;
			}
			if (!StringUtils.isEmpty(studentCode)){
				username = studentCode;
			}
			baseForm = userService.userNameCheck(username);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 密码重置Action
	 * @return
	 */
	@Action(value = "passwordReset", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String passwordReset() {
		try {
			if (!StringUtils.isEmpty(studentCode)){
				username = studentCode;
			}
			if (!StringUtils.isEmpty(teacherCode)){
				username = teacherCode;
			}
			baseForm = userService.passwordReset(username);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 密码重置信息Action
	 * @return
	 */
	@Action(value = "passwordBackInfo", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String passwordBackInfo() {
		try {
			baseForm = userService.passwordBackInfo(username,questionOfPassword,answerOfPassword);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	
	
	/**
	 * 个人密码重置Action
	 * @return
	 */
	@Action(value = "passwordResetPerson", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String passwordResetPerson() {
		try {
			baseForm = userService.checkPassword(username,passwordOrig);
			if(baseForm.getSuccess()){
				baseForm = userService.passwordReset(username,password);
				baseForm.setInfo("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getQuestionOfPassword() {
		return questionOfPassword;
	}

	public void setQuestionOfPassword(String questionOfPassword) {
		this.questionOfPassword = questionOfPassword;
	}

	public String getAnswerOfPassword() {
		return answerOfPassword;
	}

	public void setAnswerOfPassword(String answerOfPassword) {
		this.answerOfPassword = answerOfPassword;
	}

	public String getPasswordOrig() {
		return passwordOrig;
	}

	public void setPasswordOrig(String passwordOrig) {
		this.passwordOrig = passwordOrig;
	}

	
}
