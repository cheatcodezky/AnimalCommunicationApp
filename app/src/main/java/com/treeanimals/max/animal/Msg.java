package com.treeanimals.max.animal;

/**
 * Created by 95112 on 2018/6/26.
 */

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;
    private int headPortrait;
    public Msg(String content,int type,int headPortrait){
        this.content = content;
        this.type = type;
        this.headPortrait = headPortrait;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public int getHeadPortrait() {
        return headPortrait;
    }
}
