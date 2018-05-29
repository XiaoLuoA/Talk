package com.xiaoluo.index;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.dao.GroupDao;
import com.xiaoluo.model.Group;

public class IndexAction extends ActionSupport{
	
	public static List<Group> allGroup = GroupDao.me.getAllGroup();
	
	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getValueStack().set("allGroup",allGroup);;
		return "index";
	}
	
	
}
