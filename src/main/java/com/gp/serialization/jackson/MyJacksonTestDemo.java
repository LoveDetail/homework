package com.gp.serialization.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by Wayne on 2020/8/4
 */
public class MyJacksonTestDemo {


    public static void main(String[] args) throws IOException {


        xmlPersonDemo() ;

//        java8DateTime() ;

//        configDemo() ;

        //容器转json
//        collations();

        //实体类转json
//        entityDemo();
    }


    @SneakyThrows
    private static void xmlPersonDemo(){
        XmlPerson p1 = new XmlPerson("yitian", "易天", 25, "10000", LocalDate.of(1994, 1, 1));
        XmlMapper mapper = new XmlMapper();
        mapper.findAndRegisterModules();

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String text = mapper.writeValueAsString(p1);
        System.out.println(text);
        XmlPerson p2 = mapper.readValue(text, XmlPerson.class);
        System.out.println(p2);
    }


    @SneakyThrows
    private static void entityDemo(){
        ObjectMapper mapper = new ObjectMapper();
        Friend friend = new Friend("yitian", 25);

        // 写为字符串
        String text = mapper.writeValueAsString(friend);
        // 写为文件
        mapper.writeValue(new File("friend.json"), friend);
        // 写为字节流
        byte[] bytes = mapper.writeValueAsBytes(friend);
        System.out.println(text);
        // 从字符串中读取
        Friend newFriend = mapper.readValue(text, Friend.class);

        // 从字节流中读取
        newFriend = mapper.readValue(bytes, Friend.class);

        // 从文件中读取
        newFriend = mapper.readValue(new File("friend.json"), Friend.class);
        System.out.println(newFriend);
    }



    @SneakyThrows
    private static void collations(){
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();
        map.put("age", 25);
        map.put("name", "yitian");
        map.put("interests", new String[]{"pc games", "music"});

        String text = mapper.writeValueAsString(map);
        System.out.println(text);

        Map<String, Object> map2 = mapper.readValue(text, new TypeReference<Map<String, Object>>() {});
        System.out.println(map2);

        JsonNode root = mapper.readTree(text);
        String name = root.get("name").asText();
        int age = root.get("age").asInt();

        System.out.println("name:" + name + " age:" + age);
    }


    /**
     * 由于设置了排除的属性，所以生成的JSON和Java类并不是完全对应关系，
     * 所以禁用DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES是必要的
     */
    @SneakyThrows
    private static void configDemo(){
        ObjectMapper mapper = new ObjectMapper();

        //增加带{"FriendDetail":{"NickName":"yitian","Age":25}}
//        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        FriendDetail fd = new FriendDetail("yitian", 25, "", 0, "");

        String text = mapper.writeValueAsString(fd);
        System.out.println(text);

        FriendDetail fd2 = mapper.readValue(text, FriendDetail.class);
        System.out.println(fd2);
    }

    static void java8DateTime() throws IOException {
        Person p1 = new Person("yitian", "易天", 25, "10000", LocalDate.of(1994, 1, 1));
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String text = mapper.writeValueAsString(p1);
        System.out.println(text);

        Person p2 = mapper.readValue(text, Person.class);
        System.out.println(p2);
    }

    private static void timeMoudle(){
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module());

        mapper.findAndRegisterModules();

        // 美化输出
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 允许序列化空的POJO类
        // （否则会抛出异常）
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 把java.util.Date, Calendar输出为数字（时间戳）
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


        // 在遇到未知属性的时候不抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 强制JSON 空字符串("")转换为null对象值:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 在JSON中允许C/C++ 样式的注释(非标准，默认禁用)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        // 允许没有引号的字段名（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        // 允许单引号（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // 强制转义非ASCII字符
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);

        // 将内容包裹为一个JSON属性，属性名由@JsonRootName注解指定
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);


    }

}


