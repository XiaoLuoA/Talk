
package websocket;

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


public class ServerListener extends WsServerAioListener {
	
	private static Logger log = LoggerFactory.getLogger(ServerListener.class);

	public static final ServerListener me = new ServerListener();

	private ServerListener() {
		
	}

	
	@Override
	public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
		super.onAfterConnected(channelContext, isConnected, isReconnect);
		
	}

	@Override
	public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
		super.onAfterSent(channelContext, packet, isSentSuccess);
		
	}

	
	//断链之前执行
	@Override
	public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
		super.onBeforeClose(channelContext, throwable, remark, isRemove);
		
		//获取webSocketSessionContext
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
				
		//获取握手包
		HttpRequest request = wsSessionContext.getHandshakeRequestPacket();
				
		//握手包获取sessionId
		String JSESSIONID = request.getParam("sessionId");
				
		//通过sessionId获取此用户
		User user = (User) CommonData.loginUser.get(JSESSIONID);
		
		//将该用户从登录用户中移除
		CommonData.loginUser.remove(user);
		//从登录用户id移除
		CommonData.loginUserID.remove(user.getId());
		
		//得到所有该客户端所在的所有组
		SetWithLock<String> groups = channelContext.getGroupContext().groups.groups(channelContext);
		
		//将该客户端与所有的组解绑
		channelContext.getGroupContext().groups.unbind(channelContext);
		
		//得到客户端所在所有组的id
		Set<String> groupIds = groups.getObj();
		
		for(String groupId:groupIds){
			//将该用户从某个Group中移除
			IndexService.me.removeAUserFromGroup(Integer.parseInt(groupId),user);
			JSONObject msg = new JSONObject();
			msg.put("type", 3);
			msg.put("userId", user.getId());
			msg.put("groupId", groupId);
			msg.put("num", IndexService.me.getUserSizeFromGroup(Integer.parseInt(groupId)));
			//将消息格式化
			WsResponse wsResponse = WsResponse.fromText(msg.toJSONString(), WSConfig.CHARSET);
			//将该用户退出登录的信息发送到这个群组
			Aio.sendToGroup(channelContext.getGroupContext(), groupId, wsResponse);
		}
		
		
	}

	@Override
	public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {
		super.onAfterDecoded(channelContext, packet, packetSize);

	}

	@Override
	public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {
		super.onAfterReceivedBytes(channelContext, receivedBytes);

	}

	@Override
	public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {
		super.onAfterHandled(channelContext, packet, cost);

	}

}
