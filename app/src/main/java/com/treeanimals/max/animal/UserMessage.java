package com.treeanimals.max.animal;

/**
 * Created by 95112 on 2018/6/25.
 */

public class UserMessage {
    private int imageId;
    private String userName,message,time;

    public UserMessage(int imageId,String userName,String message,String time){
        this.imageId = imageId;
        this.userName = userName;
        this.message = message;
        this.time = time;
    }
    public int getImageId() {
        return imageId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
