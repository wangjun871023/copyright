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
import com.copyright.service.UserService;
import com.copyright.util.StringUtils;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/manager/desktop/userlogin")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class LoginAction extends BaseAction {
	@Autowired
	private UserService userService;
	//登录页面属性
	private String username;
	private String password;
	private String coureValue;
	private String identityValue;
	private String randomCode;
	//找回密码页面性
	private String questionOfPassword;
	private String answerOfPassword;

	/**
	 * 登录Action 
	 */
	@Action(value = "login", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String login() {
		try {
			baseForm = userService.login(username,password,coureValue,identityValue,randomCode,getSession());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 密码找回Action
	 * @return
	 */
	@Action(value = "backPassword", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String backPassword() {
		try {
			baseForm = userService.backPassword(username,questionOfPassword,answerOfPassword);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	/**
	 * 密码修改Action
	 * @return
	 */
	@Action(value = "userPasswordConfirm", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String userPasswordConfirm() {
		try {
			baseForm = userService.modifyPassword(username,password);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return "success";
	}
	
	
	
	
	// getter setter
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

	public String getCoureValue() {
		return coureValue;
	}

	public void setCoureValue(String coureValue) {
		this.coureValue = coureValue;
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	public String getIdentityValue() {
		return identityValue;
	}
	
	public void setIdentityValue(String identityValue) {
		this.identityValue = identityValue;
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
}
