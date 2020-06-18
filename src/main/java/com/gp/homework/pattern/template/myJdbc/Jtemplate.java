package com.gp.homework.pattern.template.myJdbc;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class Jtemplate {

	private DataSource dataSource ;
	
	public Jtemplate(DataSource dataSource) {
		this.dataSource = dataSource ;
	}
	
	public List<?> executeQuery(String sql,WayneMapper<?> mapper) throws SQLException{
		
//		String sql = "SELECT * FROM `user`" ;
		
		Connection conn = this.getConnection(dataSource) ;
		PreparedStatement state = this.getStatement(conn, sql) ;
		ResultSet rs = this.getResultSet(state) ;
		
		List<?> list = this.querySql(rs, mapper) ;
		
		this.clossAll(rs, state, conn);
		
		return list ;
	}
	
	
	private Connection getConnection(DataSource dataSource) throws SQLException {
		return dataSource.getConnection() ;
	}
	
	private java.sql.PreparedStatement getStatement(Connection conn,String sql) throws SQLException {
		return conn.prepareStatement(sql) ;
	}
	
	private ResultSet getResultSet(PreparedStatement statement) throws SQLException {
		return statement.executeQuery();
	}
	
	private List<?> querySql(ResultSet rs,WayneMapper<?> mapper) throws SQLException{
		
		List<Object> list = new ArrayList<>() ;
		
        while (rs.next()){
        	list.add(mapper.mapper(rs));
        }
        return list;
	}
	
	private void clossAll(ResultSet rs,PreparedStatement ps,Connection conn) throws SQLException {
		rs.close();ps.close();conn.close(); 
	}
}
