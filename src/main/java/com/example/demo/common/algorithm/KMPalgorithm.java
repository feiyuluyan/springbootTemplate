package com.example.demo.common.algorithm;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Package： com.example.demo.common.algorithm
 * Author:  hujin
 * Date: 2019/8/15 20:21
 * Description: KMP字符串查找算法
 * Version：
 */
public class KMPalgorithm {

    /**
     * 不使用算法
     * 常规匹配思路
     * @param fromStr
     * @param str
     * @return
     */
    public int normalSearch (String fromStr , String str) {
        // 初始化下标,字符串长度,字符数组
        int i=0,j=0;
        int fLen = fromStr.length(),sLen = str.length();
        char[] fromChars = fromStr.toCharArray(),strChars = str.toCharArray();
        //遍历字符数组
        while(i<fLen&&j<sLen){
            // 字符匹配时, 两数组同时向后移一位
            if(fromChars[i] == strChars[j]) {
                i++;
                j++;
            }else{
                // 不匹配时,str重头开始, fromStr从开始匹配的位置向后移一位
                i = i - j + 1;
                j = 0;
            }
        }
        // 全部匹配完毕时, j下标等于str长度
        if (j == sLen) {
            return i - j;
        }else {
            return -1;
        }
    }

    public int kmpSearch (String fromStr , String str) {
        // 初始化下标,字符串长度,字符数组
        int i=0,j=0;
        int next = -1;
        int fLen = fromStr.length(),sLen = str.length();
        char[] fromChars = fromStr.toCharArray(),strChars = str.toCharArray();
        //遍历字符数组
        while(i<fLen&&j<sLen){
            // 字符匹配时, 两数组同时向后移一位
            if(j == -1 ||fromChars[i] == strChars[j]) {
                i++;
                j++;
                next ++;
            }else{
                // 不匹配时,str重头开始, fromStr从开始匹配的位置向后移一位
                j = next;
            }
        }
        // 全部匹配完毕时, j下标等于str长度
        if (j == sLen) {
            return i - j;
        }else {
            return -1;
        }
    }

}
