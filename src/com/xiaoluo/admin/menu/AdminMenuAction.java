package com.xiaoluo.admin.menu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.tio.utils.json.Json;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.admin.user.AdminUserService;
import com.xiaoluo.model.Menu;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;


public class AdminMenuAction extends ActionSupport implements ModelDriven<Menu> {
	 private Menu menu =new Menu();
		
	 ActionContext context = ActionContext.getContext();
     HttpServletRequest request = ServletActionContext.getRequest();
     
    
	@Override
	public Menu getModel() {
		return menu;
	}
	
	public String listAllMenu(){
		ActionContext.getContext().getValueStack().set("allMenu",AdminMenuService.me.findAllMenus());
		return "admin";
		
	}
	
	public String deleteMenu(){
		int id =Integer.parseInt(request.getParameter("id"));
		AdminMenuService.me.deleteMenu(id);
		ActionContext.getContext().getValueStack().set("allMenu",AdminMenuService.me.findAllMenus());
		return "admin";
	}
	
	
	public String addMenu(){
		AdminMenuService.me.addMenu(menu);
		ActionContext.getContext().getValueStack().set("allMenu",AdminMenuService.me.findAllMenus());
		return "admin";
	}
	
	public String updateMenu(){
	
		AdminMenuService.me.updateMenu(menu);;
		ActionContext.getContext().getValueStack().set("allMenu",AdminMenuService.me.findAllMenus());
		return "admin";
	}
	
	public String findMenu(){
		int id =Integer.parseInt(request.getParameter("id"));
		Menu menu=AdminMenuService.me.findMenu(id);
		ActionContext.getContext().getValueStack().set("menu",menu);	
		return "update";
	}
	public String searchLikeMenuList() throws UnsupportedEncodingException{
		 request.setCharacterEncoding("utf-8");
		String name =request.getParameter("searchName");
		  List<Menu> searchLikeMenuList =AdminMenuService.me.searchLikeMenuList(name);
		ActionContext.getContext().getValueStack().set("allMenu",searchLikeMenuList);	
		return "admin";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	


	public Menu getMenu() {
		return menu;
	}


	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	

}
