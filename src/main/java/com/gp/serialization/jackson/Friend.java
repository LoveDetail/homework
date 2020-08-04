package com.gp.serialization.jackson;

import lombok.*;

/**
 * Create by Wayne on 2020/8/4
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Friend {
    private String nickname;
    private int age;
}
