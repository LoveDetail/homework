package com.gp.homework.domain.mapper;

import com.gp.homework.domain.entity.Materiel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create by Wayne on 2020/4/29
 */
public interface MyTestMapper {

    @Select("select * from Materiel")
    List<Materiel> queryMateriel() ;
}
