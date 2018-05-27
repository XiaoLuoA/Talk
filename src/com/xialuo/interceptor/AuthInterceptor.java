package com.xialuo.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;



public class AuthInterceptor implements Interceptor {

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation inv) throws Exception {
		// TODO Auto-generated method stub
		ActionContext c = inv.getInvocationContext();
		
//		//HttpServletRequest request = c.getRequest();
//		
//		int contextLength = request.getContextPath().length();
//		String currUrl = request.getRequestURI().substring(contextLength);
//		System.out.println(UrlUtil.formatBaseUrl(currUrl));
//		
//		List<String> noAuthUrl = c.getSessionAttr("noAuthUrl");
//		
//		if(noAuthUrl != null) {
//			//页面权限处理，拦截action/method链接的所有/action/*页面
//			for (String url : noAuthUrl) {
//				if(UrlUtil.formatBaseUrl(currUrl).equals(UrlUtil.formatBaseUrl(url))) {
//					c.renderText("没有权限访问该页面！");
//					return;
//				}
//			}
//			//按钮权限
//			Map<String, Object> authBtn = c.getSessionAttr("noAuthBtnUrl");
//			// TODO 强制类型装换看看是不是有好的解决方案
//			@SuppressWarnings("unchecked")
//			List<String> noAuthBtnUrl = (List<String>) authBtn.get("btnUrlList");
//			@SuppressWarnings("unchecked")
//			Map<String, String> noAuthBtnMap = (Map<String, String>) authBtn.get("pageBtnMap");
//			request.setAttribute("noAuthBtn", noAuthBtnMap.get(currUrl));
//			for (String btnUrl : noAuthBtnUrl) {
//				if(currUrl.equals(btnUrl)) {
//					c.renderText("没有权限访问该页面！");
//					return;
//				}
//			}
//		}
		
		
		inv.invoke();
		return null;
	}
}
