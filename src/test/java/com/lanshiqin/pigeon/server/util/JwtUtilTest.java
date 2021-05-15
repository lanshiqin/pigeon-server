package com.lanshiqin.pigeon.server.util;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JWT 工具类单元测试
 *
 * @author 蓝士钦
 */
@SpringBootTest
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
    }

    @Test
    void generateToken() {
        Assertions.assertNotNull(jwtUtil.generateToken("1","蓝士钦"));
    }

    @Test
    void getJwtUserInfo() {
        String token = jwtUtil.generateToken("1","蓝士钦");
        System.out.println("token:"+token);
        JwtUserInfo jwtUserInfo = jwtUtil.getJwtUserInfo(token);
        Assertions.assertEquals("1",jwtUserInfo.getJwtUserId());
        Assertions.assertEquals("蓝士钦",jwtUserInfo.getJwtUserName());
    }


}