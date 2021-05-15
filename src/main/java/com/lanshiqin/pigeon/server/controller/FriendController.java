package com.lanshiqin.pigeon.server.controller;

import com.alibaba.fastjson.JSON;
import com.lanshiqin.pigeon.server.Interceptor.JwtInterceptor;
import com.lanshiqin.pigeon.server.base.BaseResponse;
import com.lanshiqin.pigeon.server.model.AgreeFriendRequest;
import com.lanshiqin.pigeon.server.model.FriendAddRequest;
import com.lanshiqin.pigeon.server.model.FriendListItem;
import com.lanshiqin.pigeon.server.service.AccountRelationService;
import com.lanshiqin.pigeon.server.service.FriendAddMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

    @Resource
    private FriendAddMessageService friendAddMessageService;

    @Resource
    private AccountRelationService accountRelationService;

    @Resource
    private JwtInterceptor jwtInterceptor;

    @PostMapping("/addFriendReq")
    public BaseResponse<String> addFriendReq(@RequestBody FriendAddRequest request) {
        logger.info("addFriendReq parameter: {}", JSON.toJSONString(request));
        String accountId = jwtInterceptor.getUserInfo().getJwtUserId();
        friendAddMessageService.addFriendReq(Integer.valueOf(accountId), request.getFriendId(), request.getRemark());
        return BaseResponse.success("添加请求已发送");
    }

    @PostMapping("/agreeAddFriend")
    public BaseResponse<String> agreeAddFriend(@RequestBody AgreeFriendRequest request) {
        logger.info("agreeAddFriend parameter: {}", JSON.toJSONString(request));
        friendAddMessageService.agreeAddFriend(request.getRequestId(), request.getMessage());
        return BaseResponse.success("已同意请求");
    }

    @PostMapping("/disAgreeAddFriend")
    public BaseResponse<String> disAgreeAddFriend(@RequestBody AgreeFriendRequest request) {
        logger.info("disAgreeAddFriend parameter: {}", JSON.toJSONString(request));
        friendAddMessageService.disAgreeAddFriend(request.getRequestId(), request.getMessage());
        return BaseResponse.success("已拒绝请求");
    }

    @GetMapping("/getFriendList")
    public BaseResponse<List<FriendListItem>> getFriendList(){
        logger.info("getFriendList");
        Integer accountId = 0;
        List<FriendListItem> friendList = accountRelationService.getFriendList(accountId);
        if (friendList.isEmpty()) return BaseResponse.success("好友列表",new ArrayList<>());
        return BaseResponse.success("好友列表",friendList);
    }

}
