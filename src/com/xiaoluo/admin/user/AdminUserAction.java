package com.xiaoluo.admin.user;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.tio.utils.json.Json;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.admin.menu.AdminMenuService;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.Menu;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;

import cn.hutool.db.Session;




public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
	 private User user =new User();
	
	 ActionContext context = ActionContext.getContext();
     HttpServletRequest request = ServletActionContext.getRequest();
    
     
    
	@Override
	public User getModel() {
		return user;
	}

	

	
	
	public String listAllUser() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		HttpSession sessionName = request.getSession();
		User loginUser = (User) ActionContext.getContext().getSession().get("user");
		sessionName.setAttribute("menuList", AdminMenuService.me.getUserMenu(loginUser));
		
		/*List<Menu> list=AdminMenuService.me.getUserMenu(loginUser);
		  for(int i=0;i<list.size();i++){
         	
         System.out.print(list.get(i).getMenuUrl());
         System.out.print(list.get(i).getMenuName());
         	
         	 
         	  for(int j=0;j<list.get(i).getSubMenuList().size();j++){
         		  
         		System. out.print(list.get(i).getSubMenuList().get(j).getMenuUrl());
         		System. out.print(list.get(i).getSubMenuList().get(j).getMenuName());

         	    }
         	  }
         	   */
		return "admin";
		
	}
	
	public String deleteUser() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		int id =Integer.parseInt(request.getParameter("id"));
		AdminUserService.me.deleteUser(id);
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		return "admin";
	}
	
	public void testAjax() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		Ret ret = Ret.ok();
		
		ret.set("msg","success");
		
		ret.setFail();
		
		try {
			
			response.getWriter().print(Json.toJson(ret));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String addUser() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		AdminUserService.me.addUser(user);
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		return "admin";
	}
	
	public String updateUser() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
	
		AdminUserService.me.updateUser(user);;
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		return "admin";
	}
	
	public String findUser() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		int id =Integer.parseInt(request.getParameter("id"));
		User user=AdminUserService.me.findUser(id);
		ActionContext.getContext().getValueStack().set("user",user);	
		return "update";
	}
	public String searchLikeUserList() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		 
		String name =request.getParameter("searchName");
		  List<User> searchLikeUserList =AdminUserService.me.searchLikeUserList(name);
		ActionContext.getContext().getValueStack().set("allUser",searchLikeUserList);	
		return "admin";
	}






	public User getUser() {
		return user;
	}






	public void setUser(User user) {
		this.user = user;
	}

	
	
	

}
