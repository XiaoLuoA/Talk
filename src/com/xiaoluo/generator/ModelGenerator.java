package com.xiaoluo.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.xiaoluo.utils.JavaKeyword;
import com.xiaoluo.utils.StrKit;

public class ModelGenerator {
	
	//不用生成model的表
	protected Set<String> excludedTables = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	
	protected String packageTemplate =
		"package %s;%n%n";
	
	protected String classDefineTemplate =
		
		"public class %s {%n%n";
	
	protected String classDefineForSetterChainTemplate =
			
			"public class %s {%n%n";
	
	
	protected String setterTemplate =
			"\tpublic void %s(%s %s) {%n" +
				"\t\tthis.%s = %s;%n" +
			"\t}%n%n";
	
	
	protected String setterChainTemplate =
			"\tpublic %s %s(%s %s) {%n" +
				"\t\tthis.%s = %s;%n" +
				"\t\treturn this;%n" +
			"\t}%n%n";
	
	protected String getterTemplate =
			"\tpublic %s %s() {%n" +
				"\t\treturn this.%s;%n" +
			"\t}%n%n";
	
	protected String attrTemplate =
			"\tprivate %s %s;%n";
	
	protected String modelPackageName;
	
	protected String modelOutputDir;
	
	protected boolean generateChainSetter = true;
	
	protected JavaKeyword javaKeyword = JavaKeyword.me;
	
	public ModelGenerator(String modelPackageName, String modelOutputDir) {
		if (StrKit.isBlank(modelPackageName)) {
			throw new IllegalArgumentException("modelPackageName can not be blank.");
		}
		if (modelPackageName.contains("/") || modelPackageName.contains("\\")) {
			throw new IllegalArgumentException("modelPackageName error : " + modelPackageName);
		}
		if (StrKit.isBlank(modelOutputDir)) {
			throw new IllegalArgumentException("modelOutputDir can not be blank.");
		}
		
		this.modelPackageName = modelPackageName;
		this.modelOutputDir = modelOutputDir;
	}
	
	public void setGenerateChainSetter(boolean generateChainSetter) {
		this.generateChainSetter = generateChainSetter;
	}
	
	public void generate(List<TableMeta> tableMetas) {
		System.out.println("Generate model ...");
		System.out.println("Model Output Dir: " + modelOutputDir);
		for (TableMeta tableMeta : tableMetas) {
			genmodelContent(tableMeta);
		}
		
		writeToFile(tableMetas);
		
	}
	
	/**
	 * 增加不生成model的表
	 * @param excludedTables
	 */
	public void addExcludedTable(String... excludedTables) {
		if (excludedTables != null) {
			for (String table : excludedTables) {
				this.excludedTables.add(table);
			}
		}
	}
	protected boolean isSkipTable(String tableName) {
		return false;
	}
	/**
	 * 增加不生成model的表
	 * @param excludedTables
	 */
	
	protected void genmodelContent(TableMeta tableMeta) {
		StringBuilder ret = new StringBuilder();
		genPackage(ret);
		
		genClassDefine(tableMeta, ret);
		for (ColumnMeta columnMeta : tableMeta.columnMetas) {
			genAttr(columnMeta, ret);
		}
		
		for (ColumnMeta columnMeta : tableMeta.columnMetas) {
			genSetMethodName(tableMeta,columnMeta, ret);
			genGetMethodName(columnMeta, ret);
		}
		
		ret.append(String.format("}%n"));
		tableMeta.modelContent = ret.toString();
	}
	
	protected void genPackage(StringBuilder ret) {
		ret.append(String.format(packageTemplate, modelPackageName));
	}
	

	protected void genClassDefine(TableMeta tableMeta, StringBuilder ret) {
		String template = generateChainSetter ? classDefineForSetterChainTemplate : classDefineTemplate;
		ret.append(String.format(template, tableMeta.modelName));
	}
	
	protected void genSetMethodName(TableMeta tableMeta,ColumnMeta columnMeta, StringBuilder ret) {
		String setterMethodName = "set" + StrKit.firstCharToUpperCase(columnMeta.attrName);
		// 如果 setter 参数名为 java 语言关键字，则添加下划线前缀 "_"
		String argName = javaKeyword.contains(columnMeta.attrName) ? "_" + columnMeta.attrName : columnMeta.attrName;
		
		String template = generateChainSetter ? setterChainTemplate : setterTemplate;
		String setter = "";
		if(generateChainSetter){
			setter = String.format(template,tableMeta.modelName, setterMethodName, columnMeta.javaType, argName, argName, argName); 
		}else{
			setter = String.format(template, setterMethodName, columnMeta.javaType, argName, argName, argName); 
		}
		 
		ret.append(setter);
	}
	
	protected  void genAttr(ColumnMeta columnMeta,StringBuilder ret){
		String argName = javaKeyword.contains(columnMeta.attrName) ? "_" + columnMeta.attrName : columnMeta.attrName;
		ret.append(String.format(attrTemplate,columnMeta.javaType,argName ));
	}
	
	protected void genGetMethodName(ColumnMeta columnMeta, StringBuilder ret) {
		String getterMethodName = "get" + StrKit.firstCharToUpperCase(columnMeta.attrName);
		String getter = String.format(getterTemplate, columnMeta.javaType, getterMethodName, columnMeta.attrName);
		ret.append(getter);
	}
	
	protected void writeToFile(List<TableMeta> tableMetas) {
		try {
			for (TableMeta tableMeta : tableMetas) {
				
				String tableName = tableMeta.name;
				if (excludedTables.contains(tableName)) {
					System.out.println("Skip table :" + tableName);
					continue ;
				}
				if (isSkipTable(tableName)) {
					System.out.println("Skip table :" + tableName);
					continue ;
				}
				
				writeToFile(tableMeta);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void writeToFile(TableMeta tableMeta) throws IOException {
		File dir = new File(modelOutputDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		String target = modelOutputDir + File.separator + tableMeta.modelName + ".java";
		
		File file = new File(target);
		if (file.exists()) {
			return ;	// 若 Model 存在，不覆盖
		}
		
		
		FileWriter fw = new FileWriter(target);
		try {
			
			fw.write(tableMeta.modelContent);
		}
		finally {
			fw.close();
		}
	}
}






