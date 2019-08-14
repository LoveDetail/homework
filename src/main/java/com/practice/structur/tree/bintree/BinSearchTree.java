package com.practice.structur.tree.bintree;

import com.practice.structur.tree.DataNode;
import com.practice.structur.tree.Tree;

/**
 * Create by Wayne on 2019/4/26
 */
public class BinSearchTree  implements Tree{
    private DataNode root ;
    private DataNode current ;

    public DataNode getRoot() {
        return root;
    }

    @Override
    public boolean insert(int data){
        DataNode tempNode = new DataNode(data) ;
        current = this.root ;
        if(current == null) {
            root = tempNode;
            return true ;
        }

        while(current != null){
            if(current.data > data){
                if(current.liftNode == null) {
                    current.liftNode = tempNode;
                    return true ;
                }

                current = current.liftNode ;
            }
            else if(current.data <= data){
                if(current.rightNode == null) {
                    current.rightNode = tempNode;
                    return  true ;
                }

                current = current.rightNode ;
            }
        }
        return false ;
    }

    @Override
    public DataNode find(int key) {
        DataNode current  = this.root ;

        while(current != null){
            if(current.data > key)
                current = current.liftNode ;
            else if(current.data < key)
                current = current.rightNode ;
            else
                return current ;
        }


        return null;
    }

    @Override //中序遍历  左中右
    public void infixOrder(DataNode current) {
        if(current != null){
            infixOrder(current.liftNode) ;
            System.out.print("["+current.data+"] -> ");
            infixOrder(current.rightNode) ;
        }
    }

    @Override //前序遍历  中左右
    public void preOrder(DataNode current) {

        if(current != null){
            System.out.print("["+current.data+"] -> ");
            preOrder(current.liftNode) ;
            preOrder(current.rightNode) ;
        }

    }

    @Override  // 后续  右中左
    public void postOrder(DataNode current) {
        if(current != null){
            postOrder(current.rightNode);
            System.out.print("["+current.data+"] -> ");
            postOrder(current.liftNode);
        }

    }

    @Override
    public DataNode findMax() {
        DataNode current ;

        if((current = this.root) != null){
            while (current.rightNode != null){
                current = current.rightNode ;
                continue;
            }
                return current ;
        }

        return null;
    }

    @Override
    public DataNode findMin() {
        DataNode current ;

        if((current = this.root) != null){
            while (current.liftNode != null){
                current = current.liftNode ;
                continue;
            }
            return current ;
        }

        return null;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }
}
