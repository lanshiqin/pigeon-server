package com.lanshiqin.pigeon.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanshiqin.pigeon.server.entity.FriendAddMessage;
import com.lanshiqin.pigeon.server.mapper.FriendAddMessageMapper;
import com.lanshiqin.pigeon.server.model.AccountRegisterRequest;
import com.lanshiqin.pigeon.server.model.AgreeFriendRequest;
import com.lanshiqin.pigeon.server.model.FriendAddRequest;
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

import javax.annotation.Resource;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 好友接口集成测试
 * 支持事务回滚
 *
 * @author 蓝士钦
 */
@SpringBootTest
@Transactional
class FriendControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FriendAddMessageMapper friendAddMessageMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addFriendReq() {
        try {
            FriendAddRequest request = new FriendAddRequest();
            request.setAccountId(1);
            request.setFriendId(1);
            request.setRemark("请求添加你为好友");
            String requestContent = JSON.toJSONString(request);
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/friend/addFriendReq")
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
            Assertions.assertEquals("添加请求已发送", jsonObject.getString("resultMsg"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void agreeAddFriend() {
        FriendAddMessage friendAddMessage = new FriendAddMessage();
        String requestId = "requestId_1";
        friendAddMessage.setRequestId(requestId);
        friendAddMessage.setAccountId(Integer.MIN_VALUE);
        friendAddMessage.setObjectAccountId(Integer.MAX_VALUE);
        friendAddMessageMapper.insert(friendAddMessage);
        try {
            AgreeFriendRequest request = new AgreeFriendRequest();
            request.setRequestId(requestId);
            request.setMessage("我通过了你的好友请求");
            String requestContent = JSON.toJSONString(request);
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/friend/agreeAddFriend")
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
            Assertions.assertEquals("已同意请求", jsonObject.getString("resultMsg"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void disAgreeAddFriend() {
        FriendAddMessage friendAddMessage = new FriendAddMessage();
        String requestId = "requestId_1";
        friendAddMessage.setRequestId(requestId);
        friendAddMessage.setAccountId(Integer.MIN_VALUE);
        friendAddMessage.setObjectAccountId(Integer.MAX_VALUE);
        friendAddMessageMapper.insert(friendAddMessage);
        try {
            AgreeFriendRequest request = new AgreeFriendRequest();
            request.setRequestId(requestId);
            request.setMessage("我拒绝了你的好友请求");
            String requestContent = JSON.toJSONString(request);
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/friend/disAgreeAddFriend")
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
            Assertions.assertEquals("已拒绝请求", jsonObject.getString("resultMsg"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void getFriendList() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.get("/friend/getFriendList")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.log())
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString(Charset.defaultCharset());
            System.out.println("接口返回结果：" + result);
            JSONObject jsonObject = JSON.parseObject(result);
            Assertions.assertEquals(200, jsonObject.getInteger("resultCode"));
            Assertions.assertEquals("好友列表", jsonObject.getString("resultMsg"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}