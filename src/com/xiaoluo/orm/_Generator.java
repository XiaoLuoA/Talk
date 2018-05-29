package com.xiaoluo.orm;

import javax.sql.DataSource;

import com.xiaoluo.generator.Generator;

public class _Generator
{
	public static DataSource getDataSource() 
	{
		return new MyDataSource();
	}
	
	public static void main(String[] args) {
		//model 所使用的包名
		String modelPackageName = "com.xiaoluo.model";
		
		//model 文件保存路径
		String modelOutputDir = "src/test/com/xiaoluo/model";
		
		// 创建生成器
		Generator generator = new Generator(getDataSource(), modelPackageName, modelOutputDir);
		
		//添加不用生成的model的表名
		//generator.addExcludedTable("Record","Server","Allmessage");
		
		// 设置是否生成链式 setter 方法
		generator.setGenerateChainSetter(true);
		
		// 运行啦啦啦
		generator.generate();
	}
}
