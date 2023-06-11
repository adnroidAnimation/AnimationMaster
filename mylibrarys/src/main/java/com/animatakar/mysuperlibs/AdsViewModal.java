package com.animatakar.mysuperlibs;

public class AdsViewModal {


    String app_name;
    String enable_ads;
    String ad_app_name;
    String app_description;
    String app_logo;
    String app_banner;


    public AdsViewModal(String app_name, String enable_ads, String ad_app_name, String app_description, String app_logo, String app_banner) {
        this.app_name = app_name;
        this.enable_ads = enable_ads;
        this.ad_app_name = ad_app_name;
        this.app_description = app_description;
        this.app_logo = app_logo;
        this.app_banner = app_banner;
    }


    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getEnable_ads() {
        return enable_ads;
    }

    public void setEnable_ads(String enable_ads) {
        this.enable_ads = enable_ads;
    }

    public String getAd_app_name() {
        return ad_app_name;
    }

    public void setAd_app_name(String ad_app_name) {
        this.ad_app_name = ad_app_name;
    }

    public String getApp_description() {
        return app_description;
    }

    public void setApp_description(String app_description) {
        this.app_description = app_description;
    }

    public String getApp_logo() {
        return app_logo;
    }

    public void setApp_logo(String app_logo) {
        this.app_logo = app_logo;
    }

    public String getApp_banner() {
        return app_banner;
    }

    public void setApp_banner(String app_banner) {
        this.app_banner = app_banner;
    }


}
