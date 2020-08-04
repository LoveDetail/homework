package com.gp.homework.domain.mapper;

import com.gp.homework.controller.transaction.TransactionUser;
import org.apache.ibatis.annotations.*;

/**
 * Create by Wayne on 2020/8/4
 */
public interface MyTransactionUserMapper {

    @Select("select * from user where id = ${id}")
    TransactionUser findById(Long id) ;

    @Insert("insert into user (email,nick_name,pass_word,reg_time,user_name ) values (#{user.email},#{user.nick_name},#{user.pass_word},#{user.reg_time},#{user.user_name})")
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
    Integer insert(@Param("user") TransactionUser user) ;

    @Update("update user set nick_name = #{nikeName} where id = ${id}")
    Integer updateNikeName(@Param("id") Long id,@Param("nikeName") String nikeName) ;

    @Update("update user set user_name = #{user_name} where id = ${id}")
    Integer updateName(@Param("id") Long id,@Param("user_name") String user_name) ;

}
