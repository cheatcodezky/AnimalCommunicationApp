package com.treeanimals.max.animal;

/**
 * Created by 95112 on 2018/6/8.
 */

public class PetRelease {
    private String petName,petDetail,releaseTime;
    private int imageId,petMoney;
    public PetRelease(String petName,String petDetail,String releaseTime,int imageId,int petMoney){
        this.petName = petName;
        this.petDetail = petDetail;
        this.releaseTime = releaseTime;
        this.imageId = imageId;
        this.petMoney = petMoney;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetDetail() {
        return petDetail;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public int getImageId() {
        return imageId;
    }

    public int getPetMoney() {
        return petMoney;
    }
}
