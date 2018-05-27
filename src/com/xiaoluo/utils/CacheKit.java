
package com.xiaoluo.utils;

import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * CacheKit. Useful tool box for EhCache.
 */
public class CacheKit {
	
	private static CacheManager cacheManager;
	private static Object locker = new Object();
	
	static void init(CacheManager cacheManager) {
		CacheKit.cacheManager = cacheManager;
	}
	
	public static CacheManager getCacheManager() {
		return cacheManager;
	}
	
	static Cache getOrAddCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			synchronized(locker) {
				cache = cacheManager.getCache(cacheName);
				if (cache == null) {
					cacheManager.addCacheIfAbsent(cacheName);
					cache = cacheManager.getCache(cacheName);
				}
			}
		}
		return cache;
	}
	
	public static void put(String cacheName, Object key, Object value) {
		getOrAddCache(cacheName).put(new Element(key, value));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheName, Object key) {
		Element element = getOrAddCache(cacheName).get(key);
		return element != null ? (T)element.getObjectValue() : null;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getKeys(String cacheName) {
		return getOrAddCache(cacheName).getKeys();
	}
	
	public static void remove(String cacheName, Object key) {
		getOrAddCache(cacheName).remove(key);
	}
	
	public static void removeAll(String cacheName) {
		getOrAddCache(cacheName).removeAll();
	}
	
	
}


