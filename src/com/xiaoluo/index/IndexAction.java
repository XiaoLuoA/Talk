package com.xiaoluo.index;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;

public class IndexAction extends ActionSupport{
	
	
	@Override
	public String execute() throws Exception {
		
		ActionContext.getContext().getValueStack().set("allGroup",IndexService.me.allGroup);;
		return "index";
	}
	
	public void getAllMsg(){
		User loginUser = (User)ActionContext.getContext().getSession().get("");
		Map<Integer, List<UserMess>> allMsg = IndexService.me.getAllMsg(loginUser);
		Ret ret = Ret.ok();
		ret.set("allMsg",allMsg);
	}
	
	public String index(){
		//HttpServletResponse response = ResponseUtils.getResponse(ActionContext.getContext());
		Cookie c = new Cookie("name","aaa");
		c.setMaxAge(60*60*24*60);//周期是60天  
		 ServletActionContext.getResponse().addCookie(c);
		return "indexPage";
	}
	
	
}
