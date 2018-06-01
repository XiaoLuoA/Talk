package com.xiaoluo.admin.group;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.model.Groups;



public class AdminGroupsAction extends ActionSupport{
private Groups group;
	
	
    public Groups getModel() {
		
		return group;
	}
    
    public String listAllGroups(){
		ActionContext.getContext().getValueStack().set("allGroups",AdminGroupsService.me.allGroups);
		return "admin";
		
	}
    
    public String deleteGroups(Groups Groups){
		AdminGroupsService.me.deleteGroups(Groups);
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

}
