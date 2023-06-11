package com.animatakar.mysuperlibs;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.res.ResourcesCompat;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Random;

public class MyProHelperClass extends Application {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static MyProHelperClass instance;

    public static MyProHelperClass app;
    public static int Entery_UpdateApps;

    /*Google*/
    public static int Google_inter_number;
    public static int Google_native_number;
    public static int Google_banner_number;

    public  static ArrayList<String> color_array = new ArrayList<>();

    public static synchronized MyProHelperClass getInstanceHelp() {
        MyProHelperClass application;
        synchronized (MyProHelperClass.class) {
            application = instance;
        }
        return application;
    }

    public static MyProHelperClass getInstant() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        /*Google*/
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        /*Facebook*/
        AudienceNetworkAds.initialize(this);
//        AdSettings.setTestMode(true);

        /*App Lovin*/
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
            }
        });

        sharedPreferences = getSharedPreferences("babaji", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        super.onCreate();

        color_array.add("#ffbf00");
        color_array.add("#e52b50");
        color_array.add("#9966cc");
        color_array.add("#a4c639");
        color_array.add("#5d8aa8");
        color_array.add("#6e7f80");
        color_array.add("#21abcd");
        color_array.add("#fe6f5e");
        color_array.add("#cb4154");
        color_array.add("#d19fe8");
        color_array.add("#de3163");
        color_array.add("#996515");
        color_array.add("#008b8b");
        color_array.add("#ffa07a");
        color_array.add("#778899");
        color_array.add("#e25098");
    }



    public static void setBackAdsOnOff(String BackAdsOnOff) {
        editor.putString("BackAdsOnOff", BackAdsOnOff).commit();
    }

    public static String getBackAdsOnOff() {
        return sharedPreferences.getString("BackAdsOnOff", null);
    }

    /**
     * Google ads
     */
    public static void setGoogleEnable(String GoogleEnable) {
        editor.putString("GoogleEnable", GoogleEnable).commit();
    }

    public static String getGoogleEnable() {
        return sharedPreferences.getString("GoogleEnable", null);
    }


    public static void setGoogle_OpenADS(String Google_OpenADS) {
        editor.putString("Google_OpenADS", Google_OpenADS).commit();
    }

    public static String getGoogle_OpenADS() {
        return sharedPreferences.getString("Google_OpenADS", null);
    }

    public static void setad_ICON(String ad_ICON) {
        editor.putString("ad_ICON", ad_ICON).commit();
    }

    public static String getad_ICON() {
        return sharedPreferences.getString("ad_ICON", null);
    }

    public static void setGooglebutton_color(String Googlebutton_color) {
        editor.putString("Googlebutton_color", Googlebutton_color).commit();
    }

    public static String getGooglebutton_color() {
        return sharedPreferences.getString("Googlebutton_color", null);
    }

    public static void setGooglebutton_name(String Googlebutton_name) {
        editor.putString("Googlebutton_name", Googlebutton_name).commit();
    }

    public static String getGooglebutton_name() {
        return sharedPreferences.getString("Googlebutton_name", null);
    }

    public static void SetGoogleInter(String GoogleInter) {
        editor.putString("GoogleInter", GoogleInter).commit();
    }

    public static String getGoogleInter() {
        return sharedPreferences.getString("GoogleInter", null);
    }

    public static void SetGoogleInter1(String GoogleInter1) {
        editor.putString("GoogleInter1", GoogleInter1).commit();
    }

    public static String getGoogleInter1() {
        return sharedPreferences.getString("GoogleInter1", null);
    }

    public static void SetGoogleInter2(String GoogleInter2) {
        editor.putString("GoogleInter2", GoogleInter2).commit();
    }

    public static String getGoogleInter2() {
        return sharedPreferences.getString("GoogleInter2", null);
    }

    public static void SetGoogleBanner(String GoogleBanner) {
        editor.putString("GoogleBanner", GoogleBanner).commit();
    }

    public static String getGoogleBanner() {
        return sharedPreferences.getString("GoogleBanner", null);
    }

    public static void SetGoogleBanner1(String GoogleBanner1) {
        editor.putString("GoogleBanner1", GoogleBanner1).commit();
    }

    public static String getGoogleBanner1() {
        return sharedPreferences.getString("GoogleBanner1", null);
    }

    public static void SetGoogleBanner2(String GoogleBanner2) {
        editor.putString("GoogleBanner2", GoogleBanner2).commit();
    }

    public static String getGoogleBanner2() {
        return sharedPreferences.getString("GoogleBanner2", null);
    }


    public static void SetGoogleNative(String GoogleNative) {
        editor.putString("GoogleNative", GoogleNative).commit();
    }

    public static String getGoogleNative() {
        return sharedPreferences.getString("GoogleNative", null);
    }

    public static void SetGoogleNative1(String GoogleNative1) {
        editor.putString("GoogleNative1", GoogleNative1).commit();
    }

    public static String getGoogleNative1() {
        return sharedPreferences.getString("GoogleNative1", null);
    }

    public static void SetGoogleNative2(String GoogleNative2) {
        editor.putString("GoogleNative2", GoogleNative2).commit();
    }

    public static String getGoogleNative2() {

        return sharedPreferences.getString("GoogleNative2", null);
    }


    /**
     * Facebook
     */
    public static void setFacebookEnable(String FacebookEnable) {
        editor.putString("FacebookEnable", FacebookEnable).commit();
    }

    public static String getFacebookEnable() {
        return sharedPreferences.getString("FacebookEnable", null);
    }


    public static void setFacebookBanner(String FacebookBanner) {
        editor.putString("FacebookBanner", FacebookBanner).commit();
    }

    public static String getFacebookBanner() {
        return sharedPreferences.getString("FacebookBanner", null);
    }

    public static void setFacebookBanner1(String FacebookBanner1) {
        editor.putString("FacebookBanner1", FacebookBanner1).commit();
    }

    public static String getFacebookBanner1() {
        return sharedPreferences.getString("FacebookBanner1", null);
    }

    public static void setFacebookBanner2(String FacebookBanner2) {
        editor.putString("FacebookBanner2", FacebookBanner2).commit();
    }

    public static String getFacebookBanner2() {
        return sharedPreferences.getString("FacebookBanner2", null);
    }

    public static void SetFacebookInter(String FacebookInter) {
        editor.putString("FacebookInter", FacebookInter).commit();
    }

    public static String getFacebookInter() {
        return sharedPreferences.getString("FacebookInter", null);
    }

    public static void SetFacebookInter1(String FacebookInter1) {
        editor.putString("FacebookInter1", FacebookInter1).commit();
    }

    public static String getFacebookInter1() {
        return sharedPreferences.getString("FacebookInter1", null);
    }

    public static void SetFacebookInter2(String FacebookInter2) {
        editor.putString("FacebookInter2", FacebookInter2).commit();
    }

    public static String getFacebookInter2() {
        return sharedPreferences.getString("FacebookInter2", null);
    }

    public static void SetFacebookNative(String FacebookNative) {
        editor.putString("FacebookNative", FacebookNative).commit();
    }

    public static String getFacebookNative() {
        return sharedPreferences.getString("FacebookNative", null);
    }

    public static void SetFacebookNative1(String FacebookNative1) {
        editor.putString("FacebookNative1", FacebookNative1).commit();
    }

    public static String getFacebookNative1() {
        return sharedPreferences.getString("FacebookNative1", null);
    }

    public static void SetFacebookNative2(String FacebookNative2) {
        editor.putString("FacebookNative2", FacebookNative2).commit();
    }

    public static String getFacebookNative2() {
        return sharedPreferences.getString("FacebookNative2", null);
    }

    /**
     * AppLovin
     */
    public static void setAppLovinEnable(String AppLovinEnable) {
        editor.putString("AppLovinEnable", AppLovinEnable).commit();
    }

    public static String getAppLovinEnable() {
        return sharedPreferences.getString("AppLovinEnable", null);
    }

    public static void setAppLovinBanner(String AppLovinBanner) {
        editor.putString("AppLovinBanner", AppLovinBanner).commit();
    }

    public static String getAppLovinBanner() {
        return sharedPreferences.getString("AppLovinBanner", null);
    }

    public static void setAppLovinNative(String AppLovinNative) {
        editor.putString("AppLovinNative", AppLovinNative).commit();
    }

    public static String getAppLovinNative() {
        return sharedPreferences.getString("AppLovinNative", null);
    }

    public static void setAppLovinInter(String AppLovinInter) {
        editor.putString("AppLovinInter", AppLovinInter).commit();
    }

    public static String getAppLovinInter() {
        return sharedPreferences.getString("AppLovinInter", null);
    }


    /**
     * Unity
     */
    public static void setUnityEnable(String UnityEnable) {
        editor.putString("UnityEnable", UnityEnable).commit();
    }

    public static String getUnityEnable() {
        return sharedPreferences.getString("UnityEnable", null);
    }

    public static void setUnityAppID(String UnityAppID) {
        editor.putString("UnityAppID", UnityAppID).commit();
    }

    public static String getUnityAppID() {
        return sharedPreferences.getString("UnityAppID", null);
    }

    public static void setUnityBannerID(String UnityBannerID) {
        editor.putString("UnityBannerID", UnityBannerID).commit();
    }

    public static String getUnityBannerID() {
        return sharedPreferences.getString("UnityBannerID", null);
    }

    public static void setUnityInterID(String UnityInterID) {
        editor.putString("UnityInterID", UnityInterID).commit();
    }

    public static String getUnityInterID() {
        return sharedPreferences.getString("UnityInterID", null);
    }

    /**
     * Custom ads
     */
    public static void setCustomEnable(String CustomEnable) {
        editor.putString("CustomEnable", CustomEnable).commit();
    }

    public static String getCustomEnable() {
        return sharedPreferences.getString("CustomEnable", null);
    }


    /**
     * Qureka link
     */
    public static void setauto_link_on_off(String auto_link_on_off) {
        editor.putString("auto_link_on_off", auto_link_on_off).commit();
    }

    public static String getauto_link_on_off() {
        return sharedPreferences.getString("auto_link_on_off", null);
    }

    public static void setauto_link_array(String auto_link_array) {
        editor.putString("auto_link_array", auto_link_array).commit();
    }

    public static String getauto_link_array() {
        return sharedPreferences.getString("auto_link_array", null);
    }


    public static void setauto_link_timer(String auto_link_timer) {
        editor.putString("auto_link_timer", auto_link_timer).commit();
    }

    public static String getauto_link_timer() {
        return sharedPreferences.getString("auto_link_timer", null);
    }

    public static void set_q_link_btn_on_off(String _q_link_btn_on_off) {
        editor.putString("_q_link_btn_on_off", _q_link_btn_on_off).commit();
    }

    public static String get_q_link_btn_on_off() {
        return sharedPreferences.getString("_q_link_btn_on_off", null);
    }

    public static void set_q_link_array(String _q_link_array) {
        editor.putString("_q_link_array", _q_link_array).commit();
    }

    public static String get_q_link_array() {
        return sharedPreferences.getString("_q_link_array", null);
    }


    /**
     * Skip ADS
     */
    public static void setCounter_Inter(Integer Counter) {
        editor.putInt("Counter", Counter).commit();
    }

    public static Integer getCounter_Inter() {
        return sharedPreferences.getInt("Counter", 5000);
    }

    public static void setCounter_Banner(Integer CounterBanner) {
        editor.putInt("CounterBanner", CounterBanner).commit();
    }

    public static Integer getCounter_Banner() {
        return sharedPreferences.getInt("CounterBanner", 5000);
    }

    public static void setOpenAdsShow(Integer OpenAdsShow) {
        editor.putInt("OpenAdsShow", OpenAdsShow).commit();
    }

    public static Integer getOpenAdsShow() {
        return sharedPreferences.getInt("OpenAdsShow", 5000);
    }

    public static void setCounter_Native(Integer CounterNative) {
        editor.putInt("CounterNative", CounterNative).commit();
    }

    public static Integer getCounter_Native() {
        return sharedPreferences.getInt("CounterNative", 5000);
    }


    public static void setBackCounter(Integer BackCounter) {
        editor.putInt("BackCounter", BackCounter).commit();
    }

    public static Integer getBackCounter() {
        return sharedPreferences.getInt("BackCounter", 5000);
    }

    /**
     * Skip Country
     */
    public static void setSkip_country_on_off(String skip_country_on_off) {
        editor.putString("skip_country_on_off", skip_country_on_off).commit();
    }

    public static String getSkip_country_on_off() {
        return sharedPreferences.getString("skip_country_on_off", null);
    }

    public static void setSkip_country_list(String skip_country_list) {
        editor.putString("skip_country_list", skip_country_list).commit();
    }

    public static String getSkip_country_list() {
        return sharedPreferences.getString("skip_country_list", null);
    }



    /**
     * MIX ADS
     */
    public static void setmix_ad_on_off(String mix_ad_on_off) {
        editor.putString("mix_ad_on_off", mix_ad_on_off).commit();
    }

    public static String getmix_ad_on_off() {
        return sharedPreferences.getString("mix_ad_on_off", null);
    }

    public static void setmix_ad_name(String mix_ad_name) {
        editor.putString("mix_ad_name", mix_ad_name).commit();
    }

    public static String getmix_ad_name() {
        return sharedPreferences.getString("mix_ad_name", null);
    }

    public static void setmix_ad_banner(String mix_ad_banner) {
        editor.putString("mix_ad_banner", mix_ad_banner).commit();
    }

    public static String getmix_ad_banner() {
        return sharedPreferences.getString("mix_ad_banner", null);
    }

    public static void setmix_ad_native(String mix_ad_native) {
        editor.putString("mix_ad_native", mix_ad_native).commit();
    }

    public static String getmix_ad_native() {
        return sharedPreferences.getString("mix_ad_native", null);
    }

    public static void setmix_ad_inter(String mix_ad_inter) {
        editor.putString("mix_ad_inter", mix_ad_inter).commit();
    }

    public static String getmix_ad_inter() {
        return sharedPreferences.getString("mix_ad_inter", null);
    }

    public static void setmix_ad_counter_inter(Integer mix_ad_counter) {
        editor.putInt("mix_ad_counter", mix_ad_counter).commit();
    }

    public static Integer getmix_ad_counter_inter() {
        return sharedPreferences.getInt("mix_ad_counter", 5000);
    }

    public static void setmix_ad_counter_native(Integer mix_ad_counter_native) {
        editor.putInt("mix_ad_counter_native", mix_ad_counter_native).commit();
    }

    public static Integer getmix_ad_counter_native() {
        return sharedPreferences.getInt("mix_ad_counter_native", 5000);
    }

    public static void setmix_ad_counter_banner(Integer mix_ad_counter_banner) {
        editor.putInt("mix_ad_counter_banner", mix_ad_counter_banner).commit();
    }

    public static Integer getmix_ad_counter_banner() {
        return sharedPreferences.getInt("mix_ad_counter_banner", 5000);
    }

    public static void setfacebook_open_ad_id(String facebook_open_ad_id) {
        editor.putString("facebook_open_ad_id", facebook_open_ad_id).commit();
    }

    public static String getfacebook_open_ad_id() {
        return sharedPreferences.getString("facebook_open_ad_id", null);
    }


    public static void setExtraSwitch_4(String ExtraSwitch_4) {
        editor.putString("ExtraSwitch_4", ExtraSwitch_4).commit();
    }

    public static String getExtraSwitch_4() {
        return sharedPreferences.getString("ExtraSwitch_4", null);
    }  //1 preload start //o preload stop


    public static void setExtraBtn_2(String ExtraBtn_2) {
        editor.putString("ExtraBtn_2", ExtraBtn_2).commit();
    }

    public static String getExtraBtn_2() {
        return sharedPreferences.getString("ExtraBtn_2", null);
    }

    public static void setExtraBtn_3(String ExtraBtn_3) {
        editor.putString("ExtraBtn_3", ExtraBtn_3).commit();
    }

    public static String getExtraBtn_3() {
        return sharedPreferences.getString("ExtraBtn_3", null);
    }

    public static void setExtraBtn_4(String ExtraBtn_4) {
        editor.putString("ExtraBtn_4", ExtraBtn_4).commit();
    }

    public static String getExtraBtn_4() {
        return sharedPreferences.getString("ExtraBtn_4", null);
    }

    public static void setExtraBtn_5(String ExtraBtn_5) {
        editor.putString("ExtraBtn_5", ExtraBtn_5).commit();
    }

    public static String getExtraBtn_5() {
        return sharedPreferences.getString("ExtraBtn_5", null);
    }


    public static void setUpdateApps(String UpdateApps) {
        editor.putString("UpdateApps", UpdateApps).commit();
    }

    public static String getUpdateApps() {
        return sharedPreferences.getString("UpdateApps", null);
    }


    public static void setAppversioncode(String Appversioncode) {
        editor.putString("Appversioncode", Appversioncode).commit();
    }

    public static String getAppversioncode() {
        return sharedPreferences.getString("Appversioncode", null);
    }


    public static void setOtherAppsShow(String OtherAppsShow) {
        editor.putString("OtherAppsShow", OtherAppsShow).commit();
    }

    public static String getOtherAppsShow() {
        return sharedPreferences.getString("OtherAppsShow", null);
    }

    public static void setOtherAppsShowLink(String OtherAppsShowLink) {
        editor.putString("OtherAppsShowLink", OtherAppsShowLink).commit();
    }

    public static String getOtherAppsShowLink() {
        return sharedPreferences.getString("OtherAppsShowLink", null);
    }


    /**
     * Preload ON OFF
     *
     */
    public static void setInterPreLoad(String InterPreLoad) {
        editor.putString("InterPreLoad", InterPreLoad).commit();
    }

    public static String getInterPreLoad() {
        return sharedPreferences.getString("InterPreLoad", null);
    }

     public static void setNative_preload(String Native_preload) {
        editor.putString("Native_preload", Native_preload).commit();
    }

    public static String getNative_preload() {
        return sharedPreferences.getString("Native_preload", null);
    }

     public static void setBanner_preload(String Banner_preload) {
        editor.putString("Banner_preload", Banner_preload).commit();
    }

    public static String getBanner_preload() {
        return sharedPreferences.getString("Banner_preload", null);
    }


    public static void LinkopenChromeCustomTabUrl(Context context, String Link) {

        try {

            if (isAppInstalled("com.android.chrome", context)) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ResourcesCompat.getColor(context.getResources(), R.color.purple_700, null));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setPackage("com.android.chrome");
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(Link));


            } else {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ResourcesCompat.getColor(context.getResources(), R.color.purple_700, null));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(Link));
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static boolean isAppInstalled(String packageName, Context context) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }


    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }


    public static void Autolink() {
        String[] Auto_Link = MyProHelperClass.getauto_link_array().split(",");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Autolink();
                LinkOpenChromeCustomTabUrl(instance, Auto_Link[getRandomNumber(0, Auto_Link.length - 1)]);
            }
        }, Integer.parseInt(MyProHelperClass.getauto_link_timer()));
    }

    public static void LinkOpenChromeCustomTabUrl(Context context, String Link) {
        try {
            if (MyProHelperClass.isAppInstalled("com.android.chrome", context)) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ResourcesCompat.getColor(context.getResources(), R.color.purple_700, null));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setPackage("com.android.chrome");
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(Link));

            } else {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ResourcesCompat.getColor(context.getResources(), R.color.purple_700, null));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(Link));
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static void BtnAutolink() {
        String[] Auto_Link = MyProHelperClass.get_q_link_array().split(",");
        if (Auto_Link.length == 1) {
            LinkOpenChromeCustomTabUrl(instance, Auto_Link[0]);
            return;
        }
        LinkOpenChromeCustomTabUrl(instance, Auto_Link[getRandomNumber(0, Auto_Link.length - 1)]);
    }

    public static boolean isOnline(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }


    public static Dialog startLoader(final Context context) {
        final Dialog d = new Dialog(context);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        d.setContentView(R.layout.progress);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        d.show();
        d.getWindow().setAttributes(lp);
        return d;
    }

    public static void stopLoader(final Dialog d) {
        if (d != null && d.isShowing()) {
            d.cancel();
        }
    }
}




