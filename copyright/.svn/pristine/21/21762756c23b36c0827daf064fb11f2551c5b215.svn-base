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
@Namespace("/")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class TempAction extends BaseAction {
	
	@Action(value = "login", 
			results = { @Result(name = "success", type = "json",location="",params = {"root", "baseForm" }) })
	public String login() {
		UserEntity entity = new UserEntity();
		entity.setId("adminC");
		getSession().setAttribute(Constants.LOGIN_USER_ENTITY, entity);
		return "success";
	}
}
