package com.gp.homework.rocketmq.hash;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Create by Wayne on 2020/4/15
 */
public class MyConsistantHashByTreeMap {

    private static TreeMap<Long,Node> cycle = new TreeMap<>() ;
    private static List<Node> realNode = new ArrayList<>() ;
    private static Map<Long,Integer> map = new HashMap<>();


    public static void main(String[] args) {

        //徐晓峰 139 1168 2107

        MyConsistantHashByTreeMap myConsistantHashByTreeMap = new MyConsistantHashByTreeMap() ;
        myConsistantHashByTreeMap.addNode("10086") ;
        myConsistantHashByTreeMap.addNode("Fruit") ;
        myConsistantHashByTreeMap.addNode("Elitel") ;

        for(int i=0; i<5000; i++){
            myConsistantHashByTreeMap.getNode(StrUtil.format("{}",i)) ;
        }
        map.forEach((k,v)-> System.out.println(k+"*******"+v));
    }

    private void addNode(String key){
        Node node = new Node(key) ;
        realNode.add(node);
        cycle.put(node.getKeyHash(),node) ;
        System.out.println(StrUtil.format("KeyHash is ==> : {}",node.getKeyHash()));
        map.put(node.getKeyHash(),0) ;
    }

    private Node getNode(String willKey){
       return Optional.of(cycle.tailMap(md5(willKey)))
               .map(temp -> {
                   if(temp.isEmpty()){
                       System.out.println("检索的key是: "+md5(willKey)+", not hit ,合理key 是 ：" + cycle.firstKey());
                       map.put(cycle.firstKey(),map.get(cycle.firstKey()) +1) ;
                       return cycle.get(cycle.firstKey()) ;
                   }
                   System.out.println("检索的key是: "+md5(willKey)+" 匹配的合理key是：" + temp.firstKey());
                   map.put(temp.firstKey(),map.get(temp.firstKey()) +1) ;
                   return cycle.get(temp.firstKey()) ;
               })
               .orElseThrow(()->new NullPointerException("cycle is null ")) ;
    }



    public static long md5(String key){
        byte[] bKey = SecureUtil.md5(key).getBytes(StandardCharsets.UTF_8);
        return ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8)| bKey[0] & 0xFF;
    }





}

@Getter
@Setter
@ToString
@EqualsAndHashCode
class Node{
    private String Key ;
    private Long keyHash ;

    public Node(String key){
        this.Key = key ;
        this.keyHash = MyConsistantHashByTreeMap.md5(key) ;
    }
}


