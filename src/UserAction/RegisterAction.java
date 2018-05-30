package UserAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoluo.model.User;

import service.RegisterService;;

public class RegisterAction  implements ModelDriven<User>{	
	private User user = new User();
		
	@Override
	public User getModel() {
		return user;
	}
	
	/*
	private String name;
	private String password;
	private String sex;
	private String email;
	private String pic;*/
	
	public String register(){	
		try {
			user.setStatus("1").setCreateTime(System.currentTimeMillis()).setPic("")
			.setRoles("1").setTel("1231").setReportNum(0);
			service.RegisterService.register(user);
			return "success";	
			
		} catch (Exception e) {
			System.out.println(e);
			return "fail";
		}
			
	}

	

	

}
