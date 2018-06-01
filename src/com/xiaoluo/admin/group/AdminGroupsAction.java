package com.xiaoluo.admin.group;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.admin.user.AdminUserService;
import com.xiaoluo.model.Groups;
import com.xiaoluo.model.User;



public class AdminGroupsAction extends ActionSupport{
	
	
    private Groups groups = new Groups() ;
    ActionContext context = ActionContext.getContext();
    HttpServletRequest request = ServletActionContext.getRequest();	
	
    public Groups getModel() {
		
		return groups;
	}
    
    public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public String listAllGroups(){
		ActionContext.getContext().getValueStack().set("allGroups",AdminGroupsService.me.allGroups);
		return "admin";
		
	}
    
    public String deleteGroups(){
    	int id =Integer.parseInt(request.getParameter("id"));
		AdminGroupsService.me.deleteGroups(id);
		return "admin";
	}
	
	public String addGroups(Groups Groups){
		AdminGroupsService.me.addGroups(Groups);
		return "admin";
	}
	
	public String updateGroups(Groups Groups){
		AdminGroupsService.me.updateGroups(Groups);;
		
		return "admin";
	}
	
	public String findGroups(){
		int id =Integer.parseInt(request.getParameter("id"));
		Groups groups=AdminGroupsService.me.findGroups(id);
		System.out.println(groups.getGroupName());
		ActionContext.getContext().getValueStack().set("groups",groups);
		System.out.println(groups.getGroupName()+"111111111111111111111111111");
		return "update";
	}

}
