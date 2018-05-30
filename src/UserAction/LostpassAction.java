package UserAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.xiaoluo.utils.MailUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xiaoluo.model.User;

public class LostpassAction extends ActionSupport{
	ActionContext context = ActionContext.getContext();
    HttpServletRequest request = ServletActionContext.getRequest();
    String uname =request.getParameter("name");
    String uemail =request.getParameter("email");
    
	public String lostpassword(){		
		List<User> userlist=service.LoginService.findUser(uname);			
		if(userlist==null){
			return "null";
		}else{
		for(User user:userlist){
			if(user.getEmail().equals(uemail)){
				request.getSession().setAttribute("user", user);
				int m=(int)(Math.random()*8999+1000); 					
				String n="【Talk】您正在修改密码，验证码为："+m;
				request.getSession().setAttribute("m", m);
				System.out.print(n);
				MailUtils.send(n, uemail);					
				return "success";
				}else{
					return "fail";
			   }
			}
		}		
		return null;				
	}
}
