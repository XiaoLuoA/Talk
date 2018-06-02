package com.xiaoluo.common;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue<T> {
	
	Queue<T> queue = new LinkedList<T>();
	
	private int size = 0;
	
	public MyQueue(int size){
		
		this.size = size;
		
	}
	
	public MyQueue<T> add(T t){
		if(queue.size() == size)
			queue.poll();
		queue.offer(t);
		return this;
	}
	
	public void poll(){
		queue.poll();
	}
	
	public void clearAll(){
		queue.clear();
	}
	
}
