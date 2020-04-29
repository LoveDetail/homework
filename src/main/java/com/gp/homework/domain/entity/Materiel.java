package com.gp.homework.domain.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Materiel {
    private String m_id ;
    private String m_number ;
    private String m_name ;
    private String m_model ;
    private String m_isdelete ;
    private String m_brand ;
    private String m_producing_area ;
    private String m_purchase_no ;
    private String m_games_no ;
    private String m_syjx ;
    private String m_zxt ;
    private String f_create_user ;
    private String f_create_date ;
    private String f_lastmod_date ;
    private String f_state ;
    private String f_note ;
}
