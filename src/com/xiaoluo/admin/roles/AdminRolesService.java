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
	public void addRole(Role role ,int s[]){
		RoleDao.me.addRole(role, s);
	}
	
	public void updateRole(int roleid ,int menuid,int updateMenuId){
		RoleDao.me.updateRole(roleid, menuid, updateMenuId);
		
	}
	public Role findRole(int id){
		
		 return RoleDao.me.findRole(id);
		
	}
	
	

}
