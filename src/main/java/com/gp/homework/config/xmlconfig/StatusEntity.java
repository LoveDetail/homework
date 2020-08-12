package com.gp.homework.config.xmlconfig;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @program: uni_commu
 * @description:
 * @author:
 * @create: 2020-08-06
 **/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "StatusEntity")  // XML文件中的根标识
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class StatusEntity {
    //字段名称
    @XmlAttribute(name = "stnm")
    private String stnm;

    //字段占位
    @XmlAttribute(name = "stplace")
    private String stplace;

}