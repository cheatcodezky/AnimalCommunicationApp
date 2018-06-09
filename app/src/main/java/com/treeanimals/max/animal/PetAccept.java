package com.treeanimals.max.animal;

/**
 * Created by 95112 on 2018/6/9.
 */

public class PetAccept {
    private int imageId;
    private String petAcceptTime,petAcceptDetail,userName;

    public PetAccept(int imageId,String petAcceptTime,String userName,String petAcceptDetail){
        this.imageId = imageId;
        this.petAcceptTime =petAcceptTime;
        this.userName = userName;
        this.petAcceptDetail = petAcceptDetail;
    }

    public String getUserName(){
        return userName;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPetAcceptTime() {
        return petAcceptTime;
    }

    public String getPetAcceptDetail() {
        return petAcceptDetail;
    }
}
