package UserAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import service.LoginService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.model.User;

public class LoginAction extends ActionSupport{
	 ActionContext context = ActionContext.getContext();
     HttpServletRequest request = ServletActionContext.getRequest();
     String uname =request.getParameter("name");
     String upassword =request.getParameter("password");
	public String lostpassword(){		
		List<User> userlist=service.LoginService.findUser(uname);
		if(userlist==null){
			return "fail";
		}else{
		for(User user:userlist){
			if(user.getPassword().equals(upassword)){
			  request.getSession().setAttribute("user", user);
				return "success";
				}else{
					return "fail";
			   }
			}
		}		
		return null;				
	}

}
