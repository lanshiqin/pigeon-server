package com.lanshiqin.pigeon.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanshiqin.pigeon.server.entity.FriendAddMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 好友添加消息Mapper操作
 *
 * @author 蓝士钦
 */
@Mapper
public interface FriendAddMessageMapper extends BaseMapper<FriendAddMessage> {
}
