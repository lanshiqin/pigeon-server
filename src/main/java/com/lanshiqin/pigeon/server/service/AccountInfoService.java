package com.lanshiqin.pigeon.server.service;

import com.lanshiqin.pigeon.server.entity.AccountInfo;
import com.lanshiqin.pigeon.server.exception.BusinessException;
import com.lanshiqin.pigeon.server.mapper.AccountInfoMapper;
import com.lanshiqin.pigeon.server.model.LoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountInfoService {

    @Resource
    private AccountInfoMapper accountInfoMapper;

    @Transactional
    public void register(String tel, String nickName) {
        if (telIsExist(tel)) {
            throw new BusinessException("手机号已存在");
        }
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setTel(tel);
        accountInfo.setNickName(nickName);
        accountInfoMapper.insert(accountInfo);
    }

    public LoginResponse login(String tel, String passWord) {
        LoginResponse loginResponse = new LoginResponse();
        AccountInfo accountInfo = accountInfoMapper.findByTel(tel);
        if (accountInfo == null) throw new BusinessException("账号不存在");
        if (!passWord.equals(accountInfo.getPassWord())) throw new BusinessException("账号或密码错误");

        loginResponse.setToken("token_" + accountInfo.getAccountId());

        accountInfo.setIpAddress("192.168.1.1");
        accountInfoMapper.updateById(accountInfo);
        return loginResponse;
    }

    private boolean telIsExist(String tel) {
        AccountInfo accountInfo = accountInfoMapper.findByTel(tel);
        return accountInfo != null;
    }
}
