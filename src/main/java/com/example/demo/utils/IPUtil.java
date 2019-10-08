package com.example.demo.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Package： com.example.demo.utils
 * Author:  hujin
 * Date: 2019/9/2 14:08
 * Description:
 * Version：
 */
public class IPUtil {

    public static String getIpaddress() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return getIpaddress(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @return the ipaddress
     */
    public static String getIpaddress(HttpServletRequest request) {
        String ipChain = request.getHeader("x-forwarded-for");
        if (null != ipChain && !ipChain.trim().isEmpty())
        {
            for (String ip : ipChain.split(","))
            {
                if (!ip.isEmpty() && !("unknown".equalsIgnoreCase(ip)))
                {
                    return ip;
                }
            }
        }
        return request.getRemoteAddr();
    }
}
