package com.gp.homework.domain.mapper;

import com.gp.homework.domain.entity.SimpleLocalPerson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create by Wayne on 2020/8/5
 */
public interface MyTestLocalDateTimeMapper {

    @Select("SELECT * FROM person  ORDER BY p_id DESC LIMIT 0,20")
    List<SimpleLocalPerson> queryPerson() ;

    @Insert("insert into person (p_id,p_number,p_name,p_age,p_certificate,p_address,p_username,p_password,p_departmentid,p_email,p_phone,p_createdate,p_modifydate,p_note) values " +
            "(#{p_id},#{p_number},#{p_name},#{p_age},#{p_certificate},#{p_address},#{p_username},#{p_password},#{p_departmentid},#{p_email},#{p_phone},#{p_createdate},#{p_modifydate},#{p_note})")
    int insertPerson(SimpleLocalPerson person) ;

}
