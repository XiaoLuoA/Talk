package com.xiaoluo.user;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.tio.utils.json.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.MailUtils;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;


public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	public String loginPage(){
		return "loginPage";
	}
	
	public String regPage(){
		return "regPage";
	}
	
	public String findPage(){
		return "findPage";
	}
	
	public String infoPage(){
		return "infoPage";
	}
	
	public String changePage(){
		return "changePage";
	}
	
	/*
	 * 注册用户
	 * 用户初始化：状态为2(正常);角色为1(普通用户);举报数为0;
	 */
	public void regist(){	
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		Ret ret = Ret.ok();
		try {
			UserService.me.regist(user);
			response.getWriter().write(ret.toJson());
		} catch (Exception e) {
			System.out.println("UserAction regist 方法："+e);
		}
	}
	
	public void checkName(){
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		if(user.getName()==null||user.getName().equals("")){
			try {
				response.getWriter().write(Ret.fail().set("msg", "用户名不能为空").toJson());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ;
		}
		User loginUser = LoginService.me.findUser(user.getName());
		Ret ret = Ret.ok();
		if(loginUser!=null)
		{
			ret = ret.setFail().set("msg", "用户名已存在");
		}
		try 
		{
			response.getWriter().write(ret.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void login() {
		ActionContext ac = ActionContext.getContext();
		
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		User loginUser = LoginService.me.findUser(user.getName());
		Ret ret = Ret.ok();
		if(loginUser.getPassword().equals(user.getPassword()))
		{
			ActionContext.getContext().getSession().put("user", loginUser);
			ret.set("returnUrl", "index.action");
		}
		else
		{
			ret = ret.setFail().set("msg", "用户名密码不匹配");
		}
		try 
		{
			response.getWriter().write(ret.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void findPass(){
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		User loginUser = LoginService.me.findUser(user.getName());
		Ret ret = Ret.ok();
		 if(loginUser.getEmail().equals(user.getEmail()))
		{
			 UserService.me.sendReg(loginUser);
			 ac.getSession().put("user", loginUser);
			 ret.set("msg", "邮件发送成功！");
		}
		else
		{
			ret = ret.setFail().set("msg", "用户名邮件不匹配");
		}
		try 
		{
			response.getWriter().write(ret.toJson());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void reg(){
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		User loginUser = (User) ActionContext.getContext().getSession().get("user");
		Ret ret = Ret.ok();
		System.out.println(loginUser.getRegNum());
		System.out.println(user.getRegNum());
		System.out.println(user.getRegNum()==loginUser.getRegNum());
		if(user.getRegNum().intValue()==loginUser.getRegNum().intValue())
		{
			
		}
		else
		{
			ret = ret.setFail().set("msg", "验证码不正确");
		}
		try 
		{
			System.out.println(ret.toJson());
			response.getWriter().write(ret.toJson());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changePass(){
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		User loginUser = (User) ActionContext.getContext().getSession().get("user");
		Ret ret = Ret.ok();
		loginUser.setPassword(user.getPassword());
		UserService.me.saveOrUpdate(loginUser);
		try 
		{
			response.getWriter().write(ret.toJson());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
