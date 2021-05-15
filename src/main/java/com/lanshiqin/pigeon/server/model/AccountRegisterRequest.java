package com.lanshiqin.pigeon.server.model;

public class AccountRegisterRequest {
    private String tel;
    private String nickName;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
