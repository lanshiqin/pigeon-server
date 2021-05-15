package com.lanshiqin.pigeon.server.service;

import com.lanshiqin.pigeon.server.entity.FriendAddMessage;
import com.lanshiqin.pigeon.server.exception.BusinessException;
import com.lanshiqin.pigeon.server.mapper.FriendAddMessageMapper;
import com.lanshiqin.pigeon.server.model.FriendListItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 好友添加消息服务
 *
 * @author 蓝士钦
 */
@Service
public class FriendAddMessageService {

    @Resource
    private FriendAddMessageMapper friendAddMessageMapper;

    @Resource
    private AccountRelationService accountRelationService;

    private static final int AGREE_FRIEND = 1;
    private static final int DISAGREE_FRIEND = -1;

    /**
     * 添加好友请求
     *
     * @param accountId 账号id
     * @param friendId  好友id
     * @param remark    备注内容
     */
    public void addFriendReq(Integer accountId, Integer friendId, String remark) {
        if (accountRelationService.friendRelationExist(accountId, friendId)) {
            throw new BusinessException("好友关系已存在");
        }
        FriendAddMessage friendAddMessage = new FriendAddMessage();
        friendAddMessage.setAccountId(accountId);
        friendAddMessage.setObjectAccountId(friendId);
        friendAddMessage.setRemark(remark);
        friendAddMessageMapper.insert(friendAddMessage);
    }

    /**
     * 同意添加好友
     *
     * @param requestId 添加好友请求原始id
     * @param message   附加消息
     */
    @Transactional
    public void agreeAddFriend(String requestId, String message) {
        FriendAddMessage friendAddMessage = friendAddMessageMapper.selectById(requestId);
        if (friendAddMessage == null) throw new BusinessException("消息不存在");
        friendAddMessage.setObjectAgreedStatus(AGREE_FRIEND);
        friendAddMessage.setObjectAccountRead(true);

        accountRelationService.addFriend(friendAddMessage.getAccountId(), friendAddMessage.getObjectAccountId());
    }

    /**
     * 不同意添加好友
     *
     * @param requestId 添加好友请求原始id
     * @param message   附加消息
     */
    public void disAgreeAddFriend(String requestId, String message) {
        FriendAddMessage friendAddMessage = friendAddMessageMapper.selectById(requestId);
        if (friendAddMessage == null) throw new BusinessException("消息不存在");
        friendAddMessage.setObjectAgreedStatus(DISAGREE_FRIEND);
        friendAddMessage.setObjectAccountRead(true);
    }

}
