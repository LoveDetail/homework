package com.practice;

import com.practice.structur.tree.bintree.BinSearchTree;

import java.util.Random;

/**
 * Create by Wayne on 2019/4/26
 */
public class MyPracticeJava {

    public static void main(String[] args) {

        BinSearchTree binTree = new BinSearchTree() ;
//        BeanFactory
        for(int i=1; i<=10000; i++)
            binTree.insert(new Random().nextInt(100000000)) ;

        binTree.infixOrder(binTree.getRoot());
        System.out.println();
        binTree.preOrder(binTree.getRoot()); ;
        System.out.println();
        binTree.postOrder(binTree.getRoot()); ;

        System.out.println();
        System.out.println("最大值是=="+binTree.findMax().data);
        System.out.println("最小值是=="+binTree.findMin().data);

        System.out.println(binTree.find(binTree.findMin().data).data+"*********"+binTree.find(binTree.findMax().data).data);

    }

}
