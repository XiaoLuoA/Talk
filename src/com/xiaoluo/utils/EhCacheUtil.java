package com.xiaoluo.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.common.MyQueue;
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;

import net.sf.ehcache.Element;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class EhCacheUtil {
	
	private static final String path = "/ehcache.xml";  
	  
    private URL url;  
  
    private CacheManager cacheManager;  
  
    private static EhCacheUtil ehCache;  
  
    private EhCacheUtil(String path) {  
        url = getClass().getResource(path);  
        cacheManager = cacheManager.create(url);  
    }  
  
	
    public static EhCacheUtil getInstance() {  
        if (ehCache == null) {  
        	System.out.println(path);
            ehCache = new EhCacheUtil(path);  
            ehCache.put("ehcache001", "loginUser", new Ret());
            ehCache.put("ehcache001", "loginUserID", new ArrayList<String>());
            ehCache.put("ehcache001", "groupsMess", new HashMap<Integer,MyQueue<GroupsMess>>());
            ehCache.put("ehcache001", "userInGroup", new HashMap<Integer, List<User>>());
        }  
        return ehCache;  
    }  
  
    public void put(String cacheName, String key, Object value) {  
        Cache cache = cacheManager.getCache(cacheName);  
        Element element = new Element(key, value);  
        cache.put(element);  
    }  
  
    public Object get(String cacheName, String key) {  
        Cache cache = cacheManager.getCache(cacheName);  
        Element element = cache.get(key); 
        return element == null ? null : element.getObjectValue();  
    }  
  
    public Cache get(String cacheName) {  
        return cacheManager.getCache(cacheName);  
    }  
  
    public void remove(String cacheName, String key) {  
        Cache cache = cacheManager.getCache(cacheName);  
        cache.remove(key);  
    }  
    
}
