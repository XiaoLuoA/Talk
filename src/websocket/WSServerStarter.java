package websocket;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.intf.TioUuid;
import org.tio.http.server.util.Threads;
import org.tio.server.AioServer;
import org.tio.server.ServerGroupContext;
import org.tio.utils.thread.pool.SynThreadPoolExecutor;
import org.tio.websocket.common.WsTioUuid;
import org.tio.websocket.server.WsServerAioHandler;
import org.tio.websocket.server.WsServerAioListener;
import org.tio.websocket.server.WsServerConfig;
import org.tio.websocket.server.handler.IWsMsgHandler;

/**
 *
 * @author tanyaowu
 * 
 */
public class WSServerStarter {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(WSServerStarter.class);

	private WsServerConfig wsServerConfig = null;

	private IWsMsgHandler wsMsgHandler = null;

	private WsServerAioHandler wsServerAioHandler = null;

	private WsServerAioListener wsServerAioListener = null;

	private ServerGroupContext serverGroupContext = null;

	private AioServer aioServer = null;

	/**
	 * @return the wsServerConfig
	 */
	public WsServerConfig getWsServerConfig() {
		return wsServerConfig;
	}

	/**
	 * @return the wsMsgHandler
	 */
	public IWsMsgHandler getWsMsgHandler() {
		return wsMsgHandler;
	}

	/**
	 * @return the wsServerAioHandler
	 */
	public WsServerAioHandler getWsServerAioHandler() {
		return wsServerAioHandler;
	}

	/**
	 * @return the wsServerAioListener
	 */
	public WsServerAioListener getWsServerAioListener() {
		return wsServerAioListener;
	}

	/**
	 * @return the serverGroupContext
	 */
	public ServerGroupContext getServerGroupContext() {
		return serverGroupContext;
	}

	public WSServerStarter(int port, IWsMsgHandler wsMsgHandler) throws IOException {
		this(port, wsMsgHandler, null, null);
	}

	public WSServerStarter(int port, IWsMsgHandler wsMsgHandler, SynThreadPoolExecutor tioExecutor, ThreadPoolExecutor groupExecutor) throws IOException {
		this(new WsServerConfig(port), wsMsgHandler, tioExecutor, groupExecutor);
	}

	public WSServerStarter(WsServerConfig wsServerConfig, IWsMsgHandler wsMsgHandler) throws IOException {
		this(wsServerConfig, wsMsgHandler, null, null);
	}

	public WSServerStarter(WsServerConfig wsServerConfig, IWsMsgHandler wsMsgHandler, SynThreadPoolExecutor tioExecutor, ThreadPoolExecutor groupExecutor) throws IOException {
		this(wsServerConfig, wsMsgHandler, new WsTioUuid(), tioExecutor, groupExecutor);
	}
	
	public WSServerStarter(WsServerConfig wsServerConfig, IWsMsgHandler wsMsgHandler, TioUuid tioUuid, SynThreadPoolExecutor tioExecutor, ThreadPoolExecutor groupExecutor) throws IOException {
		if (tioExecutor == null) {
			tioExecutor = Threads.tioExecutor;
		}
		
		if (groupExecutor == null) {
			groupExecutor = Threads.groupExecutor;
		}
		
		this.wsServerConfig = wsServerConfig;
		this.wsMsgHandler = wsMsgHandler;
		wsServerAioHandler = new WsServerAioHandler(wsServerConfig, wsMsgHandler);
		wsServerAioListener = new WsServerAioListener();
		serverGroupContext = new ServerGroupContext("Tio Websocket Server", wsServerAioHandler, wsServerAioListener, tioExecutor, groupExecutor);
		serverGroupContext.setHeartbeatTimeout(0);

		aioServer = new AioServer(serverGroupContext);

		serverGroupContext.setTioUuid(tioUuid);
	}

	public void start() throws IOException {
		aioServer.start(wsServerConfig.getBindIp(), wsServerConfig.getBindPort());
	}
	
	public void stop() throws IOException{
		aioServer.stop();
	}
	
}
