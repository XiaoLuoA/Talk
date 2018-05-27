package com.xialuo.interceptor;

import java.util.List;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class MenuAuthInterceptor implements Interceptor {
//	public void intercept(Invocation inv) {
//		inv.invoke();
//		Controller controller = inv.getController();
//		
//		Boolean authLoad = getAuthLoad(controller.getSessionAttr("authLoad"));
//		
//		SysUser sysUser = controller.getSessionAttr("sysUser");
//		// 加载权限条件，标识符为true，有登录用户
//		if (authLoad && null != sysUser) {
//			LoginService loginService = Duang.duang(LoginService.class);
//			// 用户菜单
//			controller.setSessionAttr("menuList"
//					, loginService.getUserSysMenu(sysUser));
//			// 页面权限
//			List<String> noAuthUrl = loginService.getNoAuthUrl();
//			
//			controller.setSessionAttr("noAuthUrl", noAuthUrl);
//			// 按钮权限
//			controller.setSessionAttr("noAuthBtnUrl", loginService.getNoAuthBtnUrl(sysUser));
//			// 数据权限
//			controller.setSessionAttr("noAuthDatarule", loginService.getNoAuthDatarule(sysUser));
//			
//			
//			if("admin".equals(sysUser.get("user_name"))) {
//				controller.setSessionAttr("noAuthUrl", null);
//				controller.setSessionAttr("noAuthBtnUrl", null);
//				controller.setSessionAttr("pageBtnMap", null);
//				controller.setSessionAttr("noAuthDatarule", null);
//			}
//			// 加载完毕，修改标识符
//			controller.setSessionAttr("authLoad", false);
//		}
//	}
	
	/**
	 * 没有authLoad，说明是首次调用设置成true
	 * @param sessionAttr
	 * @return
	 */
	private Boolean getAuthLoad(Object sessionAttr) {
		return sessionAttr == null ? true : Boolean.parseBoolean(sessionAttr.toString());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
