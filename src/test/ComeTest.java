package test;

import java.util.LinkedList;
import java.util.Queue;


public class ComeTest {
	public static final int a ;
	static{
		a = 5;
	}
	public ComeTest(){
	}
	//lxy已经进去、、
	//lxy test01
	//wyh test01		
	public static void main(String[] args) {
		;
		System.out.println(testTryCatch(10,0));//
	}
	
	public static int testTryCatch(int a,int b){
		try{
			return a/b;
		}catch(Exception e){
			return 100;
		}
	}
	

}
