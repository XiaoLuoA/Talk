package com.xiaoluo.admin.user;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.admin.menu.AdminMenuService;
import com.xiaoluo.model.User;





public class AdminRightAction extends ActionSupport implements ModelDriven<User> {
	 private User user =new User();
	
	 ActionContext context = ActionContext.getContext();
     HttpServletRequest request = ServletActionContext.getRequest();
    
     
    
	@Override
	public User getModel() {
		return user;
	}

	



	
	public String listAllRight()  {
			
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		HttpSession sessionName = request.getSession();
		User loginUser = (User) ActionContext.getContext().getSession().get("user");
		sessionName.setAttribute("menuList", AdminMenuService.me.getUserMenu(loginUser));		
		return "admin";
		
	}
	
	public String deleteRight() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		int id =Integer.parseInt(request.getParameter("id"));
		AdminUserService.me.deleteUser(id);
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		return "admin";
	}
	
	
	public String addRight() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		AdminUserService.me.addUser(user);
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		return "admin";
	}
	
	public String updateRight() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
	
		AdminUserService.me.updateUser(user);;
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.findAllUsers());
		return "admin";
	}
	
	public String findRight() throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		int id =Integer.parseInt(request.getParameter("id"));
		User user=AdminUserService.me.findUser(id);
		ActionContext.getContext().getValueStack().set("user",user);	
		return "update";
	}
	public String searchLikeRightList() throws UnsupportedEncodingException{
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
