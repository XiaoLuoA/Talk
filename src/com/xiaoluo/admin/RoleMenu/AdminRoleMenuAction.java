package com.xiaoluo.admin.RoleMenu;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.admin.menu.AdminMenuService;
import com.xiaoluo.admin.user.AdminUserService;
import com.xiaoluo.model.Menu;
import com.xiaoluo.model.RoleMenu;

public class AdminRoleMenuAction extends ActionSupport implements ModelDriven<RoleMenu>{
	 private RoleMenu rolemenu =new RoleMenu();
		
	 ActionContext context = ActionContext.getContext();
	 
    HttpServletRequest request = ServletActionContext.getRequest();
    
	

	@Override
	public RoleMenu getModel() {
		
		return rolemenu;
	} 
	
	public String listAllRoleMenu(){
		
		ActionContext.getContext().getValueStack().set("allRoleMenu",AdminRoleMenuService.me.listAllRoleMenu());
		return "admin";
	}
	
	
	
	public String findRuleMenu(){
		int id =Integer.parseInt(request.getParameter("id"));
		RoleMenu rolemenu=AdminRoleMenuService.me.findRoleMenu(id);		
		ActionContext.getContext().getValueStack().set("rolemenu",rolemenu);	
		return "update";
	}
	
	
	
	public String deleteRuleMenu(){
		int id =Integer.parseInt(request.getParameter("id"));
		AdminRoleMenuService.me.deleteRoleMenu(id);
		ActionContext.getContext().getValueStack().set("allRoleMenu",AdminRoleMenuService.me.listAllRoleMenu());
		return "admin";
	}
	
	
	public String updateRuleMenu(){
		AdminRoleMenuService.me.updateRoleMenu(rolemenu);;
		ActionContext.getContext().getValueStack().set("allRoleMenu",AdminRoleMenuService.me.listAllRoleMenu());
		return "admin";
	}
	
	
	
	
	
	
	

}
