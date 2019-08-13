package com.example.demo.common;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/**
 * Package： com.example.demo.common
 * Author:  hujin
 * Date: 2019/8/8 20:44
 * Description:  组装完全二叉树 输出三种遍历
 * Version：
 */
public class TreeNode extends LinkedList{

    private static int[] array= {1,2,3,4,5,6,7,8,9};

    private static List<Node> nodeList= null;

    public static List<Integer> out = new ArrayList<>();

    /**
     * 节点
     *
     */
    private class Node{
        Node leftChild;
        Node rightChild;
        int data;

        Node(int data) {
            leftChild = null;
            rightChild = null;
            this.data = data;
        }
    }

    /**
     * 组装完全二叉树
     *
     *
     */
    public void createBinTree () {
        // 初始化
        nodeList = new LinkedList<Node>();
        // 将数组值依次转换成node
        for (int i = 0; i< array.length;i++) {
            nodeList.add(new Node(array[i]));
        }

        // 按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length/2 -1; parentIndex ++) {
            // left
            nodeList.get(parentIndex).leftChild   =    nodeList.get(parentIndex*2 +1);
            // right
            nodeList.get(parentIndex).rightChild = nodeList.get(parentIndex *2 +2);

        }

        // 最后一个节点单独处理
        int lastParentIndex = array.length/2-1;
        nodeList.get(lastParentIndex).leftChild = nodeList.get(lastParentIndex*2 +1);
        // 右孩子是否存在
        if (array.length % 2 ==  1) {
            nodeList.get(lastParentIndex).rightChild = nodeList.get(lastParentIndex * 2 +2);
        }

    }

    /**
     * 前序遍历
     * @param node
     */
    public static void pre (Node node) {
        if ( node == null) return;
        out.add(node.data);
        pre(node.leftChild);
        pre(node.rightChild);
    }

    /**
     * 中序遍历
     * @param node
     */
    public static void in (Node node) {
        if (node == null) return;
        in(node.leftChild);
        out.add(node.data);
        in(node.rightChild);
    }

    /**
     * 后序遍历
     * @param node
     */
    public static void post (Node node) {
        if (node == null) return;
        post(node.leftChild);
        post(node.rightChild);
        out.add(node.data);
    }


}
