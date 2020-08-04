package com.gp.serialization.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by Wayne on 2020/8/4
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("FriendDetail")
@JsonIgnoreProperties({"uselessProp1", "uselessProp3"})
public class FriendDetail {

    @JsonProperty("NickName")
    private String name;

    @JsonProperty("Age")
    private int age;

    private String uselessProp1;

    @JsonIgnore
    private int uselessProp2;

    private String uselessProp3;
}
