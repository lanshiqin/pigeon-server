package com.lanshiqin.pigeon.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanshiqin.pigeon.server.entity.AccountRelation;
import com.lanshiqin.pigeon.server.model.FriendListItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountRelationMapper extends BaseMapper<AccountRelation> {

    @Select("select * from account_relation where account_id = #{accountId} and friend_id = #{friendId} and deleted = 0")
    AccountRelation findFriendByAccount(Integer accountId, Integer friendId);

    @Select("select a.friend_id accountId, b.nick_name nickName " +
            "from (select friend_id from account_relation where account_id = #{accountId}) a " +
            "         left join account_info b on a.friend_id = b.account_id ")
    List<FriendListItem> getFriendList(Integer accountId);
}
