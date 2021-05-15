package com.lanshiqin.pigeon.server.service;

import com.lanshiqin.pigeon.server.entity.AccountRelation;
import com.lanshiqin.pigeon.server.exception.BusinessException;
import com.lanshiqin.pigeon.server.mapper.AccountRelationMapper;
import com.lanshiqin.pigeon.server.model.FriendListItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountRelationService {

    @Resource
    private AccountRelationMapper accountRelationMapper;

    public void addFriend(Integer accountId, Integer friendId) {
        if (friendRelationExist(accountId, friendId)) {
            throw new BusinessException("好友关系已存在");
        }
        AccountRelation accountRelation = new AccountRelation();
        accountRelation.setAccountId(accountId);
        accountRelation.setFriendId(friendId);
        accountRelationMapper.insert(accountRelation);
    }

    public boolean friendRelationExist(Integer accountId, Integer friendId) {
        AccountRelation accountRelation = accountRelationMapper.findFriendByAccount(accountId, friendId);
        return accountRelation != null;
    }

    public List<FriendListItem> getFriendList(Integer accountId) {
        return accountRelationMapper.getFriendList(accountId);
    }
}
