
package websocket;


public abstract class TalkServerConfig {
	/**
	 * 协议名字(可以随便取，主要用于开发人员辨识)
	 */
	public static final String PROTOCOL_NAME = "talk";
	
	public static final String CHARSET = "utf-8";
	/**
	 * 监听的ip
	 */
	public static final String SERVER_IP = null;//null表示监听所有，并不指定ip

	/**
	 * 监听端口
	 */
	public static final int SERVER_PORT = 9326;

	/**
	 * 心跳超时时间，单位：毫秒
	 */
	public static final int HEARTBEAT_TIMEOUT = 1000 * 60;

}
