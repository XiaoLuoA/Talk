package com.xiaoluo.talk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.tio.utils.json.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.common.CommonData;
import com.xiaoluo.common.MyQueue;
import com.xiaoluo.index.IndexService;
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.ResponseUtils;
import com.xiaoluo.utils.Ret;

public class TalkAction extends ActionSupport{
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	private String groupId;
	
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
		//String groupId = (String) ActionContext.getContext().get("groupId");
		
		System.out.println("ajax"+groupId);
		MyQueue<GroupsMess> gMess = IndexService.me.getMessFromGroup(Integer.parseInt(groupId));
		
		if(gMess == null){
			 gMess = new MyQueue<GroupsMess>(50);
		}
		
		List<User> users = IndexService.me.getUsersFromGroup(Integer.parseInt(groupId));
		
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
