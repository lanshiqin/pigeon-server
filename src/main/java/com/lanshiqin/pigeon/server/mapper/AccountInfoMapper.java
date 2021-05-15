package com.lanshiqin.pigeon.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanshiqin.pigeon.server.entity.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {

    @Select("select * from account_info where tel = #{tel} and deleted = 0 ")
    AccountInfo findByTel(String tel);
}
