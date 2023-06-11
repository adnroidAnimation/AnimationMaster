package com.animatakar.mysuperlibs;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Splash extends AppCompatActivity {
    public static String extra_switch_1;
    public static String extra_switch_2;
    public static String extra_switch_3;

    public static String pre_load_ads;
    public static String extra_text_1;
    public static String extra_text_2;
    public static String extra_text_3;
    public static String extra_text_4;

    public static Context contextx;
    public static Intent intentx;

    public static int on_offAds;

    public static boolean isShowOpen = false;
    public static AppOpenManager appOpenManager;
    public static String PackName = "";

    public static ArrayList<AdsViewModal> adsViewModals = new ArrayList<>();

    public static boolean customads_status = false;
    public static boolean OpenAdsStatus = false;

    public static boolean checkAppOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    /*Splash*/
    public static void StartAnimation(Context context, Intent intent, String packageName, String versionCode, int on_off, String basic_) {
        PackName = packageName;
        contextx = context;
        intentx = intent;
        on_offAds = on_off;

        if (!MyProHelperClass.isOnline(context)) {
            context.startActivity(new Intent(context, InternetErrorActivity.class));
            return;
        }
        String uri = "b7779d56eee78bd0f220831782eb54b0";
        String Baseurl = "https://gist.githubusercontent.com/";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
//        asyncHttpClient.get(ProMex.classs.Utils.apiii.DECode("AA9C519FD788B1E95C4BA329483BDAB3687104B07F07BD7D729885F7CBCDBC3ECA5716E530122F561B94997C0D258D5C9D134C0229A90833D19A6555B8EE975FABAEC03842CACE3D63C8CFE4682E19CB3BF2168C769B4BF10095743EC73DDEDE") + basic_,
        asyncHttpClient.get(Baseurl+basic_,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            /**
                             * Google
                             */

                            MyProHelperClass.setGoogleEnable(response.getString("enable_google_admob_id"));
                            //google Banner
                            if (response.getString("google_admob_banner_id") != null && !response.getString("google_admob_banner_id").isEmpty()) {
                                MyProHelperClass.SetGoogleBanner(response.getString("google_admob_banner_id"));
                            } else {
                                MyProHelperClass.SetGoogleBanner(null);
                            }
                            if (response.getString("google_admob_banner_id_1") != null && !response.getString("google_admob_banner_id_1").isEmpty()) {
                                MyProHelperClass.SetGoogleBanner1(response.getString("google_admob_banner_id_1"));
                            } else {
                                MyProHelperClass.SetGoogleBanner1(null);
                            }
                            if (response.getString("google_admob_banner_id_2") != null && !response.getString("google_admob_banner_id_2").isEmpty()) {
                                MyProHelperClass.SetGoogleBanner2(response.getString("google_admob_banner_id_2"));
                            } else {
                                MyProHelperClass.SetGoogleBanner2(null);
                            }
                            //google Native
                            if (response.getString("google_admob_native_id") != null && !response.getString("google_admob_native_id").isEmpty()) {
                                MyProHelperClass.SetGoogleNative(response.getString("google_admob_native_id"));
                            } else {
                                MyProHelperClass.SetGoogleNative(null);
                            }
                            if (response.getString("google_admob_native_id_1") != null && !response.getString("google_admob_native_id_1").isEmpty()) {
                                MyProHelperClass.SetGoogleNative1(response.getString("google_admob_native_id_1"));
                            } else {
                                MyProHelperClass.SetGoogleNative1(null);
                            }
                            if (response.getString("google_admob_native_id_2") != null && !response.getString("google_admob_native_id_2").isEmpty()) {
                                MyProHelperClass.SetGoogleNative2(response.getString("google_admob_native_id_2"));
                            } else {
                                MyProHelperClass.SetGoogleNative2(null);
                            }

                            //google Native Btn Name
                            if (response.getString("google_button_name") != null && !response.getString("google_button_name").isEmpty()) {
                                MyProHelperClass.setGooglebutton_name(response.getString("google_button_name"));
                            } else {
                                MyProHelperClass.setGooglebutton_name(null);
                            }
                            //google Native Btn color
                            if (response.getString("google_button_color") != null && !response.getString("google_button_color").isEmpty()) {
                                MyProHelperClass.setGooglebutton_color(response.getString("google_button_color"));
                            } else {
                                MyProHelperClass.setGooglebutton_color("#000000");
                            }
                            // google Open ADS
                            if (response.getString("google_openapp_id") != null && !response.getString("google_openapp_id").isEmpty()) {
                                MyProHelperClass.setGoogle_OpenADS(response.getString("google_openapp_id"));
                            } else {
                                MyProHelperClass.setGoogle_OpenADS(null);
                            }
                            //google Interstitial
                            if (response.getString("google_admob_interstitial_id") != null && !response.getString("google_admob_interstitial_id").isEmpty()) {
                                NextAnimation.AutoGoogleInterID = 1;
                                MyProHelperClass.SetGoogleInter(response.getString("google_admob_interstitial_id"));
                            } else {
                                MyProHelperClass.SetGoogleInter(null);
                            }
                            if (response.getString("google_admob_interstitial_id_1") != null && !response.getString("google_admob_interstitial_id_1").isEmpty()) {
                                MyProHelperClass.SetGoogleInter1(response.getString("google_admob_interstitial_id_1"));
                            } else {
                                MyProHelperClass.SetGoogleInter1(null);
                            }
                            if (response.getString("google_admob_interstitial_id_2") != null && !response.getString("google_admob_interstitial_id_2").isEmpty()) {
                                MyProHelperClass.SetGoogleInter2(response.getString("google_admob_interstitial_id_2"));
                            } else {
                                MyProHelperClass.SetGoogleInter2(null);
                            }
                            /**
                             * Facebook
                             */
                            MyProHelperClass.setFacebookEnable(response.getString("enable_facebook_id"));
                            //Facebook Banner
                            if (response.getString("facebook_banner_id") != null && !response.getString("facebook_banner_id").isEmpty()) {
                                MyProHelperClass.setFacebookBanner(response.getString("facebook_banner_id"));
                            } else {
                                MyProHelperClass.setFacebookBanner(null);
                            }
                            if (response.getString("facebook_banner_id_1") != null && !response.getString("facebook_banner_id_1").isEmpty()) {
                                MyProHelperClass.setFacebookBanner1(response.getString("facebook_banner_id_1"));
                            } else {
                                MyProHelperClass.setFacebookBanner1(null);
                            }
                            if (response.getString("facebook_banner_id_2") != null && !response.getString("facebook_banner_id_2").isEmpty()) {
                                MyProHelperClass.setFacebookBanner2(response.getString("facebook_banner_id_2"));
                            } else {
                                MyProHelperClass.setFacebookBanner2(null);
                            }
                            //Facebook Native
                            if (response.getString("facebook_native_id") != null && !response.getString("facebook_native_id").isEmpty()) {
                                MyProHelperClass.SetFacebookNative(response.getString("facebook_native_id"));
                            } else {
                                MyProHelperClass.SetFacebookNative(null);
                            }
                            if (response.getString("facebook_native_id_1") != null && !response.getString("facebook_native_id_1").isEmpty()) {
                                MyProHelperClass.SetFacebookNative1(response.getString("facebook_native_id_1"));
                            } else {
                                MyProHelperClass.SetFacebookNative1(null);
                            }
                            if (response.getString("facebook_native_id_2") != null && !response.getString("facebook_native_id_2").isEmpty()) {
                                MyProHelperClass.SetFacebookNative2(response.getString("facebook_native_id_2"));
                            } else {
                                MyProHelperClass.SetFacebookNative2(null);
                            }

                            //Facebook Open ADS
                            if (response.getString("facebook_open_id") != null && !response.getString("facebook_open_id").isEmpty()) {
                                MyProHelperClass.setfacebook_open_ad_id(response.getString("facebook_open_id"));
                            } else {
                                MyProHelperClass.setfacebook_open_ad_id(null);
                            }
                            //Facebook Interstitial
                            if (response.getString("facebook_interstitial_id") != null && !response.getString("facebook_interstitial_id").isEmpty()) {
                                MyProHelperClass.SetFacebookInter(response.getString("facebook_interstitial_id"));
                            } else {
                                MyProHelperClass.SetFacebookInter(null);
                            }
                            if (response.getString("facebook_interstitial_id_1") != null && !response.getString("facebook_interstitial_id_1").isEmpty()) {
                                MyProHelperClass.SetFacebookInter1(response.getString("facebook_interstitial_id_1"));
                            } else {
                                MyProHelperClass.SetFacebookInter1(null);
                            }
                            if (response.getString("facebook_interstitial_id_2") != null && !response.getString("facebook_interstitial_id_2").isEmpty()) {
                                MyProHelperClass.SetFacebookInter2(response.getString("facebook_interstitial_id_2"));
                            } else {
                                MyProHelperClass.SetFacebookInter2(null);
                            }

                            /**
                             *  Atme and qureka Link
                             */
                            /*auto link*/
                            MyProHelperClass.setauto_link_on_off(response.getString("enable_auto_quereka_link"));  //on_off Auto link
                            if (MyProHelperClass.getauto_link_on_off().equals("1")) {
                                MyProHelperClass.setauto_link_array(response.getString("auto_quereka_link")); //link Array
                                MyProHelperClass.setauto_link_timer(response.getString("auto_quereka_time")); //open Timer
                                MyProHelperClass.Autolink();
                            }
                            /*btn link*/
                            MyProHelperClass.set_q_link_btn_on_off(response.getString("enable_quereka_link"));  //on_off Q link btn
                            MyProHelperClass.set_q_link_array(response.getString("quereka_link")); //link Array

                            /**
                             * App Lovin
                             */
                            MyProHelperClass.setAppLovinEnable(response.getString("enable_applovin_id"));  //on_off App Lovin
                            if (response.getString("applovin_banner") != null && !response.getString("applovin_banner").isEmpty()) {   //Banner
                                MyProHelperClass.setAppLovinBanner(response.getString("applovin_banner"));
                            } else {
                                MyProHelperClass.setAppLovinBanner(null);
                            }
                            if (response.getString("applovin_native") != null && !response.getString("applovin_native").isEmpty()) {   //Native
                                MyProHelperClass.setAppLovinNative(response.getString("applovin_native"));
                            } else {
                                MyProHelperClass.setAppLovinNative(null);
                            }
                            if (response.getString("applovin_interstitial") != null && !response.getString("applovin_interstitial").isEmpty()) {   //Inter
                                MyProHelperClass.setAppLovinInter(response.getString("applovin_interstitial"));

                            } else {
                                MyProHelperClass.setAppLovinInter(null);
                            }

                            /**
                             *
                             * Unity
                             */
                            MyProHelperClass.setUnityEnable(response.getString("enable_unity_id"));  //on_off Unity
                            if (response.getString("unity_game_id") != null && !response.getString("unity_game_id").isEmpty()) {   //Unity ID
                                MyProHelperClass.setUnityAppID(response.getString("unity_game_id"));
                                UnityAds.initialize(MyProHelperClass.instance, MyProHelperClass.getUnityAppID(), false);
                            } else {
                                MyProHelperClass.setUnityAppID(null);
                            }
                            if (response.getString("unity_banner") != null && !response.getString("unity_banner").isEmpty()) { //Unity Banner ID
                                MyProHelperClass.setUnityBannerID(response.getString("unity_banner"));
                            } else {
                                MyProHelperClass.setUnityBannerID(null);
                            }
                            if (response.getString("unity_interstitial") != null && !response.getString("unity_interstitial").isEmpty()) {  //Unity Inter ID
                                MyProHelperClass.setUnityInterID(response.getString("unity_interstitial"));
                            } else {
                                MyProHelperClass.setUnityInterID(null);
                            }

                            /**
                             * Back Button
                             */
                            MyProHelperClass.setBackAdsOnOff(response.getString("enable_back_button"));
                            if (response.getString("back_button_inter_counter") != null && !response.getString("back_button_inter_counter").isEmpty()) {
                                MyProHelperClass.setBackCounter(Integer.parseInt(response.getString("back_button_inter_counter")));  //skip ads number
                            } else {
                                MyProHelperClass.setBackCounter(5000);
                            }

                            /**
                             * Skip ads
                             * 1 - Inter
                             * 2 - Native
                             * 3 - Banner
                             *
                             * btn number 0 stop ads
                             * */
                            if (response.getString("skip_inter_ad") != null && !response.getString("skip_inter_ad").isEmpty()) {
                                MyProHelperClass.setCounter_Inter(Integer.parseInt(response.getString("skip_inter_ad")));
                            } else {
                                MyProHelperClass.setCounter_Inter(5000);
                            }
                            if (response.getString("skip_native_ad") != null && !response.getString("skip_native_ad").isEmpty()) {
                                MyProHelperClass.setCounter_Native(Integer.parseInt(response.getString("skip_native_ad")));
                            } else {
                                MyProHelperClass.setCounter_Native(5000);
                            }
                            if (response.getString("skip_banner_ad") != null && !response.getString("skip_banner_ad").isEmpty()) {
                                MyProHelperClass.setCounter_Banner(Integer.parseInt(response.getString("skip_banner_ad")));
                            } else {
                                MyProHelperClass.setCounter_Banner(5000);
                            }

                            if (response.getString("openapp_ad_on_off") != null && !response.getString("openapp_ad_on_off").isEmpty()) {
                                MyProHelperClass.setOpenAdsShow(Integer.parseInt(response.getString("openapp_ad_on_off")));
                            } else {
                                MyProHelperClass.setOpenAdsShow(0);
                            }


                            /**
                             * MIX ads
                             * 1 - Inter
                             * 2 - Native
                             * 3 - Banner
                             * */
                            MyProHelperClass.setmix_ad_on_off(response.getString("mix_ad"));

                            //Mix Ads Counter
                            if (response.getString("mix_ad_name") != null && !response.getString("mix_ad_name").isEmpty()) {
                                MyProHelperClass.setmix_ad_name(response.getString("mix_ad_name"));
                                String[] Ads_number = MyProHelperClass.getmix_ad_name().split(",");
                                MyProHelperClass.setmix_ad_counter_banner(Integer.parseInt(Ads_number[0]));
                                MyProHelperClass.setmix_ad_counter_native(Integer.parseInt(Ads_number[1]));
                                MyProHelperClass.setmix_ad_counter_inter(Integer.parseInt(Ads_number[2]));
                            } else {
                                MyProHelperClass.setmix_ad_name(null);
                                MyProHelperClass.setmix_ad_counter_inter(5000);
                                MyProHelperClass.setmix_ad_counter_native(5000);
                                MyProHelperClass.setmix_ad_counter_banner(5000);
                            }

                            //Mix Ads Name
                            if (response.getString("mix_ad_banner") != null && !response.getString("mix_ad_banner").isEmpty()) {
                                MyProHelperClass.setmix_ad_banner(response.getString("mix_ad_banner"));
                            } else {
                                MyProHelperClass.setmix_ad_banner(null);
                            }

                            if (response.getString("mix_ad_native") != null && !response.getString("mix_ad_native").isEmpty()) {
                                MyProHelperClass.setmix_ad_native(response.getString("mix_ad_native"));
                            } else {
                                MyProHelperClass.setmix_ad_native(null);
                            }

                            if (response.getString("mix_ad_counter") != null && !response.getString("mix_ad_counter").isEmpty()) {
                                MyProHelperClass.setmix_ad_inter(response.getString("mix_ad_counter"));
                            } else {
                                MyProHelperClass.setmix_ad_inter(null);
                            }

                            /**
                             * Skip Country
                             */


                            /**
                             * Extra data
                             */
                            extra_switch_1 = response.getString("extra_switch_1");
                            extra_switch_2 = response.getString("extra_switch_2");
                            extra_switch_3 = response.getString("extra_switch_3");
//                            pre_load_ads = response.getString("pre_load_ads");
                            extra_text_1 = response.getString("extra_text_1");
                            extra_text_2 = response.getString("extra_text_2");
                            extra_text_3 = response.getString("extra_text_3");
                            extra_text_4 = response.getString("extra_text_4");


                            /**
                             * Preload ON OFF
                             */
                            //Interstitial PreLoad
                            MyProHelperClass.setInterPreLoad(response.getString("interstitial_preload"));
                            //Native PreLoad
                            MyProHelperClass.setNative_preload(response.getString("native_preload"));
                            //Banner PreLoad
                            MyProHelperClass.setBanner_preload(response.getString("banner_preload"));


                            /**
                             * Other App Open
                             */
                            MyProHelperClass.setOtherAppsShow(response.getString("replace_app"));
                            MyProHelperClass.setOtherAppsShowLink(response.getString("new_app_link"));
                            if (MyProHelperClass.getOtherAppsShow().equals("1")) {
                                MyProHelperClass.Entery_UpdateApps = 2;
                                context.startActivity(new Intent(context, UpdateAppActivity.class));
                                return;
                            }

                            /**
                             * Update Our App
                             */
                            MyProHelperClass.setUpdateApps(response.getString("update_app"));
                            MyProHelperClass.setAppversioncode(response.getString("version_code"));
                            if (MyProHelperClass.getUpdateApps().equals("1")) {
                                if (!MyProHelperClass.getAppversioncode().equals(versionCode)) {
                                    MyProHelperClass.Entery_UpdateApps = 1;
                                    context.startActivity(new Intent(context, UpdateAppActivity.class));
                                    return;
                                }
                            }


                            /**
                             * Next App
                             */

//                            if (MyProHelperClass.getSkip_country_on_off().equals("1") && CheckCountry(MyProHelperClass.getSkip_country_list())) {
//                                MyProHelperClass.setGoogleEnable("0");
//                                MyProHelperClass.setFacebookEnable("0");
//                                MyProHelperClass.setauto_link_on_off("0");
//                                MyProHelperClass.set_q_link_btn_on_off("0");
//                                MyProHelperClass.setAppLovinEnable("0");
//                                MyProHelperClass.setUnityEnable("0");
//                                MyProHelperClass.setCustomEnable("0");
//                                MyProHelperClass.setmix_ad_on_off("0");
//                                MyProHelperClass.setBackAdsOnOff("0");
//                                NextIntent(contextx, intentx);
//                            } else {

                            if (on_offAds == 1 || MyProHelperClass.getOpenAdsShow() == 1) {
                                on_offAds = 1;
                                AllAdsPreLoad();  //only Preload __ Not show open ads
                                return;
                            }

                            ShowADS();  //open ads show
                            AllAdsPreLoad();  //Pre load ads

//                            }
                        } catch (
                                JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable
                            throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }
                });

    }


    public static void NextIntent(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    /**
     * Show Ads
     */
    private static void ShowADS() {
        if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
            MixOpenAds(String.valueOf(MyProHelperClass.getmix_ad_inter().charAt(0)));
            return;
        }
        if (MyProHelperClass.getGoogleEnable().equals("1")) {
            GoogleAppOpen();
        } else if (MyProHelperClass.getFacebookEnable().equals("1")) {
            FaceBookAppOpen();
        } else if (MyProHelperClass.getAppLovinEnable().equals("1")) {
            AppLovingAppOpen();
        } else if (MyProHelperClass.getUnityEnable().equals("1")) {
            UnityAppOpen();
        } else {
            NextIntent(contextx, intentx);
        }
    }

    private static void UnityAppOpen() {

        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
            UnityAds.load(MyProHelperClass.getUnityInterID(), new IUnityAdsLoadListener() {
                @Override
                public void onUnityAdsAdLoaded(String placementId) {
                    UnityAds.show((Activity) contextx, MyProHelperClass.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                        @Override
                        public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                            /*Unity Mix Auto Load Inter*/
                            if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                                NextAnimation.UnityInterPreLoad();
                            }
                            FailsAds("u");

                        }

                        @Override
                        public void onUnityAdsShowStart(String placementId) {


                        }

                        @Override
                        public void onUnityAdsShowClick(String placementId) {

                        }

                        @Override
                        public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                            NextIntent(contextx, intentx);
                            /*Unity Mix Auto Load Inter*/
                            if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                                NextAnimation.UnityInterPreLoad();
                            }
                        }
                    });
                }

                @Override
                public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                    /*Unity Mix Auto Load Inter*/
                    if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                        NextAnimation.UnityInterPreLoad();
                    }
                    FailsAds("u");
                }
            });
        } else {
            FailsAds("u");
        }
    }

    private static void AppLovingAppOpen() {

        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        FailsAds("a");
                        /*AppLoving Inter PreLoad*/
                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                            NextAnimation.AppLovingInterPreLoad();
                        }
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                }

                @Override
                public void onAdHidden(MaxAd ad) {

                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                    FailsAds("a");
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }

                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();
        } else {
            FailsAds("a");
        }

    }

    private static void FaceBookAppOpen() {

        if (MyProHelperClass.getfacebook_open_ad_id() != null && !MyProHelperClass.getfacebook_open_ad_id().isEmpty()) {

            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyProHelperClass.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    FailsAds("f");
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {

                        FailsAds("f");

                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

        } else {

            FailsAds("f");
        }

    }

    private static void GoogleAppOpen() {

        if (MyProHelperClass.getGoogle_OpenADS() != null && !MyProHelperClass.getGoogle_OpenADS().isEmpty()) {
            try {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailsAds("g");
                        }
                    }

                    @Override
                    public void OnAppOpenClose() {
                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }
                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyProHelperClass.getGoogle_OpenADS(), MyProHelperClass.getInstant(), onAppOpenClose);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            if (checkAppOpen) {
                checkAppOpen = false;
                FailsAds("g");
            }
        }

    }

    private static void CustomOpenAds() {
        NextIntent(contextx, intentx);


      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (customads_status) {
                    if (Splash.adsViewModals.size() == 0) {
                        NextIntent(contextx, intentx);
                        return;
                    }
                    NextIntent(contextx, intentx);
                    contextx.startActivity(new Intent(contextx, CustomAdsActivity.class));
                } else {
                    CustomOpenAds();
                }
            }
        }, 100);*/
    }

    /**
     * Fails Ads
     */
    public static void FailsAds(String Skip) {

        if (Skip.equals("g")) {

            if (MyProHelperClass.getfacebook_open_ad_id() != null && !MyProHelperClass.getfacebook_open_ad_id().isEmpty()) {
                com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyProHelperClass.getfacebook_open_ad_id());
                InterstitialAdListener adListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        NextIntent(contextx, intentx);
                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        GoogleandFacebookFails();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (interstitialAd_FB_1 != null) {
                            interstitialAd_FB_1.show();
                        } else {
                            GoogleandFacebookFails();
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());
            } else {
                GoogleandFacebookFails();
            }

        } else if (Skip.equals("f")) {

            if (MyProHelperClass.getGoogle_OpenADS() != null && !MyProHelperClass.getGoogle_OpenADS().isEmpty()) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {

                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            GoogleandFacebookFails();
                        }

                    }

                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }

                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyProHelperClass.getGoogle_OpenADS(), MyProHelperClass.getInstant(), onAppOpenClose);
            } else {
                GoogleandFacebookFails();
            }

        } else if (Skip.equals("a")) {

            if (MyProHelperClass.getGoogle_OpenADS() != null && !MyProHelperClass.getGoogle_OpenADS().isEmpty()) {

                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailAdsAppLovin_ShowFacebookUnityCustom();
                        }


                    }


                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }
                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyProHelperClass.getGoogle_OpenADS(), MyProHelperClass.getInstant(), onAppOpenClose);

            } else {
                FailAdsAppLovin_ShowFacebookUnityCustom();
            }

        } else if (Skip.equals("u")) {

            if (MyProHelperClass.getGoogle_OpenADS() != null && !MyProHelperClass.getGoogle_OpenADS().isEmpty()) {
                isShowOpen = false;
                AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                    @Override
                    public void OnAppOpenFailToLoad() {
                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (checkAppOpen) {
                            checkAppOpen = false;
                            FailUnity_ShowFacebookAppLovinCustom();
                        }


                    }

                    @Override
                    public void OnAppOpenClose() {

                        if (checkAppOpen) {
                            checkAppOpen = false;
                        }

                        if (isShowOpen) {
                            isShowOpen = false;
                        }

                        if (!OpenAdsStatus) {
                            OpenAdsStatus = true;
                            NextIntent(contextx, intentx);
                        }
                    }
                };
                isShowOpen = true;
                appOpenManager = new AppOpenManager(MyProHelperClass.getGoogle_OpenADS(), MyProHelperClass.getInstant(), onAppOpenClose);

            } else {
                FailUnity_ShowFacebookAppLovinCustom();
            }

        } else {
            CustomOpenAds();
        }
    }

    private static void GoogleandFacebookFails() {

        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {

            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        /*AppLoving Inter PreLoad*/
                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                            NextAnimation.AppLovingInterPreLoad();
                        }

                        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomOpenAds();
                        }

                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                    //Fail Code
                    if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                        FailsAdsUnityShow();
                    } else {
                        CustomOpenAds();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    //
                }
            });
            interstitialAd.loadAd();
        } else if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
            FailsAdsUnityShow();
        } else {
            CustomOpenAds();
        }
    }

    public static void FailAdsAppLovin_ShowFacebookUnityCustom() {
        if (MyProHelperClass.getfacebook_open_ad_id() != null && !MyProHelperClass.getfacebook_open_ad_id().isEmpty()) {
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyProHelperClass.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                        FailsAdsUnityShow();
                    } else {
                        CustomOpenAds();
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {
                        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                            FailsAdsUnityShow();
                        } else {
                            CustomOpenAds();
                        }
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());
        } else if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
            FailsAdsUnityShow();
        } else {
            CustomOpenAds();
        }
    }

    public static void FailUnity_ShowFacebookAppLovinCustom() {

        if (MyProHelperClass.getfacebook_open_ad_id() != null && !MyProHelperClass.getfacebook_open_ad_id().isEmpty()) {
            com.facebook.ads.InterstitialAd interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(contextx, MyProHelperClass.getfacebook_open_ad_id());
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    NextIntent(contextx, intentx);
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {

                        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
                        interstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (interstitialAd.isReady()) {
                                    interstitialAd.showAd();
                                } else {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                        NextAnimation.AppLovingInterPreLoad();
                                    }
                                    CustomOpenAds();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                NextIntent(contextx, intentx);
                                /*AppLoving Inter PreLoad*/
                                if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                    NextAnimation.AppLovingInterPreLoad();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                //Fail Code
                                /*AppLoving Inter PreLoad*/
                                if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                    NextAnimation.AppLovingInterPreLoad();
                                }
                                CustomOpenAds();
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                //
                            }
                        });
                        interstitialAd.loadAd();
                    } else {
                        CustomOpenAds();
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (interstitialAd_FB_1 != null) {
                        interstitialAd_FB_1.show();
                    } else {
                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {

                            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
                            interstitialAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(MaxAd ad) {
                                    if (interstitialAd.isReady()) {
                                        interstitialAd.showAd();
                                    } else {
                                        /*AppLoving Inter PreLoad*/
                                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                            NextAnimation.AppLovingInterPreLoad();
                                        }
                                        CustomOpenAds();
                                    }
                                }

                                @Override
                                public void onAdDisplayed(MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(MaxAd ad) {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                        NextAnimation.AppLovingInterPreLoad();
                                    }
                                    NextIntent(contextx, intentx);
                                }

                                @Override
                                public void onAdClicked(MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(String adUnitId, MaxError error) {
                                    /*AppLoving Inter PreLoad*/
                                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                                        NextAnimation.AppLovingInterPreLoad();
                                    }
                                    //Fail Code
                                    CustomOpenAds();

                                }

                                @Override
                                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                    //
                                }
                            });
                            interstitialAd.loadAd();
                        } else {
                            CustomOpenAds();
                        }
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(interstitialAd_FB_1.buildLoadAdConfig().withAdListener(adListener).build());

        } else if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) contextx);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        /*AppLoving Inter PreLoad*/
                        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                            NextAnimation.AppLovingInterPreLoad();
                        }
                        CustomOpenAds();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(contextx, intentx);
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    /*AppLoving Inter PreLoad*/
                    if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty()) {
                        NextAnimation.AppLovingInterPreLoad();
                    }
                    CustomOpenAds();
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();
        } else {
            CustomOpenAds();
        }
    }

    private static void FailsAdsUnityShow() {
        UnityAds.load(MyProHelperClass.getUnityInterID(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show((Activity) contextx, MyProHelperClass.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                        /*Unity Mix Auto Load Inter*/
                        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                            NextAnimation.UnityInterPreLoad();
                        }
                        CustomOpenAds();
                    }

                    @Override
                    public void onUnityAdsShowStart(String placementId) {


                    }

                    @Override
                    public void onUnityAdsShowClick(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                        NextIntent(contextx, intentx);
                        /*Unity Mix Auto Load Inter*/
                        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                            NextAnimation.UnityInterPreLoad();
                        }

                    }
                });
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                /*Unity Mix Auto Load Inter*/
                if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty()) {
                    NextAnimation.UnityInterPreLoad();
                }
                CustomOpenAds();
            }
        });
    }

    /**
     * Interstitial PreLoad
     */

    public static void AllAdsPreLoad() {
        NextAnimation.main_context = (Activity) contextx;
        SmallAnimation.main_context = (Activity) contextx;
        BigAnimation.main_context = (Activity) contextx;

        if (MyProHelperClass.getBanner_preload().equals("1")) {
            MixAdOnBanner();
        }
        if (MyProHelperClass.getNative_preload().equals("1")) {
            MixAdOnNative();
        }
        if (MyProHelperClass.getInterPreLoad().equals("1")) {
            MixAdOnInter();
        }
        //Off Open Ads
        if (on_offAds == 1) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    NextIntent(contextx, intentx);
                }
            }, 3000);
        }
    }

    private static void MixAdOnBanner() {
        /**
         * Banner
         */
        /*Google Banner*/
        if (MyProHelperClass.getGoogleBanner() != null && !MyProHelperClass.getGoogleBanner().isEmpty()) {

            if (MyProHelperClass.getGoogleBanner().equals(MyProHelperClass.getGoogleBanner1()) && MyProHelperClass.getGoogleBanner().equals(MyProHelperClass.getGoogleBanner2()) && MyProHelperClass.getGoogleBanner1().equals(MyProHelperClass.getGoogleBanner2())) {
                MyProHelperClass.Google_banner_number = 1;
                SmallAnimation.AutoGoogleBannerID = 1;
                SmallAnimation.GoogleBannerPreload();

            } else {
                if (MyProHelperClass.getGoogleBanner2() == null) {
                    MyProHelperClass.Google_banner_number = 2;
                    SmallAnimation.GoogleBannerPreload1();
                    SmallAnimation.GoogleBannerPreload2();

                } else {
                    MyProHelperClass.Google_banner_number = 3;
                    SmallAnimation.GoogleBannerPreload1();
                    SmallAnimation.GoogleBannerPreload2();
                    SmallAnimation.GoogleBannerPreload3();

                }
            }
        }

        /*Facebook Banner*/
        if (MyProHelperClass.getFacebookBanner() != null && !MyProHelperClass.getFacebookBanner().isEmpty()) {
            SmallAnimation.AutoLoadFBBannerID = 1;
            SmallAnimation.FacebookBannerPreLoad();
        }

        /*AppLoving Banner*/
        if (MyProHelperClass.getAppLovinBanner() != null && !MyProHelperClass.getAppLovinBanner().isEmpty()) {
            SmallAnimation.AppLovingBannerPreLoad();
        }

        /*Unity Banner*/
        if (MyProHelperClass.getUnityBannerID() != null && !MyProHelperClass.getUnityBannerID().isEmpty()) {
            SmallAnimation.UnityBannerPreLoad();
        }
    }

    private static void MixAdOnNative() {
        /**
         * Native
         */
        /*Google Native*/
        if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {
            if (MyProHelperClass.getGoogleNative().equals(MyProHelperClass.getGoogleNative1()) && MyProHelperClass.getGoogleNative().equals(MyProHelperClass.getGoogleNative2()) && MyProHelperClass.getGoogleNative1().equals(MyProHelperClass.getGoogleNative2())) {
                MyProHelperClass.Google_native_number = 1;
                BigAnimation.AutoGoogleNativeID = 1;
                BigAnimation.GoogleNativePreload();
            } else {
                if (MyProHelperClass.getGoogleNative2() == null) {
                    MyProHelperClass.Google_native_number = 2;
                    BigAnimation.GoogleNativePreload1();
                    BigAnimation.GoogleNativePreload2();
                } else {
                    MyProHelperClass.Google_native_number = 3;
                    BigAnimation.GoogleNativePreload1();
                    BigAnimation.GoogleNativePreload2();
                    BigAnimation.GoogleNativePreload3();
                }
            }
        }

        /*Facebook Native*/
        if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {
            BigAnimation.AutoLoadFBNativeID = 1;
            BigAnimation.FacebookNativePreLoad();
        }

        /*AppLoving Native*/
        if (MyProHelperClass.getAppLovinNative() != null && !MyProHelperClass.getAppLovinNative().isEmpty()) {
            BigAnimation.AppLovingNativePreLoad();
        }

    }

    private static void MixAdOnInter() {
        /**
         * Inter
         */
        /*Google Inter*/
        if (MyProHelperClass.getGoogleInter() != null && !MyProHelperClass.getGoogleInter().isEmpty()) {

            //Inter
            if (MyProHelperClass.getGoogleInter().equals(MyProHelperClass.getGoogleInter1()) && MyProHelperClass.getGoogleInter().equals(MyProHelperClass.getGoogleInter2()) && MyProHelperClass.getGoogleInter1().equals(MyProHelperClass.getGoogleInter2())) {
                MyProHelperClass.Google_inter_number = 1;
                NextAnimation.AutoGoogleInterID = 1;
                NextAnimation.GoogleInterPreload();
            } else {
                if (MyProHelperClass.getGoogleInter2() == null) {
                    MyProHelperClass.Google_inter_number = 2;
                    NextAnimation.GoogleInterPreload1();
                    NextAnimation.GoogleInterPreload2();
                } else {
                    MyProHelperClass.Google_inter_number = 3;
                    NextAnimation.GoogleInterPreload1();
                    NextAnimation.GoogleInterPreload2();
                    NextAnimation.GoogleInterPreload3();
                }
            }
        }

        /*Facebook Mix Auto Load Inter*/
        if (MyProHelperClass.getFacebookInter() != null && !MyProHelperClass.getFacebookInter().isEmpty()) {
            NextAnimation.AutoLoadFBInterID = 1;
            NextAnimation.FacebookInterPreLoad();
        }

    }


    /**
     * Mix Open Ads
     */

    /*Mix Open*/
    private static void MixOpenAds(String valueOf) {
        if (valueOf.equals("g")) {
            GoogleAppOpen();
        } else if (valueOf.equals("f")) {
            FaceBookAppOpen();
        } else if (valueOf.equals("a")) {
            AppLovingAppOpen();
        } else if (valueOf.equals("u")) {
            UnityAppOpen();
        } else if (valueOf.equals("q")) {
            NextIntent(contextx, intentx);
            MyProHelperClass.BtnAutolink();
        } else if (valueOf.equals("c")) {
            CustomOpenAds();
        } else {
            NextIntent(contextx, intentx);
        }
    }

    /**
     * Country Check
     */
    public static String getCountryCode() {
        TelephonyManager tm = (TelephonyManager) contextx.getSystemService(contextx.getApplicationContext().TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }

    public static Boolean CheckCountry(String Country_name) {
        try {
            List<String> COUNTRY = new ArrayList<String>(Arrays.asList(Country_name.split(",")));
            String tm = getCountryCode();
            for (int i = 0; i < COUNTRY.size(); i++) {
                if (COUNTRY.get(i).equals(tm)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void ShareApp(Context context, String AppName) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, AppName);
            String shareMessage = "\nInstall this cool application\n\n";
            shareMessage = shareMessage + "Check out the App at : https://play.google.com/store/apps/details?id=" + PackName + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }

    }

    public static void RateApp(Context context) {
        try {
            Uri marketUri = Uri.parse("market://details?id=" + PackName);
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(marketIntent);
        } catch (ActivityNotFoundException e) {
            Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=" + PackName);
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(marketIntent);
        }
    }


    //Without preload ads
    private static void InOpenAds() {
        GoogleAppOpen();
    }

}
