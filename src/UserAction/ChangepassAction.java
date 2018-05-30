package UserAction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class ChangepassAction {
	ActionContext context = ActionContext.getContext();
    HttpServletRequest request = ServletActionContext.getRequest();
	int unum=Integer.parseInt(request.getParameter("num"));
	int n=(Integer) request.getSession().getAttribute("m");
	public String changepassword(){
		if(unum==n){
		  return "success";
		}else{
			return "fail";
		}
	}

}
