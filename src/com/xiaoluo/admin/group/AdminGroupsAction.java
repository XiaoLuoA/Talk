package com.xiaoluo.admin.group;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.model.Groups;




public class AdminGroupsAction extends ActionSupport  implements ModelDriven<Groups>{
	
	
    private Groups groups = new Groups() ;
    ActionContext context = ActionContext.getContext();
    HttpServletRequest request = ServletActionContext.getRequest();	
    @Override
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
	
	public String addGroups(){
		AdminGroupsService.me.addGroups(groups);
		ActionContext.getContext().getValueStack().set("allGroups",AdminGroupsService.me.findAllGroups());
		return "admin";
	}
	
	public String updateGroups(){
		AdminGroupsService.me.updateGroups(groups);;
		ActionContext.getContext().getValueStack().set("allGroups",AdminGroupsService.me.findAllGroups());
		return "admin";
	}
	
	public String findGroups(){
		int id =Integer.parseInt(request.getParameter("id"));
		Groups groups=AdminGroupsService.me.findGroups(id);
		ActionContext.getContext().getValueStack().set("groups",groups);
		return "update";
	}
	
	public String searchLikeGroupsList() throws UnsupportedEncodingException{
		 
		  request.setCharacterEncoding("utf-8");
		  String name =request.getParameter("searchName");
		  List<Groups> searchLikeGroupsList =AdminGroupsService.me.searchLikeGroupsList(name);
		  ActionContext.getContext().getValueStack().set("allGroups",searchLikeGroupsList);	
		  return "admin";
	}

}
