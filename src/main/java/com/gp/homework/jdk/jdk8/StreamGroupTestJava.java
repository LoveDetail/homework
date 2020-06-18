package com.gp.homework.jdk.jdk8;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Create by Wayne on 2020/5/28
 */
public class StreamGroupTestJava {


    public static void main(String[] args) {
        case1() ;
    }


    private static void case1(){
        List<String> items =
                Arrays.asList("apple", "apple", "banana","apple", "orange", "banana", "papaya");

        Map<String, List<String>> result1 = items.stream().collect(Collectors.groupingBy(Function.identity()));
        System.out.println(result1);


        Map<String, Long> result2 = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result2);



        Map<String, Long> finalMap = new LinkedHashMap<>();
        result2.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        System.out.println(finalMap);
    }



}
