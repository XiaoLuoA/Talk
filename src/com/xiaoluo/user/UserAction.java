package com.xiaoluo.user;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.MailUtils;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	
	/*
	 * 注册用户
	 * 用户初始化：状态为2(正常);角色为1(普通用户);举报数为0;
	 */
	public String regist(){	
		try {
			user.setStatus("2").setCreateTime(System.currentTimeMillis()).setPic("")
			.setRoles("1").setTel(user.getTel()).setEmail(user.getEmail()).setReportNum(0)
			.setPassword(user.getPassword()).setName(user.getName());
			return "registSuccess";	
		} catch (Exception e) {
			System.out.println("UserAction regist 方法："+e);
			return "registFail";
		}
			
	}
	
	
	public String login(){
		
		User loginUser = LoginService.me.findUser(user.getName());
		
		if(loginUser.getPassword().equals(user.getPassword()))
		{
			ActionContext.getContext().getSession().put("user", loginUser);
			return "loginSuccess";
		}
		return "loginFail";			
	}
	
	
	public String findPass(){
	   
		User loginUser = LoginService.me.findUser(user.getName());
		 if(loginUser.getEmail().equals(user.getEmail())){
			int m=(int)(Math.random()*8999+1000); 					
			String n="【Talk】您正在修改密码，验证码为："+m;
			MailUtils.send(n, user.getEmail());					
			return "findPassSuccess";
		}
			return "findPassFail";				
	}
	
	public String changePass(){
		ActionContext request = ActionContext.getContext();
		return "changeSuccess";
	}

}
