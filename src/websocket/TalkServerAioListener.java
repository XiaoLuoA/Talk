
package websocket;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.http.common.HttpRequest;
import org.tio.utils.json.Json;
import org.tio.utils.lock.SetWithLock;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.WsServerAioListener;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.xiaoluo.common.CommonData;
import com.xiaoluo.index.IndexService;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.user.LoginService;
import com.xiaoluo.user.UserService;


public class TalkServerAioListener extends WsServerAioListener {
	
	private static Logger log = LoggerFactory.getLogger(TalkServerAioListener.class);

	public static final TalkServerAioListener me = new TalkServerAioListener();

	private TalkServerAioListener() {
		
	}

	
	@Override
	public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
		super.onAfterConnected(channelContext, isConnected, isReconnect);
	
		try{
			System.out.println("onAfter连接成功！");
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

	@Override
	public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
		super.onAfterSent(channelContext, packet, isSentSuccess);
		//System.out.println("aftersent");
		
	}

	@Override
	public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
		super.onBeforeClose(channelContext, throwable, remark, isRemove);
		
		System.out.println("close");
		//获取webSocketSessionContext
				WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
				
				//获取握手包
				HttpRequest request = wsSessionContext.getHandshakeRequestPacket();
				
				//握手包获取sessionId
				String JSESSIONID = request.getParam("sessionId");
				
				//通过sessionId获取此用户
				User user = (User) CommonData.loginUser.get(JSESSIONID);
				System.out.println(Json.toJson(user));
		
		
		
		//channelContext.getGroupContext().groups.
		SetWithLock<String> groups = channelContext.getGroupContext().groups.groups(channelContext);
		channelContext.getGroupContext().groups.unbind(channelContext);
	
		Set<String> groupIds = groups.getObj();
		for(String groupId:groupIds){
			System.out.println(groupId);
			IndexService.me.removeAUserFromGroup(Integer.parseInt(groupId),user);
			JSONObject msg = new JSONObject();
			msg.put("type", 3);
			msg.put("userId", user.getId());
			msg.put("groupId", groupId);
			msg.put("num", IndexService.me.getUserSizeFromGroup(Integer.parseInt(groupId)));
			//将消息格式化
			WsResponse wsResponse = WsResponse.fromText(msg.toJSONString(), TalkServerConfig.CHARSET);
			//发送到群组
			Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
		}
		
		//发送到群组
		//Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
	}

	@Override
	public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {
		super.onAfterDecoded(channelContext, packet, packetSize);
//		if (log.isInfoEnabled()) {
//			log.info("onAfterDecoded\r\n{}\r\n{}", packet.logstr(), channelContext);
//		}
	}

	@Override
	public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {
		super.onAfterReceivedBytes(channelContext, receivedBytes);
//		if (log.isInfoEnabled()) {
//			log.info("onAfterReceivedBytes\r\n{}", channelContext);
//		}
	}

	@Override
	public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {
		super.onAfterHandled(channelContext, packet, cost);
//		if (log.isInfoEnabled()) {
//			log.info("onAfterHandled\r\n{}\r\n{}", packet.logstr(), channelContext);
//		}
	}

}
