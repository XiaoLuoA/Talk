package com.xiaoluo.dialect;

public enum Dialect{	
	
	MysqlDialect,
	OracleDialect,
	AnsiSqlDialect,
	PostgreSqlDialect,
	Sqlite3Dialect,
	SqlServerDialect;
	
	public String getDialet(String tableName){
		switch (this){
			case MysqlDialect: 
				return "select * from `" + tableName + "` where 1 = 2"; 
			case OracleDialect:
				return "select * from " + tableName + " where rownum < 1";
			case PostgreSqlDialect:
				return "select * from \"" + tableName + "\" where 1 = 2";
			case Sqlite3Dialect:
			case SqlServerDialect:
			case AnsiSqlDialect:
			default:
				return "select * from " + tableName + " where 1 = 2";
		}
	}
	
}
