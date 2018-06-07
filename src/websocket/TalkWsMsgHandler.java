package websocket;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.ChannelContextFilter;
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
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.talk.TalkService;



public class TalkWsMsgHandler implements IWsMsgHandler {
	
	private static Logger log = LoggerFactory.getLogger(TalkWsMsgHandler.class);

	public static TalkWsMsgHandler me = new TalkWsMsgHandler();

	private TalkWsMsgHandler() {
			
	}

	/**
	 * 握手时走这个方法，业务可以在这里获取cookie，request参数等
	 */
	@Override
	public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
		System.out.println("handshake");
		String JSESSIONID = request.getParam("sessionId");
		System.out.println("handshake sessionId是"+JSESSIONID);
		User user = (User) CommonData.loginUser.get(JSESSIONID);
		if(user!=null){
			//channelContext.setUserid(user.getId()+"");
			Aio.bindUser(channelContext, user.getId()+"");
			System.out.println(user.getId()+"建链成功");
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
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
		HttpRequest request = wsSessionContext.getHandshakeRequestPacket();
		String JSESSIONID = request.getParam("sessionId");
		User user = (User) CommonData.loginUser.get(JSESSIONID);
		CommonData.loginUser.remove(user);
		return null;
	}

	
	/*
	 * 字符消息（binaryType = blob）过来后会走这个方法
	 */
	@Override
	public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
		try{
			
		//如果发的是心跳；now直接返回null;之后添加相关业务逻辑
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
		
		
		//测试
		System.out.println("come in type"+type);
		System.out.println("come int message"+jsonObject2);
		//测试
		
		//将fromId(SessionId)换为当前用户的id
		jsonObject2.put("fromId", user.getId());
		
		//封装返回的Json字符串
		jsonObject.put("message", jsonObject2);
		
		//发给私人的消息;并且对方已经和自己建立连接
		if(type.equals("0")){
		
			//获取接受者的id
			String toId = jsonObject2.getString("toId");
			Integer toIdInt = Integer.parseInt(toId);
			
			//判断接收者是否在线
			boolean flag = CommonData.loginUserID.contains(toId);
			
			//发消息前判断是否黑名单
			
			//发消息前判断是否黑名单
			
			if(flag){
				//接收者在线,只需要将消息发给目标用户
				
				//将得到的消息转发给目标用户
				WsResponse wsResponse = WsResponse.fromText(jsonObject.toJSONString(), TalkServerConfig.CHARSET);
				Aio.sendToUser(channelContext.getGroupContext(), toId, wsResponse);
			}else{
				
				//接收者不在线,保存UserMess
				UserMess userMess = Json.toBean(jsonObject2.toJSONString(), UserMess.class);
				System.out.println(jsonObject2.toJSONString());
				TalkService.me.saveMsg(userMess);
				//UserItem talkerItem = new UserItem();
			}
		
		}
		
		
		//加入群组的消息 type:1 groupId
		if(type.equals("1"))
		{
			try{
				//获取groupId
				String groupId = jsonObject2.getString("groupId");
				
				//测试
				System.out.println("type1");
				System.out.println("groupId "+groupId+", userID "+user.getId());
				//测试
				
				
				JSONObject ret = new JSONObject();
				ret.put("type", 1);
				ret.put("num", CommonData.usersInGroup.size()+1);
				ret.put("groupId", groupId);
				ret.put("user", user);
				
				//将此用户绑定到groupId
				Aio.bindGroup(channelContext,groupId);
				
				//将用户加入到Group中
				CommonData.usersInGroup.add(user);
				
				//向群组中的所有用户发消息，XXX登录,并且将用户信息显示出来
				
				//将消息格式化
				
				WsResponse wsResponse = WsResponse.fromText(ret.toJSONString(), TalkServerConfig.CHARSET);
				
				Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
				
				System.out.println("type1执行完毕");
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		//普通发群消息 type:2
		else if(type.equals("2"))
		{
			
			try{
				
			System.out.println("come in type2");
			
			//获取groupId
			String groupId = jsonObject2.getString("groupId");
			
			//将takerId(SessionId)换为当前用户的id
			jsonObject2.put("talkerId", user.getId());
			
			//将jsonObject2对象转化为GroupsMess对象
			GroupsMess userMess = Json.toBean(jsonObject2.toJSONString(), GroupsMess.class);
			System.out.println(jsonObject2);
			
			//将消息格式化
			WsResponse wsResponse = WsResponse.fromText(Json.toJson(userMess), TalkServerConfig.CHARSET);
		
			//发送到群组
			//Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
			Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse, new ChannelContextFilter() {
				@Override
				public boolean filter(ChannelContext arg0) {
					// TODO Auto-generated method stub
					return false;
				}
			});
			
			//向某个群组中加入消息
			
			//获取群组当前消息队列
			MyQueue<GroupsMess> myQueue = CommonData.groupsMess.get(groupId);
			
			//若不存在队列，初始化大小为50的队列
			if(myQueue==null){
				myQueue = new MyQueue<GroupsMess>(50);
			}
			
			//向队列中加入消息
			myQueue.add(userMess);
			
			//将队列放在群组消息中
			CommonData.groupsMess.put(groupId, myQueue);
			
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
				CommonData.usersInGroup.remove(user);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		//发给私人的消息;对方暂时未和自己建立连接
		else if(type.equals("4")){
			//获取接受者的id
			String toId = jsonObject2.getString("toId");
			Integer toIdInt = Integer.parseInt(toId);
			
			UserItem userItem = new UserItem();
//			userItem.setUserItemId(toId+"|"+user.getId())
//			.setIsBlack(0).setLastTime(System.currentTimeMillis()).setNewNum(0).setTalkerId(user.getId())
//			.setTalkerName(user.getName()).setTalkerPic(user.getPic())
//			.setUserId(toIdInt).setUserName(userMess.get);
			
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
