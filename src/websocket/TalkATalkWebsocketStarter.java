package websocket;

import java.io.IOException;

import org.tio.server.ServerGroupContext;
import org.tio.websocket.server.WsServerStarter;


public class TalkATalkWebsocketStarter {
	
	private  WsServerStarter wsServerStarter;
	private ServerGroupContext serverGroupContext;
	
	public TalkATalkWebsocketStarter(int port, TalkWsMsgHandler wsMsgHandler) throws IOException {
		wsServerStarter = new WsServerStarter(port, wsMsgHandler);
		serverGroupContext = wsServerStarter.getServerGroupContext();
		serverGroupContext.setName(TalkServerConfig.PROTOCOL_NAME);
		serverGroupContext.setServerAioListener(TalkServerAioListener.me);
		//设置心跳超时时间
		serverGroupContext.setHeartbeatTimeout(TalkServerConfig.HEARTBEAT_TIMEOUT);
	}

	/**
	 * @param args
	 * @author tanyaowu
	 * @throws IOException
	 */
	public static void start() throws IOException {
		TalkATalkWebsocketStarter appStarter = new TalkATalkWebsocketStarter(TalkServerConfig.SERVER_PORT, TalkWsMsgHandler.me);
		appStarter.wsServerStarter.start();
	}

	/**
	 * @return the serverGroupContext
	 */
//	public ServerGroupContext getServerGroupContext() {
//		return serverGroupContext;
//	}

//	public WsServerStarter getWsServerStarter() {
//		return wsServerStarter;
//	}
	
	public static void main(String[] args) throws IOException {
		start();
	}

}
