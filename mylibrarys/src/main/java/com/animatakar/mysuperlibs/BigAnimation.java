package com.animatakar.mysuperlibs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.NativeAdListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BigAnimation {

    /*Google*/
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads1 = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads2 = null;
    public static com.google.android.gms.ads.nativead.NativeAd google_native_ads3 = null;
    public static int AutoGoogleNativeID;

    public static com.google.android.gms.ads.nativead.NativeAd regular_google_native_banner = null;
    public static AdLoader regular_google_banner_ad_loader;


    /*Facebook*/
    public static com.facebook.ads.NativeAd facebook_native_ads = null;
    public static int AutoLoadFBNativeID;

    /*AppLoving*/
    public static MaxNativeAdLoader appLoving_native_ads_loader;
    public static MaxNativeAdView max_nativeAdView;
    public static MaxAd appLoving_native_ads = null;

    /*Unity*/
    public static BannerView regular_unity_banner_adView;


    /*Helper*/
    public static RelativeLayout main_native;
    public static Activity main_context;

    /*Mix*/
    public static int auto_notShow_ads_native = 0;
    public static int mix_ads_native = 0;
    public static int auto_native_show_id = 0;

    public static String Ad_Size = "small";


    /**
     * NATIVE ADS CODE START
     */
    public static void TopAnimation(Activity context1, RelativeLayout main_native1, String ad_Size) {
        main_context = context1;
        main_native = main_native1;
        Ad_Size = ad_Size;

        if (!MyProHelperClass.isOnline(context1)) {
            context1.startActivity(new Intent(context1, InternetErrorActivity.class));
            return;
        }
        /*Stop Ad*/
        if (MyProHelperClass.getCounter_Native() == 0) {
            return;
        }

        /*Skip Ads*/
        if (MyProHelperClass.getCounter_Native() != 5000) {
            auto_notShow_ads_native++;
            if (MyProHelperClass.getCounter_Native() + 1 == auto_notShow_ads_native) {
                auto_notShow_ads_native = 0;
                if (MyProHelperClass.getNative_preload().equals("0")) {
                    if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
                        StopPreLoadNativeMixAds();
                    } else {
                        StopPreLoadNative();
                    }
                } else {
                    if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
                        NativeMixAds();
                    } else {
                        RegularAds();
                    }
                }
                return;
            }
            return;
        }

        //on Demand
        if (MyProHelperClass.getNative_preload().equals("0")) {
            if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
                StopPreLoadNativeMixAds();
            } else {
                StopPreLoadNative();
            }
            return;
        }


        /*Mix Ads*/
        if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
            NativeMixAds();
        } else {
            RegularAds();
        }

    }


    private static void StopPreLoadNative() {
        if (MyProHelperClass.getGoogleEnable().equals("1")) {
            GoogleSmallNativeLoadDialog();
            StopPreGoogleNative();
        } else if (MyProHelperClass.getFacebookEnable().equals("1")) {
            FacebookNativeLoadDialog();
            StopPreFacebookNative();
        } else if (MyProHelperClass.getAppLovinEnable().equals("1")) {
            GoogleSmallNativeLoadDialog();
            StopPreAppLovingNative();
        } else if (MyProHelperClass.getUnityEnable().equals("1")) {
            GoogleSmallNativeLoadDialog();
            StopPreUnityNative();
        } else {
            main_native.removeAllViews();
        }
    }

    private static void RegularAds() {
        if (MyProHelperClass.getGoogleEnable().equals("1")) {
            GoogleADSNativeShow("r");
        } else if (MyProHelperClass.getFacebookEnable().equals("1")) {
            FacebookNativeShow();
        } else if (MyProHelperClass.getAppLovinEnable().equals("1")) {
            AppLovingNativeShow();
        } else {
            main_native.removeAllViews();
        }
    }

    /**
     * Regular Ads Show
     */
    /*Google*/
    private static void GoogleADSNativeShow(String checker) {
        if (MyProHelperClass.Google_native_number == 1) {
            RegularGoogleNativeShow(checker);
        } else if (MyProHelperClass.Google_native_number == 2) {
            if (auto_native_show_id == 0) {
                auto_native_show_id = 1;
                RegularGoogleNativeShow1(checker);
            } else {
                auto_native_show_id = 0;
                RegularGoogleNativeShow2(checker);
            }
        } else if (MyProHelperClass.Google_native_number == 3) {
            if (auto_native_show_id == 0) {
                auto_native_show_id = 1;
                RegularGoogleNativeShow1(checker);
            } else if (auto_native_show_id == 1) {
                auto_native_show_id = 2;
                RegularGoogleNativeShow2(checker);
            } else {
                auto_native_show_id = 0;
                RegularGoogleNativeShow3(checker);
            }
        }
    }

    private static void RegularGoogleNativeShow(String checker) {
        if (google_native_ads != null) {
            if (Ad_Size.equals("small")) {
                GoogleNativePopulaterSmallShow(google_native_ads);
            } else if (Ad_Size.equals("medium")) {

                GoogleNativePopulaterMediumShow(google_native_ads);

            } else if (Ad_Size.equals("big")) {

                GoogleNativePopulaterLargeShow(google_native_ads);

            }
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("g");
    }


    private static void RegularGoogleNativeShow1(String checker) {
        if (google_native_ads1 != null) {

            if (Ad_Size.equals("small")) {
                GoogleNativePopulaterSmallShow(google_native_ads);
            } else if (Ad_Size.equals("medium")) {
                GoogleNativePopulaterMediumShow(google_native_ads);
            } else if (Ad_Size.equals("big")) {
                GoogleNativePopulaterLargeShow(google_native_ads);
            }
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("g1");
    }

    private static void RegularGoogleNativeShow2(String checker) {
        if (google_native_ads2 != null) {
            if (Ad_Size.equals("small")) {
                GoogleNativePopulaterSmallShow(google_native_ads);
            } else if (Ad_Size.equals("medium")) {

                GoogleNativePopulaterMediumShow(google_native_ads);

            } else if (Ad_Size.equals("big")) {

                GoogleNativePopulaterLargeShow(google_native_ads);

            }
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("g2");
    }

    private static void RegularGoogleNativeShow3(String checker) {
        if (google_native_ads3 != null) {

            if (Ad_Size.equals("small")) {
                GoogleNativePopulaterSmallShow(google_native_ads);
            } else if (Ad_Size.equals("medium")) {

                GoogleNativePopulaterMediumShow(google_native_ads);

            } else if (Ad_Size.equals("big")) {

                GoogleNativePopulaterLargeShow(google_native_ads);

            }
        } else {
            AllGoogle_Fails_Other_Ads_Show(checker);
        }
        AllAdsPreLoadsNative("g3");
    }

    private static void Google_Fails_Facebook_Show() {
        if (facebook_native_ads != null) {

            if (Ad_Size.equals("medium")) {

                FacebookNativePopulateMediumShow();

            } else if (Ad_Size.equals("big")) {

                FacebookNativePopulateShow();

            }

        } else {
            Google_Facebook_Fails_AppLoving_Show();
        }
        AllAdsPreLoadsNative("f");
    }


    private static void Google_Facebook_Fails_AppLoving_Show() {
        if (appLoving_native_ads != null) {
            main_native.removeAllViews();
            main_native.addView(max_nativeAdView);
        }
        AllAdsPreLoadsNative("a");
    }

    private static void AllGoogle_Fails_Other_Ads_Show(String checker) {
        if (checker.equals("r")) {
            Google_Fails_Facebook_Show();
        } else if (checker.equals("f")) {
            Google_Facebook_Fails_AppLoving_Show();
        } else if (checker.equals("a")) {
            AppLoving_Google_Fails_facebook_Show();
        }
    }

    public static void GoogleNativePopulateShow(com.google.android.gms.ads.nativead.NativeAd
                                                        main_google_native_ads) {
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) main_context.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        nativeAdView.setMediaView((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media));
        ((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.getMediaView().setMediaContent(main_google_native_ads.getMediaContent());
        nativeAdView.findViewById(R.id.ad_call_to_action).setBackground(ContextCompat.getDrawable(main_context, R.drawable.app_btn));
        try {
            ((TextView) nativeAdView.getHeadlineView()).setText(main_google_native_ads.getHeadline());
            if (main_google_native_ads.getBody() == null) {
                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(main_google_native_ads.getBody());
            }
            if (main_google_native_ads.getCallToAction() == null) {
                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
                if (MyProHelperClass.getGooglebutton_name() != null && !MyProHelperClass.getGooglebutton_name().isEmpty()) {
                    ((Button) nativeAdView.getCallToActionView()).setText(MyProHelperClass.getGooglebutton_name());
                } else {
                    ((Button) nativeAdView.getCallToActionView()).setText(main_google_native_ads.getCallToAction());
                }
                ((Button) nativeAdView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));
            }
            if (main_google_native_ads.getIcon() == null) {
                nativeAdView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(main_google_native_ads.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            nativeAdView.setNativeAd(main_google_native_ads);
            VideoController videoController = main_google_native_ads.getMediaContent().getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception unused) {
        }
        main_native.removeAllViews();
        main_native.addView(nativeAdView);
    }

    /*Facebook*/
    private static void FacebookNativeShow() {
        if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {

            if (Ad_Size.equals("medium")) {

                FacebookNativePopulateMediumShow();

            } else if (Ad_Size.equals("big")) {

                FacebookNativePopulateShow();

            }

        } else {
            GoogleADSNativeShow("f");
        }
        AllAdsPreLoadsNative("f");
    }

    private static void FacebookNativePopulateMediumShow() {


        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, main_native, false);
        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_medium_native_layout, main_native, false);

        facebook_native_ads.unregisterView();

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(facebook_native_ads.getAdvertiserName());
        nativeAdBody.setText(facebook_native_ads.getAdBodyText());
        nativeAdSocialContext.setText(facebook_native_ads.getAdSocialContext());
        nativeAdCallToAction.setVisibility(facebook_native_ads.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(facebook_native_ads.getAdCallToAction());
        nativeAdCallToAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));

        sponsoredLabel.setText(facebook_native_ads.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        facebook_native_ads.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);

        main_native.removeAllViews();
        main_native.addView(adView);

    }

    public static void FacebookNativePopulateShow() {
        LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, main_native, false);
//        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_medium_native_layout, main_native, false);

        facebook_native_ads.unregisterView();

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(facebook_native_ads.getAdvertiserName());
        nativeAdBody.setText(facebook_native_ads.getAdBodyText());
        nativeAdSocialContext.setText(facebook_native_ads.getAdSocialContext());
        nativeAdCallToAction.setVisibility(facebook_native_ads.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(facebook_native_ads.getAdCallToAction());
        nativeAdCallToAction.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));
        sponsoredLabel.setText(facebook_native_ads.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        facebook_native_ads.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);

        main_native.removeAllViews();
        main_native.addView(adView);

    }

    /*AppLoving*/
    private static void AppLovingNativeShow() {
        if (appLoving_native_ads != null) {
            main_native.removeAllViews();
            main_native.addView(max_nativeAdView);
        } else {
            GoogleADSNativeShow("a");
        }
        AllAdsPreLoadsNative("a");
    }

    private static void AppLoving_Google_Fails_facebook_Show() {
        if (facebook_native_ads != null) {
            if (Ad_Size.equals("medium")) {

                FacebookNativePopulateMediumShow();

            } else if (Ad_Size.equals("big")) {

                FacebookNativePopulateShow();

            }

        }
        AllAdsPreLoadsNative("f");
    }

    /*Custom Native*/
    private static void CustomADSNative() {
        if (Splash.adsViewModals.size() == 0) {
            main_native.removeAllViews();
            return;
        }
        int ads_number = MyProHelperClass.getRandomNumber(0, Splash.adsViewModals.size() - 1);
        LinearLayout native_view = (LinearLayout) main_context.getLayoutInflater().inflate(R.layout.custom_native, (ViewGroup) null);
        AppCompatButton btn_install = native_view.findViewById(R.id.btn_install);
        RelativeLayout full_click = native_view.findViewById(R.id.full_click);
        TextView app_name = native_view.findViewById(R.id.app_name);
        TextView app_shot = native_view.findViewById(R.id.app_shot);
        ImageView app_icon = native_view.findViewById(R.id.app_icon);
        ImageView ads_banner = native_view.findViewById(R.id.ads_banner);
        Glide.with(main_context).load(Splash.adsViewModals.get(ads_number).getApp_logo()).into(app_icon);
        Glide.with(main_context).load(Splash.adsViewModals.get(ads_number).getApp_banner()).into(ads_banner);
        app_name.setText(Splash.adsViewModals.get(ads_number).getAd_app_name());
        app_shot.setText(Splash.adsViewModals.get(ads_number).getApp_description());
        btn_install.setOnClickListener(v -> {
            try {
                main_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Splash.adsViewModals.get(ads_number).getApp_name())));
            } catch (android.content.ActivityNotFoundException anfe) {
                main_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Splash.adsViewModals.get(ads_number).getApp_name())));
            }
        });
        full_click.setOnClickListener(v -> {
            try {
                main_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Splash.adsViewModals.get(ads_number).getApp_name())));
            } catch (android.content.ActivityNotFoundException anfe) {
                main_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Splash.adsViewModals.get(ads_number).getApp_name())));
            }
        });
        main_native.removeAllViews();
        main_native.addView(native_view);
    }

    /**
     * Mix Ads Show
     */
    /*Mix Ads Helper*/
    private static void NativeMixAds() {
        if (MyProHelperClass.getmix_ad_native().length() == 0) {
            main_native.removeAllViews();
        } else {
            if (MyProHelperClass.getmix_ad_native().length() == 1) {
                Mix1AdsNative(MyProHelperClass.getmix_ad_native());  // 1 ads
            } else if (MyProHelperClass.getmix_ad_native().length() == 2) {
                Mix2AdsNative(MyProHelperClass.getmix_ad_native());  // 2 ads
            } else {
                MixUnlimitedAdsNative(MyProHelperClass.getmix_ad_native()); // Unlimited
            }
        }
    }

    private static void Mix1AdsNative(String s) {
        MixAdsShowNative(String.valueOf(s.charAt(0)));
    }

    private static void Mix2AdsNative(String s) {
        if (MyProHelperClass.getmix_ad_counter_native() != 5000) {
            mix_ads_native++;
            if (MyProHelperClass.getmix_ad_counter_native() + 1 == mix_ads_native) {
                MixAdsShowNative(String.valueOf(s.charAt(1)));
                mix_ads_native = 0;
            } else {
                MixAdsShowNative(String.valueOf(s.charAt(0)));
            }
        } else {
            if (mix_ads_native == 0) {
                mix_ads_native = 1;
                MixAdsShowNative(String.valueOf(s.charAt(0)));
            } else if (mix_ads_native == 1) {
                mix_ads_native = 0;
                MixAdsShowNative(String.valueOf(s.charAt(1)));
            }
        }
    }

    private static void MixUnlimitedAdsNative(String s) {
        MixAdsShowNative(String.valueOf(s.charAt(mix_ads_native)));
        if (MyProHelperClass.getmix_ad_native().length() - 1 == mix_ads_native) {
            mix_ads_native = 0;
        } else {
            mix_ads_native++;
        }
    }

    private static void MixAdsShowNative(String value) {
        if (value.equals("g")) {
            GoogleADSNativeShow("r");
        } else if (value.equals("f")) {
            FacebookNativeShow();
        } else if (value.equals("a")) {
            AppLovingNativeShow();
        } else {
            main_native.removeAllViews();
        }
    }


    /**
     * PreLoad
     */
    /*Google*/
    public static void GoogleNativePreload() {
        String google_load_id = null;
        if (AutoGoogleNativeID == 1) {
            google_load_id = MyProHelperClass.getGoogleNative();
        } else if (AutoGoogleNativeID == 2) {
            google_load_id = MyProHelperClass.getGoogleNative1();
        } else if (AutoGoogleNativeID == 3) {
            google_load_id = MyProHelperClass.getGoogleNative2();
        }
        AdLoader.Builder builder = new AdLoader.Builder(main_context, google_load_id);
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads = nativeAd;
                AutoGoogleNativeID = 1;

            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                if (AutoGoogleNativeID == 1) {
                    AutoGoogleNativeID = 2;
                    GoogleNativePreload();
                } else if (AutoGoogleNativeID == 2) {
                    AutoGoogleNativeID = 3;
                    GoogleNativePreload();
                } else {
                    google_native_ads = null;
                }
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public static void GoogleNativePreload1() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads1 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads1 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    public static void GoogleNativePreload2() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative1());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads2 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads2 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    public static void GoogleNativePreload3() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative2());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads3 = nativeAd;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                google_native_ads3 = null;
            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());

    }

    /*Facebook*/
    public static void FacebookNativePreLoad() {
        String facebook_load_id = null;
        if (AutoLoadFBNativeID == 1) {
            facebook_load_id = MyProHelperClass.getFacebookNative();
        } else if (AutoLoadFBNativeID == 2) {
            facebook_load_id = MyProHelperClass.getFacebookNative1();
        } else if (AutoLoadFBNativeID == 3) {
            facebook_load_id = MyProHelperClass.getFacebookNative2();
        }
        facebook_native_ads = new com.facebook.ads.NativeAd(main_context, facebook_load_id);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                if (AutoLoadFBNativeID == 1) {
                    AutoLoadFBNativeID = 2;
                    FacebookNativePreLoad();
                } else if (AutoLoadFBNativeID == 2) {
                    AutoLoadFBNativeID = 3;
                    FacebookNativePreLoad();
                } else {
                    facebook_native_ads = null;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                AutoLoadFBNativeID = 1;

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    /*AppLoving*/
    public static void AppLovingNativePreLoad() {
        appLoving_native_ads_loader = new MaxNativeAdLoader(MyProHelperClass.getAppLovinNative(), main_context);
        appLoving_native_ads_loader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                appLoving_native_ads = ad;
                max_nativeAdView = nativeAdView;
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int dpHeightInPx = (int) (300 * main_context.getResources().getDisplayMetrics().density);
                max_nativeAdView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));

            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                appLoving_native_ads = null;
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {

            }
        });
        appLoving_native_ads_loader.loadAd();
    }

    /*All Preload*/
    public static void AllAdsPreLoadsNative(String refresh) {
        if (refresh.equals("g")) {
            google_native_ads = null;
        } else if (refresh.equals("g1")) {
            google_native_ads1 = null;
        } else if (refresh.equals("g2")) {
            google_native_ads2 = null;
        } else if (refresh.equals("g3")) {
            google_native_ads3 = null;
        } else if (refresh.equals("f")) {
            facebook_native_ads = null;
        } else if (refresh.equals("a")) {
            appLoving_native_ads = null;
        }

        if (MyProHelperClass.Google_native_number == 1) {

            if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {
                if (google_native_ads == null) {
                    GoogleNativePreload();
                }
            }

        } else if (MyProHelperClass.Google_native_number == 2) {

            if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {
                if (google_native_ads1 == null) {
                    GoogleNativePreload1();
                }
            }
            if (MyProHelperClass.getGoogleNative1() != null && !MyProHelperClass.getGoogleNative1().isEmpty()) {
                if (google_native_ads2 == null) {
                    GoogleNativePreload2();
                }
            }
        } else if (MyProHelperClass.Google_native_number == 3) {

            if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {
                if (google_native_ads1 == null) {
                    GoogleNativePreload1();

                }
            }
            if (MyProHelperClass.getGoogleNative1() != null && !MyProHelperClass.getGoogleNative1().isEmpty()) {
                if (google_native_ads2 == null) {
                    GoogleNativePreload2();

                }
            }

            if (MyProHelperClass.getGoogleNative2() != null && !MyProHelperClass.getGoogleNative2().isEmpty()) {
                if (google_native_ads3 == null) {
                    GoogleNativePreload3();
                }
            }

        }

        if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {
            if (facebook_native_ads == null) {
                FacebookNativePreLoad();
            }
        }

        if (MyProHelperClass.getAppLovinNative() != null && !MyProHelperClass.getAppLovinNative().isEmpty()) {
            if (appLoving_native_ads == null) {
                AppLovingNativePreLoad();
            }
        }
    }

    /**
     * Stop pre load
     */

    private static void StopPreAppLovingNative() {

        appLoving_native_ads_loader = new MaxNativeAdLoader(MyProHelperClass.getAppLovinNative(), main_context);
        appLoving_native_ads_loader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                appLoving_native_ads = ad;
                max_nativeAdView = nativeAdView;
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int dpHeightInPx = (int) (300 * main_context.getResources().getDisplayMetrics().density);
                max_nativeAdView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
                if (appLoving_native_ads != null) {
                    main_native.removeAllViews();
                    main_native.addView(max_nativeAdView);
                }
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                appLoving_native_ads = null;
                Stop_Preload_apploving_fails_other_show();
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {

            }
        });
        appLoving_native_ads_loader.loadAd();
    }

    private static void StopPreFacebookNative() {
        facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyProHelperClass.getFacebookNative());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                facebook_native_ads = null;
                Stop_Preload_facebook_fails_other_show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
                    if (Ad_Size.equals("medium")) {

                        FacebookNativePopulateMediumShow();

                    } else if (Ad_Size.equals("big")) {

                        FacebookNativePopulateShow();

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
        facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    private static void StopPreGoogleNative() {
        AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative());
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                google_native_ads = nativeAd;
                if (google_native_ads != null) {
                    if (Ad_Size.equals("small")) {
                        GoogleNativePopulaterSmallShow(google_native_ads);
                    } else if (Ad_Size.equals("medium")) {
                        GoogleNativePopulaterMediumShow(google_native_ads);
                    } else if (Ad_Size.equals("big")) {
                        GoogleNativePopulaterLargeShow(google_native_ads);
                    }
                }
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Stop_Preload_google_fails_other_show();

            }

            public void onAdClicked() {
                super.onAdClicked();
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }


    private static void StopPreGoogleNativeSmall() {

        regular_google_banner_ad_loader = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleBanner()).forNativeAd(nativeAds -> {
            regular_google_native_banner = nativeAds;
        }).withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                regular_google_native_banner = null;
                main_native.removeAllViews();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (regular_google_native_banner != null) {
                    RegularGoogleBannerPopulateShow(regular_google_native_banner);
                }
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

        }).build();
        regular_google_banner_ad_loader.loadAd(new AdRequest.Builder().build());
    }

    public static void RegularGoogleBannerPopulateShow
            (com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        View layout_ad_view = LayoutInflater.from(main_context).inflate(R.layout.ad_google_native_small_banner, null);
        com.google.android.gms.ads.nativead.NativeAdView native_ad_view = layout_ad_view.findViewById(R.id.ad_view_small_banner);
        native_ad_view.setHeadlineView(native_ad_view.findViewById(R.id.ad_headline_small_banner));
        native_ad_view.setBodyView(native_ad_view.findViewById(R.id.ad_body_small_banner));
        native_ad_view.setCallToActionView(native_ad_view.findViewById(R.id.ad_call_to_action_small_banner));

        native_ad_view.setIconView(native_ad_view.findViewById(R.id.ad_app_icon_small_banner));
        ((TextView) Objects.requireNonNull(native_ad_view.getHeadlineView())).setText(nativeAd.getHeadline());
        ((TextView) Objects.requireNonNull(native_ad_view.getBodyView())).setText(nativeAd.getBody());
        ((TextView) Objects.requireNonNull(native_ad_view.getCallToActionView())).setText(nativeAd.getCallToAction());
        ((TextView) Objects.requireNonNull(native_ad_view.getCallToActionView())).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));

        if (nativeAd.getIcon() == null) {
            Objects.requireNonNull(native_ad_view.getIconView()).setVisibility(View.GONE);
        } else {
            ((ImageView) Objects.requireNonNull(native_ad_view.getIconView())).setImageDrawable(nativeAd.getIcon().getDrawable());
            native_ad_view.getIconView().setVisibility(View.VISIBLE);
        }
        native_ad_view.setNativeAd(nativeAd);
        main_native.removeAllViews();
        main_native.addView(layout_ad_view);
    }

    private static void StopPreUnityNative() {
        regular_unity_banner_adView = new BannerView((Activity) main_context, MyProHelperClass.getUnityBannerID(), new UnityBannerSize(320, 50));
        regular_unity_banner_adView.setListener(new BannerView.IListener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {
                if (regular_unity_banner_adView != null) {
                    main_native.removeAllViews();
                    main_native.addView(regular_unity_banner_adView);
                }
            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                regular_unity_banner_adView = null;
                main_native.removeAllViews();
            }

            @Override
            public void onBannerClick(BannerView bannerAdView) {

            }

            @Override
            public void onBannerLeftApplication(BannerView bannerAdView) {
            }
        });
        regular_unity_banner_adView.load();

    }

    private static void StopPreLoadNativeMixAds() {
        if (MyProHelperClass.getmix_ad_native().length() == 0) {
            main_native.removeAllViews();
        } else {
            if (MyProHelperClass.getmix_ad_native().length() == 1) {
                StopPreLoadMix1AdsNative(MyProHelperClass.getmix_ad_native());  // 1 ads
            } else if (MyProHelperClass.getmix_ad_native().length() == 2) {
                StopPreLoadMix2AdsNative(MyProHelperClass.getmix_ad_native());  // 2 ads
            } else {
                StopPreLoadMixUnlimitedAdsNative(MyProHelperClass.getmix_ad_native()); // Unlimited
            }
        }
    }

    private static void StopPreLoadMix1AdsNative(String s) {
        StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(0)));
    }


    private static void StopPreLoadMix2AdsNative(String s) {
        if (MyProHelperClass.getmix_ad_counter_native() != 5000) {
            mix_ads_native++;
            if (MyProHelperClass.getmix_ad_counter_native() + 1 == mix_ads_native) {
                StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(1)));
                mix_ads_native = 0;
            } else {
                StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(0)));
            }
        } else {
            if (mix_ads_native == 0) {
                mix_ads_native = 1;
                StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(0)));
            } else if (mix_ads_native == 1) {
                mix_ads_native = 0;
                StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(1)));
            }
        }
    }

    private static void StopPreLoadMixUnlimitedAdsNative(String s) {
        StopPreLoadMixAdsShowNative(String.valueOf(s.charAt(mix_ads_native)));
        if (MyProHelperClass.getmix_ad_native().length() - 1 == mix_ads_native) {
            mix_ads_native = 0;
        } else {
            mix_ads_native++;
        }
    }

    private static void StopPreLoadMixAdsShowNative(String value) {
        if (value.equals("g")) {
            GoogleSmallNativeLoadDialog();
            StopPreGoogleNative();
        } else if (value.equals("f")) {
            FacebookNativeLoadDialog();
            StopPreFacebookNative();
        } else if (value.equals("a")) {
            GoogleSmallNativeLoadDialog();
            StopPreAppLovingNative();
        } else if (value.equals("u")) {
            GoogleSmallNativeLoadDialog();
            StopPreUnityNative();
        } else {
            main_native.removeAllViews();
        }
    }

    public static void GoogleSmallNativeLoadDialog() {

        if (Ad_Size.equals("small")) {
            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.google_banner_load, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        } else if (Ad_Size.equals("medium")) {

            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.google_medium_native_load, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        } else if (Ad_Size.equals("big")) {

            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.google_big_native_load, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        }


    }

    public static void FacebookNativeLoadDialog() {


        if (Ad_Size.equals("medium")) {

//            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.google_medium_native_load, main_native, false);
//            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
//            layouts.startShimmer();
//            main_native.removeAllViews();
//            main_native.addView(load_view);

        } else if (Ad_Size.equals("big")) {

            LayoutInflater inflater = (LayoutInflater) main_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout load_view = (LinearLayout) inflater.inflate(R.layout.fb_big_native_load, main_native, false);
            ShimmerFrameLayout layouts = load_view.findViewById(R.id.shimmer_view_container);
            layouts.startShimmer();
            main_native.removeAllViews();
            main_native.addView(load_view);

        }
    }

    private static void GoogleNativePopulaterLargeShow(com.google.android.gms.ads.nativead.NativeAd native_ads) {
        NativeAdView nativeAdView = (NativeAdView) main_context.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        nativeAdView.setMediaView((MediaView) nativeAdView.findViewById(R.id.ad_media));
        ((MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.getMediaView().setMediaContent(native_ads.getMediaContent());
        try {
            ((TextView) nativeAdView.getHeadlineView()).setText(native_ads.getHeadline());
            if (native_ads.getBody() == null) {
                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(native_ads.getBody());
            }

            if (native_ads.getCallToAction() == null) {
                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
                ((Button) nativeAdView.getCallToActionView()).setText(native_ads.getCallToAction());
                ((Button) nativeAdView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));
            }

            if (native_ads.getIcon() == null) {
                nativeAdView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(native_ads.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            nativeAdView.setNativeAd(native_ads);
            VideoController videoController = native_ads.getMediaContent().getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception unused) {
        }

        main_native.removeAllViews();
        main_native.addView(nativeAdView);
    }

    private static void GoogleNativePopulaterMediumShow(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        NativeAdView nativeAdView = (NativeAdView) main_context.getLayoutInflater().inflate(R.layout.ad_google_medium_native, (ViewGroup) null);
        nativeAdView.setMediaView((MediaView) nativeAdView.findViewById(R.id.ad_media));
        ((MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        try {
            ((TextView) nativeAdView.getHeadlineView()).setText(nativeAd.getHeadline());
            if (nativeAd.getBody() == null) {
                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(nativeAd.getBody());
            }

            if (nativeAd.getCallToAction() == null) {
                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
                ((Button) nativeAdView.getCallToActionView()).setText(nativeAd.getCallToAction());
                ((Button) nativeAdView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));
            }

            if (nativeAd.getIcon() == null) {
                nativeAdView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            VideoController videoController = nativeAd.getMediaContent().getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception unused) {
        }
        nativeAdView.setNativeAd(nativeAd);
        main_native.addView(nativeAdView);
    }

    private static void GoogleNativePopulaterSmallShow(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        View layout_ad_view = LayoutInflater.from(main_context).inflate(R.layout.ad_google_native_small_banner, null);
        com.google.android.gms.ads.nativead.NativeAdView native_ad_view = layout_ad_view.findViewById(R.id.ad_view_small_banner);
        native_ad_view.setHeadlineView(native_ad_view.findViewById(R.id.ad_headline_small_banner));
        native_ad_view.setBodyView(native_ad_view.findViewById(R.id.ad_body_small_banner));
        native_ad_view.setCallToActionView(native_ad_view.findViewById(R.id.ad_call_to_action_small_banner));
        native_ad_view.setIconView(native_ad_view.findViewById(R.id.ad_app_icon_small_banner));
        ((TextView) Objects.requireNonNull(native_ad_view.getHeadlineView())).setText(nativeAd.getHeadline());
        ((TextView) Objects.requireNonNull(native_ad_view.getBodyView())).setText(nativeAd.getBody());
        ((TextView) Objects.requireNonNull(native_ad_view.getCallToActionView())).setText(nativeAd.getCallToAction());
        ((TextView) Objects.requireNonNull(native_ad_view.getCallToActionView())).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyProHelperClass.getGooglebutton_color())));

        if (nativeAd.getIcon() == null) {
            Objects.requireNonNull(native_ad_view.getIconView()).setVisibility(View.GONE);
        } else {
            ((ImageView) Objects.requireNonNull(native_ad_view.getIconView())).setImageDrawable(nativeAd.getIcon().getDrawable());
            native_ad_view.getIconView().setVisibility(View.VISIBLE);
        }
        native_ad_view.setNativeAd(nativeAd);
        main_native.removeAllViews();
        main_native.addView(layout_ad_view);

    }


    //On demand Google fail code
    private static void Stop_Preload_google_fails_other_show() {

        if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {

            facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyProHelperClass.getFacebookNative());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    Stop_preload_google_fails_facebook_fail_other_show();

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
                        if (Ad_Size.equals("medium")) {

                            FacebookNativePopulateMediumShow();

                        } else if (Ad_Size.equals("big")) {

                            FacebookNativePopulateShow();

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
            facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());

        } else {

            Stop_preload_google_fails_facebook_fail_other_show();
        }
    }

    private static void Stop_preload_google_fails_facebook_fail_other_show() {

        if (MyProHelperClass.getAppLovinNative() != null && !MyProHelperClass.getAppLovinNative().isEmpty()) {

            appLoving_native_ads_loader = new MaxNativeAdLoader(MyProHelperClass.getAppLovinNative(), main_context);
            appLoving_native_ads_loader.setNativeAdListener(new MaxNativeAdListener() {
                @Override
                public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                    appLoving_native_ads = ad;
                    max_nativeAdView = nativeAdView;
                    int width = ViewGroup.LayoutParams.MATCH_PARENT;
                    int dpHeightInPx = (int) (300 * main_context.getResources().getDisplayMetrics().density);
                    max_nativeAdView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
                    if (appLoving_native_ads != null) {
                        main_native.removeAllViews();
                        main_native.addView(max_nativeAdView);
                    }
                }

                @Override
                public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                    appLoving_native_ads = null;
                    main_native.removeAllViews();
                }

                @Override
                public void onNativeAdClicked(final MaxAd ad) {

                }
            });
            appLoving_native_ads_loader.loadAd();

        } else {

            main_native.removeAllViews();

        }

    }

    //On demand Facebook fail code
    private static void Stop_Preload_facebook_fails_other_show() {

        if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {

            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative());
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    google_native_ads = nativeAd;
                    if (google_native_ads != null) {
                        if (Ad_Size.equals("small")) {
                            GoogleNativePopulaterSmallShow(google_native_ads);
                        } else if (Ad_Size.equals("medium")) {
                            GoogleNativePopulaterMediumShow(google_native_ads);
                        } else if (Ad_Size.equals("big")) {
                            GoogleNativePopulaterLargeShow(google_native_ads);
                        }
                    }
                }
            });
            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    Stop_preload_google_fails_facebook_fail_other_show();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        } else {

            Stop_preload_google_fails_facebook_fail_other_show();

        }
    }


    //On demand Applovin fail code
    private static void Stop_Preload_apploving_fails_other_show() {

        if (MyProHelperClass.getGoogleNative() != null && !MyProHelperClass.getGoogleNative().isEmpty()) {

            AdLoader.Builder builder = new AdLoader.Builder(main_context, MyProHelperClass.getGoogleNative());
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    google_native_ads = nativeAd;
                    if (google_native_ads != null) {
                        if (Ad_Size.equals("small")) {
                            GoogleNativePopulaterSmallShow(google_native_ads);
                        } else if (Ad_Size.equals("medium")) {
                            GoogleNativePopulaterMediumShow(google_native_ads);
                        } else if (Ad_Size.equals("big")) {
                            GoogleNativePopulaterLargeShow(google_native_ads);
                        }
                    }
                }
            });
            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);

                    if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {

                        facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyProHelperClass.getFacebookNative());
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                facebook_native_ads = null;
                                main_native.removeAllViews();

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
                                    if (Ad_Size.equals("medium")) {

                                        FacebookNativePopulateMediumShow();

                                    } else if (Ad_Size.equals("big")) {

                                        FacebookNativePopulateShow();

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
                        facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());

                    } else {

                        main_native.removeAllViews();

                    }

                }

                public void onAdClicked() {
                    super.onAdClicked();
                }
            }).build().loadAd(new AdRequest.Builder().build());

        } else if (MyProHelperClass.getFacebookNative() != null && !MyProHelperClass.getFacebookNative().isEmpty()) {


            facebook_native_ads = new com.facebook.ads.NativeAd(main_context, MyProHelperClass.getFacebookNative());
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    facebook_native_ads = null;
                    main_native.removeAllViews();

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (facebook_native_ads != null && facebook_native_ads.isAdLoaded()) {
                        if (Ad_Size.equals("medium")) {

                            FacebookNativePopulateMediumShow();

                        } else if (Ad_Size.equals("big")) {

                            FacebookNativePopulateShow();

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
            facebook_native_ads.loadAd(facebook_native_ads.buildLoadAdConfig().withAdListener(nativeAdListener).build());

        } else {

            main_native.removeAllViews();

        }


    }

}

