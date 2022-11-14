package com.example.gridimages_macc;

public class ItemEntity {

    private String imgPrevURL, imgWebURL, user;
    private int likes;

    public ItemEntity() {
    }

    public ItemEntity(String imgPrevURL, String imgWebURL, String user, int likes) {
        this.imgPrevURL = imgPrevURL;
        this.imgWebURL = imgWebURL;
        this.user = user;
        this.likes = likes;
    }

    public String getImgPrevURL() {
        return imgPrevURL;
    }

    public void setImgPrevURL(String imgPrevURL) {
        this.imgPrevURL = imgPrevURL;
    }

    public String getImgWebURL() {
        return imgWebURL;
    }

    public void setImgWebURL(String imgWebURL) {
        this.imgWebURL = imgWebURL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
