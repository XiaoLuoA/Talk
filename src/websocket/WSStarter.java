package websocket;

import java.io.IOException;

import org.tio.server.ServerGroupContext;


public class WSStarter {
	
	/**
	 * 对框架Server的封装
	 */
	private  WSServerStarter wsServerStarter;
	
	
	/**
	 * Server上下文
	 */
	private ServerGroupContext serverGroupContext;
	
	
	/**
	 * WebSocket构造器 
	 * @param port 监听端口
	 * @param wsMsgHandler 消息处理器
	 * @throws IOException
	 */
	public WSStarter(int port, MsgHandler wsMsgHandler) throws IOException {
		//对框架赋值;设置监听端口和消息处理器
		wsServerStarter = new WSServerStarter(port, wsMsgHandler);
		//获取上下文
		serverGroupContext = wsServerStarter.getServerGroupContext();
		//设置server名字
		serverGroupContext.setName(WSConfig.PROTOCOL_NAME);
		//设置Server监听
		serverGroupContext.setServerAioListener(ServerListener.me);
		//设置心跳超时时间
		serverGroupContext.setHeartbeatTimeout(WSConfig.HEARTBEAT_TIMEOUT);
	}

	 
	
	static WSStarter appStarter;
	
	/**
	 * @param args
	 * @author xiaoluo
	 * @throws IOException
	 */
	public static void start() throws IOException {
		appStarter =  new WSStarter(WSConfig.SERVER_PORT, MsgHandler.me);
		appStarter.wsServerStarter.start();
	}
	
	public static void stop() throws IOException{
			appStarter.wsServerStarter.stop();
	}
	
	

}
