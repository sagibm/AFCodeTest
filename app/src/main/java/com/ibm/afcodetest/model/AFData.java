package com.ibm.afcodetest.model;

/**
 * Created by sheldongabriel on 6/16/16.
 */
public class AFData {

    private int id;
    private String backGroundUrl;
    private String topDesc;
    private String title;
    private String promoMsg;
    private String bottomDesc;
    private String contentTitle;
    private String contentTarget;

    private String contentArray;


    public AFData() {

    }

    public AFData(String backGroundUrl,String topDesc, String title,String promoMsg,String bottomDesc,String contentTitle,String contentTarget) {
        this.backGroundUrl = backGroundUrl;
        this.topDesc = topDesc;
        this.title = title;
        this.promoMsg = promoMsg;
        this.bottomDesc = bottomDesc;
        this.contentTitle = contentTitle;
        this.contentTarget = contentTarget;
    }

    public AFData(int id,String backGroundUrl,String topDesc, String title,String promoMsg,String bottomDesc,String contentTitle,String contentTarget) {
        this.id = id;
        this.backGroundUrl = backGroundUrl;
        this.topDesc = topDesc;
        this.title = title;
        this.promoMsg = promoMsg;
        this.bottomDesc = bottomDesc;
        this.contentTitle = contentTitle;
        this.contentTarget = contentTarget;
    }

    public void setBackGroundUrl(String backGroundUrl) {
        this.backGroundUrl = backGroundUrl;
    }

    public String getBackGroundUrl() {

        return backGroundUrl;
    }

    public void setTopDesc(String topDesc) {
        this.topDesc = topDesc;
    }

    public String getTopDesc() {
        return topDesc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPromoMsg(String promoMsg) {
        this.promoMsg = promoMsg;
    }

    public String getPromoMsg() {
        return promoMsg;
    }

    public void setBottomDesc(String bottomDesc) {
        this.bottomDesc = bottomDesc;
    }

    public String getBottomDesc() {
        return bottomDesc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setContentTitle(String title) {
        this.contentTitle = title;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTarget(String target) {
        this.contentTarget = target;
    }

    public String getContentTarget() {
        return contentTarget;
    }

    public void setContentArray(String array) {
        this.contentArray = array;
    }

    public String getContentArray() {
        return contentArray;
    }

}
