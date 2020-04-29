package com.gp.homework.callback;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Create by Wayne on 2020/4/28
 */
@Setter
@Getter
@EqualsAndHashCode
public class ClientRequest {
    String rtucode ;
    String type ;

    public ClientRequest(String rtucode, String type) {
        this.rtucode = rtucode;
        this.type = type;
    }
}
