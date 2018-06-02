package com.xiaoluo.utils;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class ResponseUtils {
	
 public static HttpServletResponse	getResponse(ActionContext ac){
	 HttpServletResponse response = (HttpServletResponse) ac.get(ServletActionContext.HTTP_RESPONSE);
     response.setContentType("text/html;charset=utf-8");
     return response;
 }
}
