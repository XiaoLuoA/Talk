package com.xiaoluo.admin.roles;

import java.util.List;

import com.xiaoluo.dao.RoleDao;
import com.xiaoluo.model.Role;

public class AdminRolesService {
	
	 
    public static AdminRolesService me = new AdminRolesService();
	
	private AdminRolesService(){
		
	}
	
	public List<Role> findAllRole(){
		List<Role> rolelist =RoleDao.me.findAllRole();
		return rolelist;
				
	}
	
	public void deleteRole(int id){
		RoleDao.me.deleteRole(id);
		
	}
	public void addRole(Role role ){
		RoleDao.me.addRole(role);
	}
	
	public void updateRole(Role role){
		RoleDao.me.updateRole(role);
		
	}
	public Role findRole(int id){
		
		 return RoleDao.me.findRole(id);
		
	}
	
	

}
