package com.gp.homework.pattern.template.myJdbc;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface WayneMapper<T> {

	
	/**
	 * 用于处理result的其中一行，用于转换成对应的DTO
	 * @param rs
	 * @param indexNum
	 * @return
	 */
	T mapper(ResultSet rs,int indexNum) throws SQLException;
}
