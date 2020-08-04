package com.gp.homework.pattern.build;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Create by Wayne on 2020/8/4
 */
@ToString
@EqualsAndHashCode
public class MyBuildUserDO {
   private Long id  ;
   private String name ;
   private String e_mail ;


    public static MyBuildUserDOBuilder builder(){
        return new MyBuildUserDOBuilder() ;
    }

    static class MyBuildUserDOBuilder{
        private Long id  ;
        private String name ;
        private String e_mail ;

        private MyBuildUserDOBuilder(){}



        public MyBuildUserDOBuilder setId(Long id ){
            this.id = id ;
            return this ;
        }
        public MyBuildUserDOBuilder setName(String name ){
            this.name = name ;
            return this ;
        }
        public MyBuildUserDOBuilder setE_mail(String e_mail ){
            this.e_mail = e_mail ;
            return this ;
        }

        public MyBuildUserDO build(){
            MyBuildUserDO v = new MyBuildUserDO() ;
            v.e_mail = this.e_mail ;
            v.id = this.id ;
            v.name = this.name ;
            return v ;
        }

    }

}
