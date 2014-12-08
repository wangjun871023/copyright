package com.copyright.action.common;

import java.io.ByteArrayInputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.copyright.common.base.BaseAction;
import com.copyright.i18n.Constants;
import com.copyright.util.SecurityCode;
import com.copyright.util.SecurityImage;

/**
 * 验证码生成Action
 */
@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/manager/desktop/common")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class RandomCodeAction extends BaseAction {
	// 图片流
	private ByteArrayInputStream imageStream;

	@Action(value = "getRandomCode", results = { @Result(name = "success", type = "stream", location = "", params = {
			"contentType", "image/jpeg", 
			"inputName", "imageStream" }) })
	public String getRandomCode() {
		try {
			// 获取默认难度和长度的验证码
			String securityCode = SecurityCode.getSecurityCode();
			// 将验证码变成图片
			imageStream = SecurityImage.getImageAsInputStream(securityCode);
			// 将验证码放入session中
			getSession().setAttribute(Constants.SECURITY_CODE, securityCode);
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		return "success";
	}

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}
}
