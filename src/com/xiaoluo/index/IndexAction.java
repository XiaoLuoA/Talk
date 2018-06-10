package com.xiaoluo.index;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.tio.utils.json.Json;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;

public class IndexAction extends ActionSupport{
	
	/**
	 * 得到所有的群组的信息；跳转到首页
	 * @return 
	 */
	public String index() {
		ActionContext.getContext().getValueStack().set("allGroup",IndexService.me.allGroup);;
		return "index";
	}
	
	
	/**
	 * 得到所有的群组的信息；跳转到伪首页
	 * @return
	 */
	public String findex(){
		ActionContext.getContext().getValueStack().set("allGroup",IndexService.me.allGroup);;
		return "findex";
	}
	
	/**
	 * 跳转到管理员首页
	 */
	@Override
	public String execute() throws Exception {
		return "adminPage";
	}
	
	
	/**
	 * 登录后得到离线时收到的信息;以及谁把自己删除的信息
	 */
	public void getAllMsg(){
		User loginUser = (User)ActionContext.getContext().getSession().get("user");
		List<UserItem> allMsg = IndexService.me.getAllItem(loginUser);
		
		Ret ret = Ret.ok();
		ret.set("items",allMsg);
		ret.set("deleteIds",IndexService.me.getDeleteMe(loginUser.getId()));
		
		IndexService.me.deleteWhoDelete(loginUser.getId());
		
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse resp = ResponseUtils.getResponse(ac);
		try {
			resp.getWriter().write(Json.toJson(ret));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
