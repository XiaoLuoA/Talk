package com.xiaoluo.admin.group;

import java.util.List;

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
		ActionContext.getContext().getValueStack().set("allGroups",AdminGroupsService.me.findAllGroups());
		return "admin";
		
	}
    
    public String deleteGroups(){
    	int id =Integer.parseInt(request.getParameter("id"));
		AdminGroupsService.me.deleteGroups(id);
		ActionContext.getContext().getValueStack().set("allGroups",AdminGroupsService.me.findAllGroups());
		return "admin";
	}
	
	public String addGroups(Groups Groups){
		AdminGroupsService.me.addGroups(Groups);
		ActionContext.getContext().getValueStack().set("allGroups",AdminGroupsService.me.findAllGroups());
		return "admin";
	}
	
	public String updateGroups(Groups Groups){
		AdminGroupsService.me.updateGroups(Groups);;
		ActionContext.getContext().getValueStack().set("allGroups",AdminGroupsService.me.findAllGroups());
		return "admin";
	}
	
	public String findGroups(){
		int id =Integer.parseInt(request.getParameter("id"));
		Groups groups=AdminGroupsService.me.findGroups(id);
		System.out.println(groups.getGroupName());
		ActionContext.getContext().getValueStack().set("groups",groups);
		return "update";
	}
	
	public String searchLikeGroupsList(){
		 String name =request.getParameter("searchName");
		  List<Groups> searchLikeGroupsList =AdminGroupsService.me.searchLikeGroupsList(name);
		ActionContext.getContext().getValueStack().set("allGroups",searchLikeGroupsList);	
		return "admin";
	}

}
