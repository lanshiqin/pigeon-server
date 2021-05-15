package com.lanshiqin.pigeon.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanshiqin.pigeon.server.model.AccountRegisterRequest;
import com.lanshiqin.pigeon.server.model.LoginRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

/**
 * 账号接口集成测试
 * 支持事务回滚
 *
 * @author 蓝士钦
 */
@SpringBootTest
@Transactional
class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        try {
            AccountRegisterRequest request = new AccountRegisterRequest();
            request.setTel("18120788750");
            request.setNickName("昵称0");
            String requestContent = JSON.toJSONString(request);
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/account/register")
                            .contentType(MediaType.APPLICATION_JSON).content(requestContent))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.log())
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString(Charset.defaultCharset());
            System.out.println("接口返回结果：" + result);
            JSONObject jsonObject = JSON.parseObject(result);
            Assertions.assertEquals(200, jsonObject.getInteger("resultCode"));
            Assertions.assertEquals("注册成功", jsonObject.getString("resultMsg"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void login() {
        try {
            LoginRequest request = new LoginRequest();
            request.setTel("18120788756");
            request.setPassword("123456");
            String requestContent = JSON.toJSONString(request);
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/account/login")
                            .contentType(MediaType.APPLICATION_JSON).content(requestContent))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.log())
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString(Charset.defaultCharset());
            System.out.println("接口返回结果：" + result);
            JSONObject jsonObject = JSON.parseObject(result);
            Assertions.assertEquals(200, jsonObject.getInteger("resultCode"));
            Assertions.assertEquals("操作成功", jsonObject.getString("resultMsg"));
            Assertions.assertNotNull(jsonObject.getJSONObject("data").getString("token"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}