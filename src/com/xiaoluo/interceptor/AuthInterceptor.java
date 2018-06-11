package com.xiaoluo.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.xiaoluo.model.User;



public class AuthInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation inv) throws Exception {
		ActionContext action = inv.getInvocationContext();
		String uri = ServletActionContext.getRequest().getRequestURI().substring(6);
		System.out.println(uri);
		User user = (User) action.getSession().get("user");
		if(user==null){
			if(uri.startsWith("user")||uri.startsWith("index")){
				inv.invoke();
			}else{
				action.getValueStack().set("msg", "你没有这个页面的访问权限！");
				return "noAuth";
			}
		}else{
			String roles = user.getRoles();
			if(roles.length()<1&&uri.startsWith("a")){
				action.getValueStack().set("msg", "你没有这个页面的访问权限！");
				return "noAuth";
			}else{
				inv.invoke();
			}
		}
		return null;
		
	}
}
