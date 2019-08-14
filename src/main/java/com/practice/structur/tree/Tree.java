package com.practice.structur.tree;

/**
 * Create by Wayne on 2019/4/26
 */
public interface Tree {

    //查找节点
    public DataNode find(int key);

    //插入新节点
    public boolean insert(int data);

    //中序遍历
    public void infixOrder(DataNode current);

    //前序遍历
    public void preOrder(DataNode current);

    //后序遍历
    public void postOrder(DataNode current);

    //查找最大值
    public DataNode findMax();

    //查找最小值
    public DataNode findMin();

    //删除节点
    public boolean delete(int key);
}
