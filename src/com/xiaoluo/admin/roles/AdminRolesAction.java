package com.xiaoluo.admin.roles;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.admin.user.AdminUserService;
import com.xiaoluo.model.Role;
import com.xiaoluo.model.User;

public class AdminRolesAction extends ActionSupport implements ModelDriven<Role>{
	 private Role role =new Role();
		
	 ActionContext context = ActionContext.getContext();
     HttpServletRequest request = ServletActionContext.getRequest();
	

	@Override
	public Role getModel() {
		
		return role;
	}
	
	
	public String listAllRole(){
		ActionContext.getContext().getValueStack().set("allRole",AdminRolesService.me.findAllRole());
		return "admin";
		
	}
	public String addRole(){
	    String menu = request.getParameter("menu");
	    String [] s= menu.split(",");
	    int [] a = null;
	    for(int i=0;i<s.length;i++){
	    	a[i]=Integer.parseInt(s[i]);
	    }
		AdminRolesService.me.addRole(role, a);
		ActionContext.getContext().getValueStack().set("allRole",AdminRolesService.me.findAllRole());
		return "admin";
	}
	
	public String deleteRole(){
		 int id= Integer.parseInt(request.getParameter("id"));
		 AdminRolesService.me.deleteRole(id);
		 ActionContext.getContext().getValueStack().set("allRole",AdminRolesService.me.findAllRole());
		 return "admin";
		
	}
	
	public String updateRole(){
		 int roleid= Integer.parseInt(request.getParameter("roleid"));
		 int menuid= Integer.parseInt(request.getParameter("menuid"));
		 int updateMenuId= Integer.parseInt(request.getParameter("updateMenuId"));
		AdminRolesService.me.updateRole(roleid, menuid, updateMenuId);
		ActionContext.getContext().getValueStack().set("allRole",AdminRolesService.me.findAllRole());
		return "admin";
	}
	
	public String findRole(){
		int id =Integer.parseInt(request.getParameter("id"));
		
		ActionContext.getContext().getValueStack().set("Role",AdminRolesService.me.findRole(id));
		return "admin";
	}
	 

}
