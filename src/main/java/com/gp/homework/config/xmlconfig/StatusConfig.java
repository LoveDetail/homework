package com.gp.homework.config.xmlconfig;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @program: uni_commu
 * @description:
 * @author:
 * @create: 2020-08-06
 **/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Config") // XML文件中的根标识
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class StatusConfig {
    @XmlElement(name = "appType")
    public int appType;

    @XmlElement(name = "deviceName")
    public String deviceName;

    @XmlElement(name = "sequence")
    public String sequence;

    @XmlElement(name = "lenlist")
    public String lenlist;

    @XmlElementWrapper(name = "stenList")
    @XmlElement(name = "StatusEntity")
    public List<StatusEntity> stenList;

    Map<String,StatusEntity> stmap;


}