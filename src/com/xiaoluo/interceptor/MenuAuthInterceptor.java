package com.xiaoluo.interceptor;
import org.tio.utils.json.Json;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.xiaoluo.admin.menu.AdminMenuService;
import com.xiaoluo.model.User;
import com.xiaoluo.user.LoginService;

public class MenuAuthInterceptor implements Interceptor {
	

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
		ActionContext action = inv.getInvocationContext();
		Boolean authLoad = getMenuLoad(action.getSession().get("menuLoad"));
		User user = (User) action.getSession().get("user");
			// 用户菜单
		action.getSession().put("menuList"
				, AdminMenuService.me.getUserMenu(user));
		// 加载完毕，修改标识符
	     action.getSession().put("menuLoad", false);
	     return inv.invoke();
	}
	
	/**
	 * 没有menuLoad，说明是首次调用设置成true
	 * @param sessionAttr
	 * @return
	 */
	private Boolean getMenuLoad(Object sessionAttr) {
		return sessionAttr == null ? true : Boolean.parseBoolean(sessionAttr.toString());
	}

}
