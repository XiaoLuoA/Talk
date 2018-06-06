package com.xiaoluo.talk;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.tio.utils.json.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.common.CommonData;
import com.xiaoluo.common.MyQueue;
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;

public class TalkAction extends ActionSupport{
	
	public String java(){
		return "java";
	}
	
	public String master(){
		return "master";
	}
	
	public String found(){
		return "found";
	}
	
	public void getGroupInfo(){
		try {
		String groupId = (String) ActionContext.getContext().get("groupId");
		MyQueue<GroupsMess> gMess = CommonData.groupsMess.get(groupId);
		
		if(gMess == null){
			 gMess = new MyQueue<GroupsMess>(50);
		}
		
		List<User> users = CommonData.usersInGroup;
		Ret ret = Ret.ok();
		ret.set("items",gMess.queue);
		ret.set("users",users);
		
		System.out.println("users"+Json.toJson(users));
		System.out.println("items"+Json.toJson(gMess));
	
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse resp = ResponseUtils.getResponse(ac);
		
			resp.getWriter().write(Json.toJson(ret));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
