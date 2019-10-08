package com.example.demo.common;

import org.apache.shiro.authc.UnknownAccountException;
import org.junit.jupiter.api.Test;

/**
 * Package： com.example.demo.common
 * Author:  hujin
 * Date: 2019/8/26 19:57
 * Description: 工具类
 * Version：
 */
public class CommonUtil {

    public static boolean isEmpty(Object object) {
        return object == null || object.toString().equals("");
    }




    private static double  H = 0; // 实时总金额

    private static int n = 0;  // 剩余人数

    private static double count = 0;

    private static  double  m = 0;  // 平均金额

    private static double sum = 0; // 统计概率中和

    public double getMoney(double p) {
        if (H <= 0 || n <= 0) {
            throw new UnknownAccountException("红包已经抢光了！");
        }
        if (m == 0) m = H/n; // 平均金额
        double random;
        double max = sum <=0 ?  p: p - sum;
        double min = sum <=0 ? -p - sum: -p;
        if (n == 1) random = 0 - sum;
        else random = Math.random()* (max -min) + min;
        sum += random;
        double data = m * random + m;  // 计算红包金额
        H -= data; // 扣除总金额
        n--;  // 人数减一
        count +=data;
        return data;
    }

    @Test
    public void test () {

        double a  = 100;
        int b = 100;
        H = a;
        n = b;
        for (int i = 0;i<b;i++){
            System.out.println(this.getMoney(0.1));
        }
        System.out.println(count);
    }
}
