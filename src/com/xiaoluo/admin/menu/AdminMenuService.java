package com.xiaoluo.admin.menu;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.xiaoluo.dao.MenuDao;
import com.xiaoluo.model.Menu;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.MenuComparator;

public class AdminMenuService {
	
	public static AdminMenuService me = new AdminMenuService();
	
	private AdminMenuService(){
		
	}
	
	
	/**
	 * 从数据库获取所有菜单，并没有任何处理
	 * @return List<Menu>
	 */
	public List<Menu> getAllMenu(){
		return  MenuDao.me.findAll();
	}
	
	
	/**
	 * 获取用户的一级菜单(一级菜单中含有二级菜单)
	 * @param user
	 * @return List<Menu> 一级菜单列表（含有二级菜单）
	 */
	public List<Menu> getUserMenu(User user) {
		List<Menu> firstMenuList = MenuDao.me.findFirstMenu(user);
		List<Menu> subMenuList = MenuDao.me.findSubMenu(firstMenuList);
		Collections.sort(firstMenuList,new MenuComparator());
		Collections.sort(subMenuList, new MenuComparator());
		formatSubMenu(firstMenuList,subMenuList);
		return firstMenuList;
	}
	
	
	
	/**
	 * 格式化菜单(将二级菜单添加到所属的一级菜单中)
	 * @param firstMenuList 一级菜单 List<Menu>
	 * @param subMenuList  二级菜单 List<Menu>
	 */
	private void formatSubMenu(Collection<Menu> firstMenuList,Collection<Menu> subMenuList) {
		for (Menu menu1 : firstMenuList) {
				for (Menu menu2 : subMenuList) {
					if(menu1.getId() == menu2.getParentId()) {
						menu1.getSubMenuList().add(menu2);
					}
				}
		}
	}
}
