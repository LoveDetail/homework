package com.practice.structur.tree;

import java.io.Serializable;

/**Binary Search Tree
 * Create by Wayne on 2019/4/26
 * @author wayne
 */
public class DataNode implements Cloneable,Serializable{
    public Integer data ;
    public DataNode rightNode ;
    public DataNode liftNode ;

    public DataNode(){}
    public DataNode(Integer data){this.data = data;}

    public void printData(){
        System.out.println("["+data+"]");
    }

}
