package com.lanshiqin.pigeon.server.util;

public class JwtUserInfo {
    private String jwtUserId;
    private String jwtUserName;

    public String getJwtUserId() {
        return jwtUserId;
    }

    public void setJwtUserId(String jwtUserId) {
        this.jwtUserId = jwtUserId;
    }

    public String getJwtUserName() {
        return jwtUserName;
    }

    public void setJwtUserName(String jwtUserName) {
        this.jwtUserName = jwtUserName;
    }
}
