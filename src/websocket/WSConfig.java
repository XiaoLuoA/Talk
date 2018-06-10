package websocket;

/**
 * WS配置
 * @author xiaoluo
 *
 */
public abstract class WSConfig {
	
	public static final String PROTOCOL_NAME = "talk";
	
	public static final String CHARSET = "utf-8";
	
	/**
	 * 监听所有的ip
	 */
	public static final String SERVER_IP = null;

	/**
	 * 监听端口
	 */
	public static final int SERVER_PORT = 8888;

	/**
	 * 心跳超时时间，单位：毫秒
	 */
	public static final int HEARTBEAT_TIMEOUT = 1000 * 60;

}
