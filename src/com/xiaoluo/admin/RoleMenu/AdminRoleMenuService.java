package com.xiaoluo.admin.RoleMenu;

import java.util.List;

import com.xiaoluo.admin.menu.AdminMenuService;
import com.xiaoluo.dao.RoleMenuDao;
import com.xiaoluo.model.RoleMenu;

public class AdminRoleMenuService {
	
	public static AdminRoleMenuService me = new AdminRoleMenuService();
	
	public List<RoleMenu> listAllRoleMenu(){
		return RoleMenuDao.me.findAllRoleMenu();
		
	}
	
	public void addRoleMenu(RoleMenu rolemenu){
		RoleMenuDao.me.addRoleMenu(rolemenu);
	}
	
	public void updateRoleMenu(RoleMenu rolemenu){
		RoleMenuDao.me.updateRoleMenu(rolemenu);
	}
	public void deleteRoleMenu(int id){
		RoleMenuDao.me.deleteRoleMenu(id);
	}
	public RoleMenu findRoleMenu(int id){
		return RoleMenuDao.me.findRoleMenu(id);
	}
	
	

}
