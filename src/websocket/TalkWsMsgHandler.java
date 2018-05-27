package websocket;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;


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
		String groupId = request.getParam("groupId");
		String userId = request.getParam("userId");
		
		if(groupId == null && userId == null){
			return httpResponse;
		}
		
		if(userId != null){
			Aio.bindUser(channelContext, userId);
		}
		else if(groupId != null)
		{
			Aio.bindGroup(channelContext, groupId);
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
		return null;
	}

	/*
	 * 字符消息（binaryType = blob）过来后会走这个方法
	 */
	@Override
	public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
		HttpRequest request = wsSessionContext.getHandshakeRequestPacket();//获取websocket握手包

		System.out.println("handshake");
		String groupId = request.getParam("groupId");
		String userId = request.getParam("userId");
		
		if(groupId == null && userId == null){
			return null;
		}
		String msg = "";
		if(userId != null){
			msg = channelContext.getUserid() + " 说：" + text;
			WsResponse wsResponse = WsResponse.fromText(msg, TalkServerConfig.CHARSET);
			Aio.sendToUser(channelContext.getGroupContext(), userId, wsResponse);
		}
		else if(groupId != null)
		{
			msg = channelContext.getUserid() + " 说：" + text;
			WsResponse wsResponse = WsResponse.fromText(msg, TalkServerConfig.CHARSET);
			Aio.sendToGroup(channelContext.getGroupContext(),groupId , wsResponse);
		}
		
		if (Objects.equals("心跳内容", text)) {
			return null;
		}
		
		//用tio-websocket，服务器发送到客户端的Packet都是WsResponse
		System.out.println("onText");
		System.out.println(""+groupId);
		return null;
	}
}
