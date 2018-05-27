package com.xiaoluo.generator;

import java.util.List;

import javax.sql.DataSource;

import com.xiaoluo.dialect.Dialect;


public class Generator {
	
	//基本项构建
	protected MetaBuilder metaBuilder;
	
	//Model构建
	protected ModelGenerator modelGenerator;
	
	//数据字典构建
	protected DataDictionaryGenerator dataDictionaryGenerator;
	
	//是否生成数据字典(默认生成)
	protected boolean generateDataDictionary = true;
	
	
	/**
	 * 构造 Generator
	 * @param dataSource 数据源
	 * @param modelPackageName  model 包名
	 * @param modelOutputDir  mode 输出目录
	 */
	public Generator(DataSource dataSource, String modelPackageName, String modelOutputDir) {
		this(dataSource, new ModelGenerator(modelPackageName, modelOutputDir));
	}
	
	public Generator(DataSource dataSource, ModelGenerator modelGenerator) {
		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource can not be null.");
		}
		if (modelGenerator == null) {
			throw new IllegalArgumentException("ModelGenerator can not be null.");
		}
		
		this.metaBuilder = new MetaBuilder(dataSource);
		
		this.modelGenerator = modelGenerator;
		
		this.dataDictionaryGenerator = new DataDictionaryGenerator(dataSource,"src");
	}
	
	
	
	/**
	 * 设置 MetaBuilder，便于扩展自定义 MetaBuilder
	 */
	public void setMetaBuilder(MetaBuilder metaBuilder) {
		if (metaBuilder != null) {
			this.metaBuilder = metaBuilder;
		}
	}
	
	
	
	public void setTypeMapping(TypeMapping typeMapping) {
		this.metaBuilder.setTypeMapping(typeMapping);
	}
	
	
	/**
	 * 设置 DataDictionaryGenerator，便于扩展自定义 DataDictionaryGenerator
	 */
	public void setDataDictionaryGenerator(DataDictionaryGenerator dataDictionaryGenerator) {
		if (dataDictionaryGenerator != null) {
			this.dataDictionaryGenerator = dataDictionaryGenerator;
		}
	}
	
	/**
	 * 设置数据库方言，默认为 MysqlDialect
	 */
	public void setDialect(Dialect dialect) {
		metaBuilder.setDialect(dialect);
	}
	
	/**
	 * 设置 BaseMode 是否生成链式 setter 方法
	 */
	public void setGenerateChainSetter(boolean generateChainSetter) {
		modelGenerator.setGenerateChainSetter(generateChainSetter);
	}
	
	/**
	 * 设置需要被移除的表名前缀，用于生成 modelName 
	 * 例如表名  "osc_account"，移除前缀 "osc_" 后变为 "account"
	 */
	public void setRemovedTableNamePrefixes(String... removedTableNamePrefixes) {
		metaBuilder.setRemovedTableNamePrefixes(removedTableNamePrefixes);
	}
	
	/**
	 * 添加不需要处理的数据表
	 */
	public void addExcludedTable(String... excludedTables) {
		modelGenerator.addExcludedTable(excludedTables);
	}
	
	
	/**
	 * 设置是否生成数据字典 Dictionary 文件，默认不生成
	 */
	public void setGenerateDataDictionary(boolean generateDataDictionary) {
		this.generateDataDictionary = generateDataDictionary;
	}
	
	
	/**
	 * 设置数据字典 DataDictionary 文件输出目录，默认与 modelOutputDir 相同
	 */
	public void setDataDictionaryOutputDir(String dataDictionaryOutputDir) {
		if (this.dataDictionaryGenerator != null) {
			this.dataDictionaryGenerator.setDataDictionaryOutputDir(dataDictionaryOutputDir);
		}
	}
	
	/**
	 * 设置数据字典 DataDictionary 文件名字，默认值为 "_DataDictionary.txt"
	 */
	public void setDataDictionaryFileName(String dataDictionaryFileName) {
		if (dataDictionaryGenerator != null) {
			dataDictionaryGenerator.setDataDictionaryFileName(dataDictionaryFileName);
		}
	}
	
	
	
	public void generate() {
		long start = System.currentTimeMillis();
		List<TableMeta> tableMetas = metaBuilder.build();
		
		
		if (tableMetas.size() == 0) {
			System.out.println("TableMeta 数量为 0，不生成任何文件");
			return ;
		}
		
		modelGenerator.generate(tableMetas);
		
		if (dataDictionaryGenerator != null && generateDataDictionary) {
			
			dataDictionaryGenerator.generate(tableMetas);
		}
		
		long usedTime = (System.currentTimeMillis() - start) / 1000;
		System.out.println("Generate complete in " + usedTime + " seconds.");
	}
}



