package com.gp.homework.jdk.map;

public class TestMap {

    public static void main(String[] args) {
        MapService map = new MapServiceImpl();
//        map.put("a1",1);
//        map.put("a2",2);
//        System.out.println("size:"+map.size());
//        System.out.println("isEmpty:"+map.isEmpty());
//        System.out.println(map.get("a1"));

        for(int i=0; i<20; i++)
            map.put(String.valueOf(i),String.valueOf(i)) ;

        for(int i=0; i<20; i++)
            System.out.println(map.get(String.valueOf(i)));

    }
}
