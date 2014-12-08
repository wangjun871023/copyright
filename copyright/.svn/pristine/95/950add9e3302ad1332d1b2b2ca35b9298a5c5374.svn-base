package com.copyright.common.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

/**
 *Action基类
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 6450190612495045644L;
	protected Log logger = LogFactory.getLog(getClass());
	protected BaseForm baseForm;

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}

	public BaseForm getBaseForm() {
		return baseForm;
	}

	public void setBaseForm(BaseForm baseForm) {
		this.baseForm = baseForm;
	}
}