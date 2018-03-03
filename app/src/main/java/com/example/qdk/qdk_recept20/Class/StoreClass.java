package com.example.qdk.qdk_recept20.Class;

/**
 * Created by lenovo on 2017/3/19.
 */

public class StoreClass {
    private int imageId;
    private String name;
    private String news;

    public StoreClass(String name, String news, int imageId){
        this.imageId=imageId;
        this.name=name;
        this.news=news;
    }

    public String getName(){
        return name;
    }

    public String getNews(){
        return news;
    }

    public int getImageId(){
        return imageId;
    }
}
