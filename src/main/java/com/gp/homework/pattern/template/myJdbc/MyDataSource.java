package com.gp.homework.pattern.template.myJdbc;


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;


public class MyDataSource implements DataSource {

//	private String driverClass = "com.mysql.jdbc.Driver";
	private String driverClass = "com.mysql.cj.jdbc.Driver" ;
	private String userName = "root" ;
	private String passWord = "abc123" ;
//	private String url = "jdbc:mysql://localhost:3306/review?useUnicode=true&useSSL=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true" ;
	private String url = "jdbc:mysql://localhost:3306/review?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8" ;
	
	
	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public Connection getConnection() throws SQLException{
		return this.getConnection(this.userName, this.passWord) ;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		try {
			Class.forName(driverClass) ;
			return DriverManager.getConnection(url,username,password) ; 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	
	
	
	//=====================================================================================================================================================================================================================
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	
}
