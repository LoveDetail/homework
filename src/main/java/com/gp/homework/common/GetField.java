package com.gp.homework.common;


import lombok.Cleanup;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@SuppressWarnings(value="unused")
public class GetField {
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException {

		String username = "wayne" ;
		String password = "Wayne)&11" ;

		//获取数据表的字段，用来生成DTO的变量 
		getDateBaseField(JdbcUrlEnum.MYSQLDRIVER8,username,password,"SELECT * FROM user LIMIT 0") ;
//		getDateBaseField(JdbcUrlEnum.SQLSERVER_MICROSOFT,username,password,"select top 0 * from test") ;
		
		//生成insert语句
//		String sql = "" ;
//		sql2Insert(JdbcUrlEnum.MYSQLDRIVER8, username,password,"ioms_stbprp_b.sql","ioms_stbprp_b", "SELECT '19841123' omoid,STCD,STTP,FRGRD,RVNM,BSNM,HNNM,BGFRYM,ESSTYM,STAZT,STATUS,OPERATOR FROM ioms_stbprp_b") ;

	}
	
	private static void getDateBaseField(JdbcUrlEnum E,String username,String password,String sql)throws ClassNotFoundException, SQLException{


		Class.forName(E.DRIVER) ;

		@Cleanup ResultSet rs = DriverManager.getConnection(E.URL,username,password).createStatement().executeQuery(sql) ;
			
		for(int i=1; i<= rs.getMetaData().getColumnCount();i++)
			System.out.println("private "+transLateField(rs.getMetaData().getColumnTypeName(i))+" "+rs.getMetaData().getColumnName(i).toLowerCase()+" ;");
		

	}
	
	private static void sql2Insert(JdbcUrlEnum E,String username,String password,String fileName,String tableName,String sql) throws ClassNotFoundException, SQLException, IOException{
		
		Class.forName(E.DRIVER) ;
		
		@Cleanup ResultSet rs = DriverManager.getConnection(E.URL, username,password).createStatement().executeQuery(sql) ;

		StringBuilder insertStr = new StringBuilder() ;
		StringBuilder valueStr = new StringBuilder() ;
		
		while(rs.next()) {
			insertStr.append("insert into ").append(tableName).append(" (") ;
			valueStr.append(" values (") ;
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
				insertStr.append(rs.getMetaData().getColumnLabel(i)) ;
				valueStr.append("'").append(rs.getObject(i)).append("'") ;
				
				if(i != rs.getMetaData().getColumnCount()){
					insertStr.append(",") ;
					valueStr.append(",") ;
				}
				else{
					insertStr.append(")") ;
					valueStr.append(") ;") ;
				}
			}
			String s = insertStr.append(valueStr).toString() ;
			System.out.println(s);
			wrightTxt(s,fileName) ;
			insertStr.setLength(0) ;
			valueStr.setLength(0) ;
		}

	}
	
	private static String transLateField(String fieldType){
//		System.out.println("***********"+fieldType);

		if( "varchar".equalsIgnoreCase(fieldType) || "char".equalsIgnoreCase(fieldType) || "nvarchar".equalsIgnoreCase(fieldType) ||"nchar".equalsIgnoreCase(fieldType))
			return "String" ;
		else 
			if(Objects.equals("TIMESTAMP",fieldType) || "datetime".equalsIgnoreCase(fieldType))
			return "Date" ;
		else 
			if("TINYINT".equalsIgnoreCase(fieldType) || "bigint".equalsIgnoreCase(fieldType) || "int".equalsIgnoreCase(fieldType) || "integer".equalsIgnoreCase(fieldType))
			return "Integer" ;
		else 
			if("decimal".equalsIgnoreCase(fieldType) || "float".equalsIgnoreCase(fieldType) || "money".equalsIgnoreCase(fieldType) || "numeric".equalsIgnoreCase(fieldType))
			return "BigDecimal" ;
		
		
		
		return "AAAAAAAAAAAAAAAAAAA" ;
	}
	
	
	private static void wrightTxt(String s,String fileName) throws IOException{

		@Cleanup
		FileWriter fileWriter=new FileWriter("D:\\"+fileName,true);

	    fileWriter.write(s+" \r\n");
	    fileWriter.flush();
	}
	

	@SuppressWarnings(value="unused")
	private enum JdbcUrlEnum{
		MYSQLDRIVER5("jdbc:mysql://localhost:3306/bstemp?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true","com.mysql.jdbc.Driver"),
		MYSQLDRIVER8("jdbc:mysql://121.36.48.218:1988/wayne?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8","com.mysql.cj.jdbc.Driver"),

		SQLSERVER_MICROSOFT("jdbc:sqlserver://192.168.2.82:1433;DatabaseName=YC5","com.microsoft.sqlserver.jdbc.SQLServerDriver"),
		SQLSERVER_JTDS("jdbc:jtds://192.168.2.82:1433;DatabaseName=YC5","net.sourceforge.jtds.jdbc.Driver"),

		ORACLEDRIVER_OJDBC8("jdbc:oracle:thin:@192.168.2.124:1521/orcl","oracle.jdbc.OracleDriver") ;


		public String URL ;
		public String DRIVER;

		JdbcUrlEnum(String URL, String DRIVER) {
			this.URL = URL;
			this.DRIVER = DRIVER;
		}
	}
	
	
	
}
