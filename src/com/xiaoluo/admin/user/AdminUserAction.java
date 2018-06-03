package com.xiaoluo.admin.user;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.tio.utils.json.Json;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.index.IndexService;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;




public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
	 private User user =new User();
	
	 ActionContext context = ActionContext.getContext();
     HttpServletRequest request = ServletActionContext.getRequest();
     
    
	@Override
	public User getModel() {
		return user;
	}

	
	/*@Override
	public String execute() throws Exception {
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.allUser);
		return "admin";
	}*/
	
	
	public String listAllUser(){
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		return "admin";
		
	}
	
	public String deleteUser(){
		int id =Integer.parseInt(request.getParameter("id"));
		AdminUserService.me.deleteUser(id);
		return "delete";
	}
	
	public void testAjax(){
		
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		Ret ret = Ret.ok();
		
		ret.set("msg","success");
		
		ret.setFail();
		
		try {
			
			response.getWriter().print(Json.toJson(ret));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String addUser(){
		AdminUserService.me.addUser(user);
		return "add";
	}
	
	public String updateUser(){
	
		AdminUserService.me.updateUser(user);;
		
		return "u   ";
	}
	
	public String findUser(){
		int id =Integer.parseInt(request.getParameter("id"));
		User user=AdminUserService.me.findUser(id);
		ActionContext.getContext().getValueStack().set("user",user);	
		return "update";
	}






	public User getUser() {
		return user;
	}






	public void setUser(User user) {
		this.user = user;
	}

	
	
	

}
