package com.indi.bean.login;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 华峰
 * @create: 2020-04-07 13:59
 */
public class User {

    @JsonAlias("account")
    private String account;

    @JsonProperty("password")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
