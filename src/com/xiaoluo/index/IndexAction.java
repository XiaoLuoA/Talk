package com.xiaoluo.index;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.dao.GroupDao;

public class IndexAction extends ActionSupport{
	
	
	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getValueStack().set("allGroup",IndexService.me.allGroup);;
		return "index";
	}
	
	
}
