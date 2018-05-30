package com.xiaoluo.admin.user;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



import com.xiaoluo.model.User;




public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
	private User user;
	
	
    
	@Override
	public User getModel() {
		
		return user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getValueStack().set("allUser",AdminUserService.me.allUser);
		return "admin";
		//主界面拿到alluser itretaror遍历即可;
	}
	
	public String deleteUser(User user){
		AdminUserService.me.deleteUser(user);
		return "admin";
	}
	
	public String addUser(User user){
		AdminUserService.me.addUser(user);
		return "admin";
	}
	
	public String updateUser(User user){
		AdminUserService.me.updateUser(user);;
		
		return "admin";
	}

	
	
	

}
