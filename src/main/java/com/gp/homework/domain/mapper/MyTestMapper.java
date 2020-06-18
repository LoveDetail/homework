package com.gp.homework.domain.mapper;

import com.gp.homework.domain.entity.Materiel;
import com.gp.homework.domain.entity.TestUsers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create by Wayne on 2020/4/29
 */
public interface MyTestMapper {

    @Select("select * from Materiel")
    List<Materiel> queryMateriel() ;


    @Insert("insert into users (username,password,user_sex,nick_name) values (#{username},#{password},#{user_sex},#{nick_name})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn = "id")
    int insetTestUsers(TestUsers users ) ;



}
