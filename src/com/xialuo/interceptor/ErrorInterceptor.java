package com.xialuo.interceptor;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class ErrorInterceptor implements Interceptor {

//	public void intercept(Invocation inv) {
//		Controller c = inv.getController();
//		// HttpServletRequest request = c.getRequest();
//		
//		try {
//			inv.invoke();
//		} catch (ErrorMsgException e) {
//			//返回失败结果。（页面会显示错误消息）
//			Map<String, Object> result = new HashMap<String, Object>();
//			result.put("result", "fail");
//			result.put("msg", e.getMessage());
//			c.renderJson(result);
//		}
//	}

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
