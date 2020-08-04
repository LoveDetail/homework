package com.gp.serialization.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Create by Wayne on 2020/8/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("Person")
public class Person {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("NickName")
    private String nickname;

    @JsonProperty("Age")
    private int age;

    @JsonProperty("IdentityCode")
    private String identityCode;

    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-DD")
    private LocalDate birthday;

}
