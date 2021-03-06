package com.xiaoluo.user;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.tio.utils.json.Json;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.common.CommonData;
import com.xiaoluo.common.StatusConst;
import com.xiaoluo.index.IndexService;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.EhCacheUtil;
import com.xiaoluo.utils.NameUtils;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;
import com.xiaoluo.utils.StrKit;


public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	public String loginPage(){
		return "loginPage";
	}
	
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	public String regPage(){
		return "regPage";
	}
	
	/**
	 * 跳转到个人中心页面
	 * @return
	 */
	public String myCenterPage(){
		return "myCenterPage";
	}
	
	
	
	
	/**
	 * 跳转到找回密码页面
	 * @return
	 */
	public String findPage(){
		return "findPage";
	}
	
	
	/**
	 * 跳转到个人信息页面
	 * @return
	 */
	public String infoPage(){
		return "infoPage";
	}
	
	
	/**
	 * 跳转到重置密码页面
	 * @return
	 */
	public String changePage(){
		return "changePage";
	}
	

	/**
	 * 注册用户;
	 * 校验密码用户名一定不能为空；
	 */
	public void regist(){	
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		Ret ret = Ret.ok();
		try{
		boolean flag =StrKit.isBlank(user.getName())||StrKit.isBlank(user.getPassword());
		if(flag){
			ret.setFail();
			ret.set("msg", "用户名和密码一定不能为空！");
			response.getWriter().write(ret.toJson());
			return;
		}
		UserService.me.regist(user);
		response.getWriter().write(ret.toJson());
		} catch (Exception e) {
			System.out.println("UserAction regist 方法："+e);
		}
	}
	
	
	/**
	 * 检查name是否存在;
	 * 且用户名不能为空;
	 */
	public void checkName(){
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		if(StrKit.isBlank(user.getName())){
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
	
	public void edit(){
		try{
			ActionContext ac = ActionContext.getContext();
			HttpServletResponse response = ResponseUtils.getResponse(ac);
			User loginUser = (User) ac.getSession().get("user");
		
			loginUser.setEmail(user.getEmail());
			loginUser.setName(user.getName());
			loginUser.setSex(user.getSex());
			System.out.println(loginUser.getId());
			UserService.me.saveOrUpdate(loginUser);
			
			//User loginUser = LoginService.me.findUser(user.getName());
			Ret ret = Ret.ok();
			ret = ret.setFail().set("msg", "修改成功！");
		
		
		
		
			response.getWriter().write(ret.toJson());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录；用户名、密码不能为空；
	 * session中存放user和sessionId
	 */
	public void login() {
		try{
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		Ret ret = Ret.ok();
		if(StrKit.isBlank(user.getName())||StrKit.isBlank(user.getPassword())){
			try {
				ret.setFail().set("msg", "用户名/密码不能为空");
				response.getWriter().write(ret.toJson());
				return;
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		
		User loginUser = LoginService.me.findUser(user.getName(),StatusConst.OK);
		if(loginUser==null){
			ret = ret.setFail().set("msg", "用户名密码不匹配");
			
		}else{
		if(loginUser.getPassword().equals(user.getPassword()))
		{
			String sessionId = ServletActionContext.getRequest().getSession().getId();
			ActionContext.getContext().getSession().put("user", loginUser.setName(NameUtils.getNickName()));
			ActionContext.getContext().getSession().put("sessionId", sessionId);
			int pic = (int) (Math.random()*9);
			loginUser.setPic("face/"+pic+".jpg");
			CommonData.loginUser.set(sessionId, loginUser);
			//EhCacheUtil.getInstance().put("ehcache001", sessionId, loginUser);
			CommonData.loginUserID.add(loginUser.getId()+"");
			ret.set("returnUrl", "indexindex.action");
		}
		else
		{
			ret = ret.setFail().set("msg", "用户名密码不匹配");
		}
		}
		try 
		{
			response.getWriter().write(ret.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}catch(Exception e){
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
	
	public String exit(){
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = ResponseUtils.getResponse(ac);
		User loginUser = (User) ActionContext.getContext().getSession().remove("user");
		CommonData.loginUser.remove(loginUser);
		CommonData.loginUserID.remove(loginUser.getId());
		ac.getSession().remove("user");
		ac.getSession().remove("authLoad");
		return "exit";
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
