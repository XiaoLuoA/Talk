package test;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.EhCacheUtil;
import com.xiaoluo.utils.PathKit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheTest {
	 public  static void TestCahce()  
	    {  
	        //string测试  
	        EhCacheUtil.getInstance().put("ehcache001","userEch","test001");  
	        String val = (String) EhCacheUtil.getInstance().get("ehcache001", "userEch");  
	          
	        //object测试  
	        User user = new User();  
	        user.setId(1);  
	        user.setName("jack");  
	        EhCacheUtil.getInstance().put("ehcache001","userJack",user);  
	  
	        User user2=(User) EhCacheUtil.getInstance().get("ehcache001", "userJack");  
	        String str="1";  
	        System.out.print(user2);  
	    }  
	 
	 
	public static void main(String[] args) {
CacheTest.TestCahce();
       
    }
}
