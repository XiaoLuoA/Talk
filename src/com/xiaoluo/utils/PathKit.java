package com.xiaoluo.utils;
import java.io.File;



public class PathKit {
	
	private static String webRootPath;
	
	private static String rootPath;
	
	/**
	 * 获取运行时该类的路径（不包含该类名）
	 * @param clazz 类
	 * @return String 获取运行时当前类的路径（不包含该类名）
	 * <br/>
	 * 例如：D:\Test\bin\com\xiaoluo
	 */
	public static String getPath(Class<?> clazz) {
		String path;
		try {
			path = clazz.getResource("").toURI().getPath();
			return new File(path).getAbsolutePath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	/**
	 * 获取运行时该类的路径（不包含该类名）
	 * @param object 对象
	 * @return String 获取运行时该类的路径（不包含该类名）<br/>
	 * 例如：D:\Test\bin\com\xiaoluo
	 */
	public static String getPath(Object object) {
		String path;
		try {
			path = object.getClass().getResource("").toURI().getPath();
			return new File(path).getAbsolutePath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	/**
	 * 获取运行的根路径 (非web项目)
	 * @return String 运行的根路径 (非web项目)
	 * <br/>
	 * 例如：D:\Test\bin
	 */
	public static String getRootPath() {
		if(rootPath == null){
			rootPath = detectRootPath();
		}
		return rootPath;
	}
	

	/**
	 * 获取项目的根目录（Web项目）
	 * @return String 运行的根目录（Web项目）<br/>
	 * 若maven项目  返回到项目名<br/>
	 *  例如：D:\Test<br/>
	 * 若普通Web项目  返回到WebRoot <br/>
	 * 例如: D:\Test\WebRoot
	 */
	public static String getWebRootPath() {
		if (webRootPath == null) {
			webRootPath = detectWebRootPath();
		}
		return webRootPath;
	}
	
	
	private static String detectWebRootPath() {
		try {
			String path = PathKit.class.getResource("/").toURI().getPath();
			return new File(path).getParentFile().getParentFile().getCanonicalPath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String detectRootPath() {
		try {
			String path = PathKit.class.getResource("/").toURI().getPath();
			return new File(path).getAbsolutePath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 获取一个对象的包名
	 * @param object 对象
	 * @return  String 例如：com/xiaoluo/utils
	 */
	public static String getPackagePath(Object object) {
		Package p = object.getClass().getPackage();
		return p != null ? p.getName().replaceAll("\\.", "/") : "";
	}
}
