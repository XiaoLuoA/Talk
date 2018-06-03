package com.xiaoluo.index;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.utils.ResponseUtils;

public class IndexAction extends ActionSupport{
	
	
	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getValueStack().set("allGroup",IndexService.me.allGroup);;
		return "index";
	}
	
	public String index(){
		//HttpServletResponse response = ResponseUtils.getResponse(ActionContext.getContext());
		Cookie c = new Cookie("name","aaa");
		c.setMaxAge(60*60*24*60);//周期是60天  
		 ServletActionContext.getResponse().addCookie(c);
		return "indexPage";
	}
	
	
}
