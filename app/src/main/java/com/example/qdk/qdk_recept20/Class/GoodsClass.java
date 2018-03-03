package com.example.qdk.qdk_recept20.Class;

/**
 * Created by lenovo on 2017/2/9.
 */

public class GoodsClass {
    private String goods_name;
    private double budget;
    private double cost;
    private double left;
    private int imageId;
//    private String commodity;

    public GoodsClass(String goods_name, double budget, double left, double cost, int imageId){
        this.goods_name=goods_name;
        this.budget=budget;
        this.cost=cost;
        this.left=left;
        this.imageId=imageId;
//        this.commodity=commodity;
    }

    public String getGoods_name(){
        return goods_name;
    }

    public double getBudget(){
        return budget;
    }


    public double getCost(){
        return cost;
    }

    public int getImageId(){return imageId;}

    public double getLeft(){return left;}

//    public String getCommodity(){return commodity;}
}
