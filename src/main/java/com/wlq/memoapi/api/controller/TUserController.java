package com.wlq.memoapi.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlq.memoapi.api.domain.TUser;
import com.wlq.memoapi.api.service.TUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: tjm
 * @date: 2024-10-23 023 15:42
 * @Description: tips: 代码不规范 同事两行泪
 */
// 登录校验：只有登录之后才能进入该方法
@SaCheckLogin
@RestController
@RequestMapping("/User")
public class TUserController {

    // 用于存储用户的重试次数和最后一次尝试时间
    private static final ConcurrentHashMap<String, Integer> retryCounts = new ConcurrentHashMap<>();

    private static final int MAX_RETRIES = 5; // 最大重试次数

    @Autowired
    private TUserService tUserService;

    // 会话登录接口
    // 此接口加上了 @SaIgnore 可以游客访问
    @SaIgnore
    @RequestMapping("doLogin")
    public SaResult doLogin(String userid, String pwd, HttpServletRequest request) {
        // 获取客户端的 IP 地址
        String ipAddress = getClientIp(request);
        String userKey = userid + "_" + ipAddress; // 使用用户ID和IP地址作为唯一键

        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TUser::getUserId, userid);
        TUser one = tUserService.getOne(queryWrapper);
        if (one == null) {
            return SaResult.error("账号不存在！");
        }
        // 第一步：比对前端提交的账号名称、密码
        if(userid.equals(one.getUserId().toString()) && pwd.equals(one.getPassword())) {
            // 校验指定账号是否已被封禁，如果被封禁则抛出异常 `DisableServiceException`
            StpUtil.checkDisable(userKey);
            // 登录成功，重置重试次数
            resetRetryCount(userKey);
            // 第二步：根据账号id，进行登录
            StpUtil.login(one.getUserId());
            return SaResult.ok("登录成功");
        }
        incrementRetryCount(userKey); // 登录失败，增加重试次数
        return SaResult.error("登录失败，账号或密码错误！");
    }

    // 获取客户端 IP 地址的方法
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            // 可能会有多个IP，用逗号分隔，取第一个
            String[] ips = ip.split(",");
            return ips[0].trim();
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        // 获取远程地址
        ip = request.getRemoteAddr();

        // 检查是否是IPv6格式，如果是，返回IPv4地址
        if ("::1".equals(ip)) {
            return "127.0.0.1"; // 将回环IPv6地址转换为IPv4地址
        }

        return ip;
    }


    // 增加重试次数
    private void incrementRetryCount(String userKey) {
        retryCounts.merge(userKey, 1, Integer::sum);

        // 如果超过最大重试次数，则锁定
        if (retryCounts.get(userKey) >= MAX_RETRIES) {
            // 校验指定账号是否已被封禁，如果被封禁则抛出异常 `DisableServiceException`
            StpUtil.checkDisable(userKey);
            // 锁定用户
            // 封禁指定账号
            StpUtil.disable(userKey, 86400/24/12);
            // 可以在这里发送通知等
        }
    }

    // 重置重试次数
    private void resetRetryCount(String userKey) {
        retryCounts.remove(userKey);
    }

    // 查询登录状态
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 注销
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    // 注销
    @SaIgnore
    @RequestMapping("untieDisable")
    public SaResult untieDisable(String userid, HttpServletRequest request) {
        // 获取客户端的 IP 地址
        String ipAddress = getClientIp(request);
        String userKey = userid + "_" + ipAddress; // 使用用户ID和IP地址作为唯一键
        // 解封：指定账号的指定服务
        StpUtil.untieDisable(userKey);
        return SaResult.ok("userKey:"+userKey+"，已解封");
    }
}
