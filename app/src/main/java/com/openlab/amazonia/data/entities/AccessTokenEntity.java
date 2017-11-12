package com.openlab.amazonia.data.entities;

import java.io.Serializable;

/**
 * Created by manu on 04/08/16.
 */
public class AccessTokenEntity implements Serializable {
    private String account_level;
    private int account_id;
    private String token;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_level() {
        return account_level;
    }

    public void setAccount_level(String account_level) {
        this.account_level = account_level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
