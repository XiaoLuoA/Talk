package service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StringToColorAction extends ActionSupport{
	
	private Color colors;
	
	public void setColors(Color colors){
		System.out.println("colors:"+colors.getName());
		this.colors = colors;
	}
	
	
	@Override
	public String execute() throws Exception {
		System.out.println(colors.name);
		ActionContext.getContext().getValueStack().set("colors", colors.name);;
		return "success";
	}
	
	
	
}
