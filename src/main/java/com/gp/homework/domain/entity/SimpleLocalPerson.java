package com.gp.homework.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Create by Wayne on 2020/8/5
 */

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SimpleLocalPerson {
    private String p_id ;
    private String p_number ;
    private String p_name ;
    private Integer p_age ;
    private String p_certificate ;
    private String p_address ;
    private String p_username ;
    private String p_password ;
    private String p_departmentid ;
    private String p_email ;
    private String p_phone ;

//    @JsonSerialize(using = LocalDateTimeConverter.class) //以时间戳返回给前端
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss") //以json返回给前端
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 以pattern格式从前端接收到的json进行格式化
    private LocalDateTime p_createdate ;

//    @JsonSerialize(using = LocalDateTimeConverter.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime p_modifydate ;

    private String p_note ;

}
