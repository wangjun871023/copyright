package com.copyright.action.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.copyright.common.base.BaseAction;
import com.copyright.dao.FileDao;
import com.copyright.i18n.Constants;
import com.copyright.pojo.FileEntity;
import com.copyright.util.SecurityCode;
import com.copyright.util.SecurityImage;

/**
 * 文件下载Action
 */
@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/manager/desktop/common")
@Results({ @Result(name = "error", location = "/error.jsp") })
public class DownloadFileAction extends BaseAction {
	// 文件流
	private InputStream  inputStream;
	private String fileId;
	private String fileName;
	@Autowired
	private FileDao fileDao;
	
	
	@Action(value = "downloadFile", results = { @Result(name = "success", type = "stream", location = "", params = {
			"contentType", "text/plain", "inputName", "inputStream","contentDisposition","attachment;filename='${fileName}'"}) })
	public String downloadFile() {
		return "success";
	}
	
	
	public InputStream getInputStream() throws Exception {   
		 String realpath = ServletActionContext.getServletContext().getRealPath(Constants.UPLOAD_PATH);
		 System.out.println(realpath);
		 FileEntity fileEntity = fileDao.getFileById(fileId);
		 String type="";
		 if(fileEntity!=null){
			 type = fileEntity.getFileType();
//			 fileName = new String(fileEntity.getFileName() .getBytes(), "ISO8859-1");
		 }
		 fileName = fileId+type;
		 return new java.io.FileInputStream(realpath+File.separator+fileId+type);
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
