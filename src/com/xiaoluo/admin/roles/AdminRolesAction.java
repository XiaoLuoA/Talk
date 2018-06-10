package com.xiaoluo.admin.roles;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.dao.RoleDao;
import com.xiaoluo.dao.RoleMenuDao;
import com.xiaoluo.model.Role;


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
	   
		AdminRolesService.me.addRole(role);
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
		 
		AdminRolesService.me.updateRole(role);
		ActionContext.getContext().getValueStack().set("allRole",AdminRolesService.me.findAllRole());
		return "admin";
	}
	
	public String findRole(){
		int id =Integer.parseInt(request.getParameter("id"));
		Role role=RoleDao.me.findRole(id);
		ActionContext.getContext().getValueStack().set("role",AdminRolesService.me.findRole(id));
		return "update";
	}
	 

}
