package com.example.demo.common;

import java.util.*;

/**
 * Package： com.example.demo.common
 * Author:  hujin
 * Date: 2019/8/13 10:30
 * Description:
 * 归并排序
 * 将数据一分为二，
 * 将两部分数据分排序，
 * 然后将这两个有序数组归并为一个数组
 * Version：
 */
public class MergeSortUtilsImpl {

    /**
     *
     * 方式一：自顶向下，递归实现
     * 递归每次都指向一个新的list
     * 缺点：内存开销较大
     *
     * @param list  待排序数组
     * @param c  构造器
     * @param <T> 排序后的数组
     *
     * Description: 基础实现
     */
    private static <T> List<T>  mergeSortImpl(List<T> list, Comparator<? super T> c){
        // 数据小于等于1时 无需排序
        if (list.size() <= 1) return list;
        // 获取中点
        int mid = (list.size())/2 ;
        // 将数组划分为左右两部分
        // 要使用深度拷贝,防止缓存污染
        List<T> left = new ArrayList<T>(list.subList(0,mid));
        List<T> right = new ArrayList<T>(list.subList(mid,list.size()));
        // 对左右两部分数组进行递归
        left = mergeSortImpl(left,c);
        right = mergeSortImpl(right,c);

        // 左右归并过程
        int i = 0,j=0;
        for (int index =0;index<list.size();index++) {
            // 左部分已经放完处理右半部分
            if (i == left.size()) {
                list.set(index, right.get(j++));
                continue;
            }
            // 右部分已经放完处理左半部分
            if (j == right.size()) {
                list.set(index, left.get(i++));
                continue;
            }
            // 比较判断 讲元素小的放在list中
            if (c == null ? ((Comparable) left.get(i)).compareTo( right.get(j)) > 0:c.compare(left.get(i), right.get(j)) > 0){
                list.set(index, right.get(j++));
             }else{
                list.set(index, left.get(i++));
            }
        }
        return list;
    }

    /**
     *
     * @param list
     * @param <T>
     *
     */
    public static <T extends Comparable<? super T>> void mergeSort(List<T> list){
        mergeSortImpl(list,null);
    }


    /**
     *
     * @param list
     * @param <T>
     * 重载一个无构造器函数
     */
    public static <T> void  mergeSort(List<T> list, Comparator<? super T> c ){
        mergeSortImpl(list,c);
    }


    /**
     *
     * 方式二：自顶向下，递归实现
     * 递归每次都指向一个新的list
     * @param list  待排序数组
     * @param c  构造器
     * @param <T> 排序后的数组
     * @param left 左边界
     * @param right 右边界
     *
     * Description: 优化数组声明
     */
    private static <T> void  mergeSort2Impl(List<T> list, Comparator<? super T> c, Integer left,Integer right){
        // 数据小于等于1时 无需排序
        if ( 1>= right - left) return;
        // 获取中点
        int mid = left+ (right -left)/2 ;

        // 对左右两部分数组进行递归
        mergeSort2Impl(list,c,left,mid);
        mergeSort2Impl(list,c,mid,right);

        // 将数组划分为左右两部分
        // 要使用深度拷贝,防止缓存污染
        List<T> leftList = new ArrayList<T>(list.subList(left,mid));
        List<T> rightList = new ArrayList<T>(list.subList(mid,right));
        // 左右归并过程
        int i = 0,j = 0;
        for (int index = left;index< right;index++) {
            // 左部分已经放完处理右半部分
            if (i >= leftList.size()) {
                list.set(index, rightList.get(j++));
                continue;
            }
            // 右部分已经放完处理左半部分
            if (j >= rightList.size()) {
                list.set(index, leftList.get(i++));
                continue;
            }
            // 比较判断 讲元素小的放在list中
            if (c == null ? ((Comparable) leftList.get(i)).compareTo( rightList.get(j)) > 0:c.compare(leftList.get(i), rightList.get(j)) > 0){
                list.set(index, rightList.get(j++));
            }else{
                list.set(index, leftList.get(i++));
            }
        }
    }


    /**
     *
     * @param list
     * @param <T>
     *
     */
    public static <T extends Comparable<? super T>> void mergeSort2(List<T> list){

        mergeSort2Impl(list,null,0,list.size());
    }


    /**
     *
     * @param list
     * @param <T>
     * 重载一个无构造器函数
     */
    public static <T> void  mergeSort2(List<T> list, Comparator<? super T> c ){
        mergeSort2Impl(list,c,0,list.size());
    }


    /**
     * 方式三 ： 对第二种进行优化，当左边左部分最大值小于右部分最小值 无需排序
     * @param list
     * @param c
     * @param left
     * @param right
     * @param <T>
     */
    private static <T> void  mergeSort3Impl(List<T> list, Comparator<? super T> c, Integer left,Integer right){
        // 数据小于等于1时 无需排序
        if ( 1>= right - left) return;
        // 获取中点
        int mid = left+ (right -left)/2 ;

        // 对左右两部分数组进行递归
        mergeSort2Impl(list,c,left,mid);
        mergeSort2Impl(list,c,mid,right);

        if (c == null ? ((Comparable) list.get(mid)).compareTo(list.get(mid+1)) > -1:c.compare(list.get(mid), list.get(mid+1)) > -1) {
            return;
        }
        // 将数组划分为左右两部分
        // 要使用深度拷贝,防止缓存污染
        List<T> leftList = new ArrayList<T>(list.subList(left,mid));
        List<T> rightList = new ArrayList<T>(list.subList(mid,right));
        // 左右归并过程
        int i = 0,j = 0;
        for (int index = left;index< right;index++) {
            // 左部分已经放完处理右半部分
            if (i >= leftList.size()) {
                list.set(index, rightList.get(j++));
                continue;
            }
            // 右部分已经放完处理左半部分
            if (j >= rightList.size()) {
                list.set(index, leftList.get(i++));
                continue;
            }
            // 比较判断 讲元素小的放在list中
            if (c == null ? ((Comparable) leftList.get(i)).compareTo( rightList.get(j)) > 0:c.compare(leftList.get(i), rightList.get(j)) > 0){
                list.set(index, rightList.get(j++));
            }else{
                list.set(index, leftList.get(i++));
            }
        }
    }


    /**
     *
     * @param list
     * @param <T>
     *
     */
    public static <T extends Comparable<? super T>> void mergeSort3(List<T> list){

        mergeSort3Impl(list,null,0,list.size());
    }


    /**
     *
     * @param list
     * @param <T>
     * 重载一个无构造器函数
     */
    public static <T> void  mergeSort3(List<T> list, Comparator<? super T> c ){
        mergeSort3Impl(list,c,0,list.size());
    }


    /**
     * 方式4： 自底向上归并  迭代
     * @param list  待排序数组
     * @param c  构造器
     * @param <T> 排序后的数组
     *
     * Description: 先对每个小部分进行排序然后再依次扩大范围
     *
     */
    private static <T> void mergeSort4Impl (List<T> list, Comparator<? super T> c){
        List<T> rightList,leftList;
        for(int i = 1;i < list.size();i+=i){
            for (int j= 0;i+j<list.size();j+=2*i){
                int left= j,mid = j+i,right=j+2*i;
                if(right> list.size()) right = list.size();
                // 并归执行
                // 将数组划分为左右两部分
                // 要使用深度拷贝,防止缓存污染
                leftList = new ArrayList<T>(list.subList(left,mid));
                rightList = new ArrayList<T>(list.subList(mid,right));
                int m=0,n=0;
                for (int index = left;index<right;index ++){
                    // 左部分已经放完处理右半部分
                    if (m >= leftList.size()) {
                        list.set(index, rightList.get(n++));
                        continue;
                    }
                    // 右部分已经放完处理左半部分
                    if (n >= rightList.size()) {
                        list.set(index, leftList.get(m++));
                        continue;
                    }
                    // 比较判断 讲元素小的放在list中
                    if (c == null ? ((Comparable) leftList.get(m)).compareTo( rightList.get(n)) > 0:c.compare(leftList.get(m), rightList.get(n)) > 0){
                        list.set(index, rightList.get(n++));
                    }else{
                        list.set(index, leftList.get(m++));
                    }
                }
            }
        }
    }


    /**
     *
     * @param list
     * @param <T>
     *
     */
    public static <T extends Comparable<? super T>> void mergeSort4(List<T> list){

        mergeSort4Impl(list,null);
    }


    /**
     *
     * @param list
     * @param <T>
     * 重载一个无构造器函数
     */
    public static <T> void  mergeSort4(List<T> list, Comparator<? super T> c ){
        mergeSort4Impl(list,c);
    }


}
