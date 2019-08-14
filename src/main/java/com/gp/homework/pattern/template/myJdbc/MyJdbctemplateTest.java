package com.gp.homework.pattern.template.myJdbc;


import java.sql.SQLException;
import java.util.List;

public class MyJdbctemplateTest {
	
	public static void main(String[] args) throws SQLException {
		
		String sql = "select id,user_code,user_name,user_pass from `user`" ;
		
		MyDataSource dataSource = new MyDataSource() ;
		
		MyJdbcTemplate j = new MyJdbcTemplate(dataSource) ;
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) j.executeQuery(sql) ;
		
		
		if(list != null && list.size() != 0) {
			for(User u : list) {
				System.out.println(u.toString());
			}
		}
	}
	

}
