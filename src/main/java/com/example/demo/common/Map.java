package com.example.demo.common;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Package： com.example.demo.common
 * Author:  hujin
 * Date: 2019/9/26 16:19
 * Description: 尝试一下迷宫问题
 * Version：
 */
public class Map {
    // 初始化地图
    public int [][] map = {                           //迷宫地图,1代表墙壁，0代表通路
            {1,1,1,1,1,1,1,1,1,1},
            {1,0,0,1,0,0,0,1,0,1},
            {1,0,0,1,0,0,0,1,0,1},
            {1,0,0,0,0,1,1,0,0,1},
            {1,0,1,1,1,0,0,0,0,1},
            {1,0,0,0,1,0,0,0,0,1},
            {1,0,1,0,0,0,1,0,0,1},
            {1,0,1,1,1,0,1,1,0,1},
            {1,1,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1}
    };
    public int[] direction = {1,2,3,4}; // 东南西北

    private int mapX = map.length - 1;                //地图xy边界

    private int mapY = map[0].length - 1;

    private int startX = 1;                           //起点

    private int startY = 1;

    private int endX = mapX - 1;                      //终点

    private int endY = mapY - 1;

    // 点的类
    @Data
    public class Point {
        // 横坐标
        private int x;
        // 纵坐标
        private int y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 带方向点的类
    public class Block extends Point{
        private int dir;
        public Block(int x, int y) {
            super(x, y);
            dir = 1 ;
        }

        public int getDir() {
            return dir;
        }
        public void changeDir () {
            dir ++;
        }
    }
    /*
     *广度优先遍历用到的数据结构，它需要一个指向父节点的索引
        */
    public class WideBlock extends Point{
        private WideBlock parent;

        public WideBlock(int x , int y , WideBlock p){
            super(x , y);
            parent = p;
        }

        public WideBlock getParent(){
            return parent;
        }
    }

    Stack<Block> stack = new Stack();

    public void getWay (Block block) {
        stack.add(block);
        while (!stack.empty()) {
            Block t = stack.peek();
            int x = t.getX(),y = t.getY(),dir = t.getDir();

            map[t.getX()][t.getY()] = 1;

            if(x == endX && y == endY) {//已达终点
                return ;
            }

            switch (dir){
                case 1:
                    if (x - 1 > 0 && map[x -1][y] == 0) {
                        stack.push(new Block(x - 1 , y));
                    }
                    t.changeDir();
                    continue;
                case 2:
                    if (y + 1  <= mapY && map[x][y + 1] == 0) {
                        stack.push(new Block(x , y + 1));
                    }
                    t.changeDir();
                    continue;
                case 3:
                    if (x + 1 <= mapX && map[x + 1][y] == 0) {
                        stack.push(new Block(x + 1 , y));
                    }
                    t.changeDir();
                    continue;
                case 4:
                    if (y - 1 > 0 && map[x][y - 1] == 0) {
                        stack.push(new Block(x, y - 1));
                    }
                    t.changeDir();
                    continue;
            }
            t = stack.pop();
            map[t.getX()][t.getY()] = 0;
        }
    }

    @Test
    public void test () {
        Block block = new Block(1, 1);
        getWay(block);
        System.out.println(stack);
    }

}
