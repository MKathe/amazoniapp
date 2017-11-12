package com.openlab.amazonia.data.entities;

import java.io.Serializable;

/**
 * Created by katherine on 12/11/17.
 */

public class ResponseUser implements Serializable {
    private int id;
    private String level;
    private UserEntity user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
