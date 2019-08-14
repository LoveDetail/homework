package com.gp.homework.pattern.template.myJdbc;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public class MyJdbcTemplate extends Jtemplate{

	public MyJdbcTemplate(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	
	public List<?> executeQuery(String sql) throws SQLException{
		
		return super.executeQuery(sql, new WayneMapper<User>() {

			@Override
			public User mapper(ResultSet rs, int indexNum) throws SQLException {
				User u = new User() ;
				u.setId(rs.getInt("id"));
				u.setUser_code(rs.getString("user_code")); 
				u.setUser_name(rs.getString("user_name"));
				u.setUser_pass(rs.getString("user_pass"));
				return u;
			}
		}) ;
		
	}
	
	
	
	
	
}
