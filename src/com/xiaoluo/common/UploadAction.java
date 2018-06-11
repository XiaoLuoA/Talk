package com.xiaoluo.common;

import java.io.File;
import java.io.FileOutputStream;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		FileOutputStream fos = new FileOutputStream(getSavePath());
		
		return super.execute();
	}
	
}
