package com.lanshiqin.pigeon.server.controller;

import com.alibaba.fastjson.JSON;
import com.lanshiqin.pigeon.server.base.BaseResponse;
import com.lanshiqin.pigeon.server.model.AccountRegisterRequest;
import com.lanshiqin.pigeon.server.model.LoginRequest;
import com.lanshiqin.pigeon.server.model.LoginResponse;
import com.lanshiqin.pigeon.server.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 账号相关接口
 *
 * @author 蓝士钦
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private AccountInfoService accountInfoService;

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody AccountRegisterRequest request) {
        logger.info("register parameter:{}", JSON.toJSONString(request));
        accountInfoService.register(request.getTel(), request.getNickName());
        return BaseResponse.success("注册成功");
    }

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        logger.info("login parameter:{}", JSON.toJSONString(request));
        return BaseResponse.success(accountInfoService.login(request.getTel(), request.getPassword()));
    }

}
