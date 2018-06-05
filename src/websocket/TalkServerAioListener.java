
package websocket;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.websocket.server.WsServerAioListener;

import com.opensymphony.xwork2.ActionContext;
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
	
		System.out.println("connect");
		System.out.println("kkkk");
		try{
			System.out.println(ActionContext.getContext().getSession());
		}catch(Exception e){
			System.out.println(e);
		}
		
		System.out.println("jjj");
		
		System.out.println(ActionContext.getContext().getSession().isEmpty());
		User user = (User)ActionContext.getContext().getSession().get("user");
		System.out.println("5555555555"+user.getId());
		System.out.println(user.getId()+"  "+user.getName());
	}

	@Override
	public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
		super.onAfterSent(channelContext, packet, isSentSuccess);
		//System.out.println("aftersent");
		
	}

	@Override
	public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
		super.onBeforeClose(channelContext, throwable, remark, isRemove);
		System.out.println("close list");
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
