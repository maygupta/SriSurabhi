package com.socal.connection.sri.surabhi.media.controller;

/**
 * Created by vivek on 07/01/18.
 */

public class SideMenuModel {
    public SideMenuModel(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    int imageId;
    String name;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
