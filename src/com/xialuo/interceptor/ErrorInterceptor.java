package com.xialuo.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class ErrorInterceptor implements Interceptor {

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
		ActionContext action = inv.getInvocationContext();
		try{
			return	inv.invoke();
		}catch(Exception e){
			action.getValueStack().set("msg", e.toString());
			return "err";
		}
	}

}
