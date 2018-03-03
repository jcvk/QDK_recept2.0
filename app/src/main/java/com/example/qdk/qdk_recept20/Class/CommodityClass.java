package com.example.qdk.qdk_recept20.Class;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/2/11.
 */

public class CommodityClass implements Serializable {
    private String commodityTime;
    private String commodityName;
    public double commodityPrice;

    public CommodityClass(String commodityTime, String commodityName, double commodityPrice) {
        this.commodityTime = commodityTime;
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public String getCommodityTime() {
        return commodityTime;
    }


}
