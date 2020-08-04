package com.gp.homework.pattern.build;

import lombok.Builder;
import lombok.ToString;

/**
 * Create by Wayne on 2020/8/4
 */

@Builder
@ToString
public class UserDO {
    private Long id;
    private String name;
}
