package websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.utils.json.Json;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;
import com.alibaba.fastjson.JSONObject;
import com.xiaoluo.common.CommonData;
import com.xiaoluo.common.MyQueue;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.dao.UserItemDao;
import com.xiaoluo.index.IndexService;
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.talk.TalkService;



public class MsgHandler implements IWsMsgHandler {
	
	private static Logger log = LoggerFactory.getLogger(MsgHandler.class);

	public static MsgHandler me = new MsgHandler();

	private MsgHandler() {
			
	}

	/**
	 * 握手时走这个方法，业务可以在这里获取cookie，request参数等
	 */
	@Override
	public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
		String JSESSIONID = request.getParam("sessionId");
		User user = (User) CommonData.loginUser.get(JSESSIONID);
		if(user!=null){
			Aio.bindUser(channelContext, user.getId()+"");
		}else{
			System.out.println("建链成功个屁！");
			return null;
		}
		return httpResponse;
	}

	/**
	 * 字节消息（binaryType = arraybuffer）过来后会走这个方法
	 */
	@Override
	public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		return null;
	}

	/**
	 * 当客户端发close flag时，会走这个方法
	 */
	@Override
	public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		Aio.remove(channelContext, "receive close flag");
//		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
//		HttpRequest request = wsSessionContext.getHandshakeRequestPacket();
//		String JSESSIONID = request.getParam("sessionId");
//		User user = (User) CommonData.loginUser.get(JSESSIONID);
//		CommonData.loginUser.remove(user);
		return null;
	}

	
	/*
	 * 字符消息（binaryType = blob）过来后会走这个方法
	 */
	@Override
	public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
		try{
			
		//如果发的是心跳；直接返回null;之后添加相关业务逻辑
		if (Objects.equals("心跳内容", text)) {
			return null;
		}
		
		//获取webSocketSessionContext
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
		
		//获取握手包
		HttpRequest request = wsSessionContext.getHandshakeRequestPacket();
		
		//握手包获取sessionId
		String JSESSIONID = request.getParam("sessionId");
		
		//通过sessionId获取此用户
		User user = (User) CommonData.loginUser.get(JSESSIONID);
	
		//将发来的json字符串消息转化成JSONObject
		JSONObject jsonObject = JSONObject.parseObject(text);
		
		//获取type,得到消息类型
		String type = jsonObject.getString("type");
		
		//获取message对象(JSONObject类型)
		JSONObject jsonObject2 = jsonObject.getJSONObject("message");
		
		//将fromId(SessionId)换为当前用户的id
		jsonObject2.put("fromId", user.getId());
		
		//封装返回的Json字符串
		jsonObject.put("message", jsonObject2);
		
		//发给私人的消息;
		if(type.equals("0")){
		
			//获取接受者的id
			String toId = jsonObject2.getString("toId");
			Integer toIdInt = Integer.parseInt(toId);
			
			//判断接收者是否在线
			boolean flag = CommonData.loginUserID.contains(toId);
			
			//判断是否已经连接
			UserItem userItem = TalkService.me.getUserItem(user.getId(), toIdInt);
			
			boolean isConn = userItem != null;
			
			if(!isConn){
				String tempName = jsonObject.getString("tempName");
				String tempPic = jsonObject.getString("tempPic");
				
				UserItem thisUserItem = new UserItem();
				UserItem toUserItem = new UserItem();
				
				//Id为A发给B;黑名单默认0;最后一次消息的时间;新消息的数量不填;TalkerName;
				//存在自己的虚拟name
				thisUserItem.setUserItemId(user.getId()+"|"+toId).setIsBlack(0).
				setLastTime(System.currentTimeMillis()).setNewNum(0).setTalkerId(toIdInt)
				.setTalkerName(tempName).setTalkerPic(tempPic+" ")
				.setUserId(user.getId()).setUserName(user.getName());
				
				
				toUserItem.setUserItemId(toId+"|"+user.getId())
				.setIsBlack(0).setLastTime(System.currentTimeMillis())
				.setNewNum(0).setTalkerId(user.getId())
				.setTalkerName(user.getName()).setTalkerPic(user.getPic()+" ")
				.setUserId(toIdInt).setUserName(tempName);
				
				UserMess userMess = Json.toBean(jsonObject2.toJSONString(), UserMess.class);
				
				List<UserMess> list = new ArrayList<UserMess>();
				list.add(userMess);
				
				if(flag){
					TalkService.me.saveUserItem(thisUserItem, toUserItem);
					JSONObject ret = new JSONObject();
					ret.put("type", 4);
					toUserItem.setMessages(list);
					ret.put("items", Json.toJson(toUserItem));
					WsResponse wsResponse = WsResponse.fromText(ret.toJSONString(), WSConfig.CHARSET);
					Aio.sendToUser(channelContext.getGroupContext(), toId, wsResponse);
				}
				else
				{
					TalkService.me.saveMsgAndUserItem(userMess, thisUserItem, toUserItem);
				}
			}
			
			
			if(flag){
				//接收者在线,只需要将消息发给目标用户
				
				//将得到的消息转发给目标用户
				WsResponse wsResponse = WsResponse.fromText(jsonObject.toJSONString(), WSConfig.CHARSET);
				Aio.sendToUser(channelContext.getGroupContext(), toId, wsResponse);
			}else{
				//接收者不在线,保存UserMess
				UserMess userMess = Json.toBean(jsonObject2.toJSONString(), UserMess.class);
				TalkService.me.saveMsg(userMess);
			}
		
		}
		
		//加入群组的消息 type:1 groupId
		if(type.equals("1"))
		{
			try{
				//获取groupId
				String groupId = jsonObject2.getString("groupId");
				jsonObject2.put("num", IndexService.me.getUserSizeFromGroup(Integer.parseInt(groupId)+1));
				jsonObject2.put("groupId", groupId);
				jsonObject2.put("user", user);
				jsonObject.put("message", jsonObject2);
				
				//将此用户绑定到groupId
				Aio.bindGroup(channelContext,groupId);
				
				//将用户加入到Group中
				IndexService.me.addAUserToGroup(Integer.parseInt(groupId), user);
				
				//将消息格式化
				WsResponse wsResponse = WsResponse.fromText(jsonObject.toJSONString(), WSConfig.CHARSET);
				Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//普通发群消息 type:2
		else if(type.equals("2"))
		{
			
			try{
			
			//获取groupId
			String groupId = jsonObject2.getString("groupId");
			
			jsonObject2.put("talkerId", user.getId());
			jsonObject2.put("talkerPic", user.getPic());
			jsonObject2.put("talkerName", user.getName());
			//将jsonObject2对象转化为GroupsMess对象
			GroupsMess userMess = Json.toBean(jsonObject2.toJSONString(), GroupsMess.class);
			jsonObject.put("message", jsonObject2);
			//将消息格式化
			WsResponse wsResponse = WsResponse.fromText(jsonObject.toJSONString(), WSConfig.CHARSET);
			//发送到群组
			Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
			//向某个群组中加入消息
			IndexService.me.addAMessToGroup(Integer.parseInt(groupId), userMess);
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//退出群聊 type:3
		else if(type.equals("3"))
		{
			try{
				//获取groupId
				String groupId = jsonObject2.getString("groupId");
				Aio.unbindGroup(groupId, channelContext);
				IndexService.me.removeAUserFromGroup(Integer.parseInt(groupId),user);
				JSONObject msg = new JSONObject();
				msg.put("type", 3);
				msg.put("userId", user.getId());
				msg.put("groupId", groupId);
				msg.put("num", IndexService.me.getUserSizeFromGroup(Integer.parseInt(groupId)));
				//将消息格式化
				WsResponse wsResponse = WsResponse.fromText(msg.toJSONString(), WSConfig.CHARSET);
				//发送到群组
				Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(type.equals("5")){
			String deleteItemId = (String) jsonObject2.get("deleteItemId");
			TalkService.me.deleteUserItem(deleteItemId);
			System.out.println(deleteItemId);
			String[] ids = deleteItemId.split("\\|");
			
		
			
			Integer userid = Integer.parseInt(ids[0]);
			Integer deleteme = Integer.parseInt(ids[1]);
			
			
			TalkService.me.deleteUserItem(deleteme+"|"+userid);
			boolean isLogin = CommonData.loginUserID.contains(deleteme+"");
			
			JSONObject msg = new JSONObject();
			msg.put("type", 5);
			
			String[] str = new String[1];
			str[0] = userid+"";
			JSONObject groupids = new JSONObject();
			groupids.put("deleteIds", str);
			
			msg.put("message", groupids);
			if(isLogin){
				System.out.println("login");
				WsResponse wsResponse = WsResponse.fromText(msg.toJSONString(), WSConfig.CHARSET);
				Aio.sendToUser(channelContext.getGroupContext(), deleteme+"", wsResponse);
			}else{
				//增加把我删除的人;直接调用了dao层  （·--·）
				System.out.println("nologin");
				UserDao.me.addWhoDelete(deleteme, userid);
			}
		}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
