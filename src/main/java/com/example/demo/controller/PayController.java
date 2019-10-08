package com.example.demo.controller;

import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.MethodType;
import com.egzosn.pay.common.bean.PayOrder;
import com.egzosn.pay.common.http.HttpConfigStorage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * Package： com.example.demo.controller
 * Author:  hujin
 * Date: 2019/9/18 14:30
 * Description:
 * Version：
 */
@RestController
public class PayController {

    private PayService service = null;

    @PostConstruct
    public void init(){
        AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
        //  合作者id
        aliPayConfigStorage.setPid("2088102179208192");
        // 应用id
        aliPayConfigStorage.setAppid("2016101200667384");
        // 支付宝公钥
        aliPayConfigStorage.setKeyPublic("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqdJak54KrTeLlQd20Un7c9xYmknLWWhNwX+DOH1MDoX+qX1t9ViI2WUaoyE/HywqBP7olvllSg0MPdP3tkUZHCmHh1ON1MDW46UlaDDRtxG59fpfGdJQqerLLpW9wAzdRz1ghYgYbrRgk/xqObX7rrQzpYcuETrT/Vpo5lJ4gsnCpl5EV3sfuckEzBr8s8cmdJbD9WG2rrS8JAkz3Vwr6UojiAk65UeEnI9WssQ/jg61OHig0GYNTH6UhRI1jHD/RYC30ra39037cDWpwWdjUuRQiK4kZhloZCTmum3P27tPeEBath1ze1c8bwSwUZOBC+bG9JeViqYeB6lYRzls2wIDAQAB");
        // 支付宝私钥
        aliPayConfigStorage.setKeyPrivate("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9SyYx4KRyWP9uRbOMCH/gh5UpPnO7+GBWGT0aH5SWZF/9pgi31RbUl0p7PnUPtlwt0wQ6bb+A9oABVtVx0/Ea1PO9QKhBAkRzQhvtevOJsEyTOHiUFsi4CxDsx7UrbdOdIr/zFaFjqpFSuy1HeXcw6B4iZKuAjqcpkTPyvpdytvaJ0wmC+o7FzchpChCcXrT8eIRD/rVXUHwX1YaTEd+YbgTkE/fJI+Euyzzyj7ZnCdYmVrJMYgA/s+3v4GGYgPOxHdkfUyc5Kz8pJfZUDCRZCUphWRLpSVILwlrkeOSxnNnbRfP8msLfOq09S+b8HZ4gwx/ME+e1ITSpvO3Xw1rLAgMBAAECggEAMzdWCkDBvb6evfNV9RmHn/SPQy2GmuFFD4acpajNxzM5VaPK0BJzrumQWXA4tBufLTbe+gAfxIDteW9JKRczMRNVL2uDGrPGWXkVnVea9TIiReMNnOPdQw6bcbVHK4z3qJhyXw8HL7RCxaOnSKNwwGbNYHjkflAaooCLEa7Hw12OjUWpg3tSVdW2hrXwDAMH8LBBbAi/+rjx5brUHXbNGCiYRP4NGN+I87LwrL+8ZYZ35xwbJR1Oq80OedC1fGPpo0QhyXNbJbBXKo0LlQOJ3kM8a3LFCm3Kmj61ySVuOEDi9rZdMwRq7vdc9rsm5+WdE00VDjlvyE0GqmWa5n2PwQKBgQDe3OHuSjeAdDsum+7qfO/a2Lf9QDuC/WSXTTnSnQW9lZSJpzU1jvT6JGYqXCorAgXgkKHljayrjq4PsgyFOhVKAipyN9KVTR63vEzHUArS71Vhvie5O/rpi9y+kUq6sIC99ewIx1Uh/QWcpn/USAdWrgOvsHZGHyvS/Gsw93bL+QKBgQDZcHnQptGj3v+gCC0gb22j3CQOQgq/RAV3ql+7sfoxnkjfWeJd4CTT79T79FkN43Gafgd2n7+c+hQ29bYSlDjfWANw3zpH/ReaurDJWSOx08Vs9XBKEUlA8PMuX5uMnWQEdKoTRAhFaydv5uSXtmO0wNnEJGpSRY+HG0TXUt2l4wKBgQC5oUoe7XqlaCsTe5bZgg9XPkSikSoAxYdP72dGYrwxWbJLphbP8a9j24Wtqu8Eu76helcfmY5Fp6acNDXxkpvA9WTdllm1OnNnn1Rkf+tEH04DCByWUbuBZJoNsX+JWRMjJv/foAjtMo/GF3lKHGTgcjln0ZfyeGxMZSvzsWwmIQKBgQCWcIJngTOkfGJCN8X5Hf8ukt4DW2ASKx7ZR3CK7T2cXNYwagD7UFxKojGqzMlcmZN8/1tskGRDWKbpy6DYQ+MBa+SdkGTDb/GH5UjxVhIjM9DxbPLu2/zhyJOH7zAw3qhXiNwvDi/mz5dujcJ00QU1BwTj9wn0uBnwsUY08ClYXwKBgEIfe3Zz44jlqqTX0sD8cyfQJN8D2glIJ6RyLSHpxclxBknvcp0BvouN5V2NADpLhjH/TWMGzrPfZ31t4rJ9yEUBHir5bWQQaQL47VpSqrVgoodZ6/Pzu5QqvyTB3nOjuOw6QMQRQGDTFNpfsyY/RxWGZGFlh592kDVgYgL0L1Zw");
        // 异步回调地址
        aliPayConfigStorage.setNotifyUrl("www.baidu.com");
        // 同步回调地址
        aliPayConfigStorage.setReturnUrl("www.baidu.com");
        // 签名方式
        aliPayConfigStorage.setSignType("RSA");
        // 收款账号
        aliPayConfigStorage.setSeller("17521715426");
        // 编码
        aliPayConfigStorage.setInputCharset("utf-8");
        //是否为测试账号，沙箱环境
        aliPayConfigStorage.setTest(true);

        //请求连接池配置
        HttpConfigStorage httpConfigStorage = new HttpConfigStorage();
        //最大连接数
        httpConfigStorage.setMaxTotal(20);
        //默认的每个路由的最大连接数
        httpConfigStorage.setDefaultMaxPerRoute(10);
        service =  new AliPayService(aliPayConfigStorage, httpConfigStorage);
        //以下拦截器与处理器用于支付与业务隔离简化版回调
//        //增加支付回调消息拦截器
//        service.addPayMessageInterceptor(new AliPayMessageInterceptor());
//        //设置回调消息处理
//        service.setPayMessageHandler(spring.getBean(AliPayMessageHandler.class));

    }

    /**
     * 跳到支付页面
     * 针对实时支付,即时付款
     * @return
     */
    @RequestMapping(value = "toPay.html", produces = "text/html;charset=UTF-8")
    public String toPay() {

        PayOrder order = new PayOrder("订单title", "摘要", new BigDecimal(0.01) , UUID.randomUUID().toString().replace("-", ""));
        //网页支付
        order.setTransactionType(AliTransactionType.DIRECT);
        //获取支付订单信息
        Map orderInfo = service.orderInfo(order);
        //组装成html表单信息
        return  service.buildRequest(orderInfo, MethodType.POST);
    }


    /**
     * 获取二维码图像
     * 二维码支付
     * @return
     */
    @RequestMapping(value = "toQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
    public byte[] toWxQrPay( ) throws IOException {
        PayOrder order = new PayOrder("订单title", "摘要", new BigDecimal(0.01) , UUID.randomUUID().toString().replace("-", ""));
        //扫码付
        order.setTransactionType(AliTransactionType.SWEEPPAY);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(service.genQrPay(order), "JPEG", baos);
        return baos.toByteArray();
    }

    /**
     * 支付回调地址 方式一
     *
     * 方式二，{@link #payBack(HttpServletRequest)} 是属于简化方式， 试用与简单的业务场景
     *
     *
     * @param request 请求
     *
     * @return 返回对应的响应码
     * @see #payBack(HttpServletRequest)
     */
    @Deprecated
    @RequestMapping(value = "payBackBefore.json")
    public String payBackBefore(HttpServletRequest request) throws IOException {

        //获取支付方返回的对应参数
        Map<String, Object> params = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null == params) {
            return service.getPayOutMessage("fail", "失败").toMessage();
        }

        //校验
        if (service.verify(params)) {
            //这里处理业务逻辑
            //......业务逻辑处理块........
            return service.getPayOutMessage("success", "成功").toMessage();
        }

        return service.getPayOutMessage("fail", "失败").toMessage();
    }
    /**
     * 支付回调地址
     *
     * @param request 请求
     *
     * @return 返回对应的响应码
     *
     * 业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看{@link com.egzosn.pay.common.api.PayService#setPayMessageHandler(com.egzosn.pay.common.api.PayMessageHandler)}
     *
     * 如果未设置 {@link com.egzosn.pay.common.api.PayMessageHandler} 那么会使用默认的 {@link com.egzosn.pay.common.api.DefaultPayMessageHandler}
     *
     */
    @RequestMapping(value = "payBack.json")
    public String payBack(HttpServletRequest request) throws IOException {
        //业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看com.egzosn.pay.common.api.PayService.setPayMessageHandler()
        return service.payBack(request.getParameterMap(), request.getInputStream()).toMessage();
    }
}
