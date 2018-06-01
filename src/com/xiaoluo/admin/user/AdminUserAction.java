package com.xiaoluo.admin.user;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



import com.xiaoluo.model.User;




public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
	 private User user =new User();
	
	 ActionContext context = ActionContext.getContext();
     HttpServletRequest request = ServletActionContext.getRequest();
     
    
	@Override
	public User getModel() {
		
		return user;
	}


	
	
	
	
	public String listAllUser(){
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.allUser);
		return "admin";
		
	}
	
	public String deleteUser(){
		int id =Integer.parseInt(request.getParameter("id"));
		AdminUserService.me.deleteUser(id);
		return "admin";
	}
	
	public String addUser(){
		AdminUserService.me.addUser(user);
		return "listAllUser";
	}
	
	public String updateUser(){
	
		AdminUserService.me.updateUser(user);;
		
		return "listAllUser";
	}
	
	public String findUser(){
		int id =Integer.parseInt(request.getParameter("id"));
		User user=AdminUserService.me.findUser(id);
		System.out.println(user.getName());
		ActionContext.getContext().getValueStack().set("user",user);
		return "update";
	}

	
	
	

}
