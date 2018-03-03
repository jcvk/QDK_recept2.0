package com.example.qdk.qdk_recept20.Class;

/**
 * Created by lenovo on 2017/3/29.
 */

public class MarketClass {
    private String marketId;
    private String marketPlace;
    private String recordTime;
    private String recordCommodity;

    public MarketClass(String marketId, String marketPlace, String recordTime, String recordCommodity){
        this.marketId=marketId;
        this.marketPlace=marketPlace;
        this.recordTime=recordTime;
        this.recordCommodity=recordCommodity;
    }

    public String getMarketId(){
        return marketId;
    }

    public String getMarketPlace(){
        return marketPlace;
    }

    public String getRecordTime(){
        return recordTime;
    }

    public String getRecordCommodity(){
        return recordCommodity;
    }
}
