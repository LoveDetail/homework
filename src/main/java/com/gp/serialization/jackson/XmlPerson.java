package com.gp.serialization.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Jackson XML除了使用Jackson JSON和JDK JAXB的一些注解之外，自己也定义了一些注解。下面简单介绍一下几个常用注解。
 *
 * @JacksonXmlProperty注解有三个属性，namespace和localname属性用于指定XML命名空间的名称，
 * isAttribute指定该属性作为XML的属性（）还是作为子标签（）.
 *
 * @JacksonXmlRootElement注解有两个属性，namespace和localname属性用于指定XML根元素命名空间的名称。
 *
 * @JacksonXmlText注解将属性直接作为未被标签包裹的普通文本表现。
 *
 * @JacksonXmlCData将属性包裹在CDATA标签中。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("XmlPerson")
public class XmlPerson {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("NickName")
    //@JacksonXmlText
    private String nickname;

    @JsonProperty("Age")
    private int age;

    @JsonProperty("IdentityCode")
//    @JacksonXmlCData
    private String identityCode;

    @JsonProperty("Birthday")
    //@JacksonXmlProperty(isAttribute = true)
    @JsonFormat(pattern = "yyyy/MM/DD")
    private LocalDate birthday;

}
