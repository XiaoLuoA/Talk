package websocket;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.http.common.Cookie;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.utils.json.Json;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoluo.common.CommonData;
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserMess;



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
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
		HttpRequest request = wsSessionContext.getHandshakeRequestPacket();
		String JSESSIONID = request.getParam("sessionId");
		
		User user = (User) CommonData.loginUser.get(JSESSIONID);
		
		if (Objects.equals("心跳内容", text)) {
			return null;
		}
		
		JSONObject jsonObject = JSONObject.parseObject(text);
		String type = jsonObject.getString("type");
		JSONObject jsonObject2 = jsonObject.getJSONObject("message");
		
		
		String groupId="";
		String userId="";
		String msg="";
		
		if(type.equals("0")){//已经建立连接进行发消息
			
			String toId = jsonObject2.getString("toId");
			boolean flag = CommonData.loginUserID.get(Integer.parseInt(toId))!=null;
			
			if(flag){
				
				jsonObject2.put("fromId", user.getId());
				
				//UserMess u = Json.toBean(jsonObject2.toJSONString(), UserMess.class);
				
				WsResponse wsResponse = WsResponse.fromText(text, TalkServerConfig.CHARSET);
				System.out.println();
				//Aio.sendToUser(channelContext.getGroupContext(), userId, wsResponse);
			}else{
				//存数据库 会话，消息
				
			}
			
			
			
			
			
		}
		
		if(groupId!=null){
			
			if(type.equals("1")){
				Aio.bindGroup(channelContext,groupId);
				CommonData.usersInGroup.add(user);
				
			}else if(type.equals("2")){
				
				WsResponse wsResponse = WsResponse.fromText(msg, TalkServerConfig.CHARSET);
				Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
				CommonData.groupsMess.add(new GroupsMess());
				
			}else if(type.equals("3")){
				Aio.unbindGroup(groupId, channelContext);
				CommonData.usersInGroup.remove(user);
			}
		}
		return null;
	}
}
