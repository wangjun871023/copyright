package com.copyright.service;

import javax.servlet.http.HttpSession;

import com.copyright.common.base.BaseForm;

public interface UserService {

	BaseForm login(String username, String password, String coureValue,
			String randomCode, String randomCode2, HttpSession httpSession);

	BaseForm backPassword(String username, String questionOfPassword,
			String answerOfPassword);

	BaseForm modifyPassword(String username, String password);

	BaseForm userNameCheck(String username);

	BaseForm passwordReset(String username);

	BaseForm passwordBackInfo(String username, String questionOfPassword,
			String answerOfPassword);

	BaseForm checkPassword(String username, String password);

	BaseForm passwordReset(String username, String password);

	
}
