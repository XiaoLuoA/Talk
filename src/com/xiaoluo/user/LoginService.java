package com.xiaoluo.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import com.xiaoluo.dao.MenuDao;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.Menu;
import com.xiaoluo.model.User;



public class LoginService {
	public static LoginService me = new LoginService();
	
	private LoginService(){
		
	}
	
	
	/**
	 * 根据用户名查询User对象
	 * @param name 用户名
	 * @return User对象
	 */
	public User findUser(String name){
		User user=UserDao.me.findUser(name);	
		return user;		
	}
	
	public List<Menu> getAllMenu(){
		return  MenuDao.me.findAll();
	}
	
	/**
	 * 获得用户菜单
	 */
	public List<Menu> getUserMenu(User user) {
		
		List<Menu> userMenuList = MenuDao.me.findByUser(user);
		
		//排序
		List<Menu> chainList = new ArrayList<Menu>(userMenuList);
		
		Collections.sort(chainList, new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				if(o1.getOrderNum() == null  || o2.getOrderNum() == null
						|| o1.getOrderNum() < o2.getOrderNum() ) {
					return -1;
				}
				return 0;
			}
		});
		
		formatSubMenu(chainList);
		
		List<Menu> result = new ArrayList<Menu>();
		
		for (Menu Menu : chainList) {
			if(Menu.getParentId()==null)
				result.add(Menu);
		}
		return result;
	}
	
	
	/**
	 * 获得菜单的所有父菜单
	 */
	public void getPChain(Collection<Menu> list, Menu Menu, Set<Menu> chainlist) {
		for (Menu m : list) {
			if(Menu.getParentId() == m.getId()){
				chainlist.add(m);
				getPChain(list, m, chainlist);
			}
		}
	}
	
	/**
	 * 按上下级格式化数据菜单数据
	 */
	private void formatSubMenu(Collection<Menu> list) {
		for (Menu Menu1 : list) {
			if(Menu1.getParentId()==null)
			{
				for (Menu Menu2 : list) {
					if(Menu1.getId() == Menu2.getParentId()) {
						Menu1.getSubMenuList().add(Menu2);
					}
				}
			}
		}
	}
}
