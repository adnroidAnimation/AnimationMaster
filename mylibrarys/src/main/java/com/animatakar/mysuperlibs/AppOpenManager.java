package com.animatakar.mysuperlibs;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class AppOpenManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {

    private AppOpenAd appOpenAd = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private Activity activity;
    private static boolean isShowingAd = false;
    private final Application myApplication;
    OnAppOpenClose onAppOpenClose;
    int myids1;
    public static String app_id;

    public interface OnAppOpenClose {
        void OnAppOpenFailToLoad();

        void OnAppOpenClose();
    }

    public AppOpenManager(String open_ad_id, Application myApplication, OnAppOpenClose onAppOpenClose) {
        this.app_id = open_ad_id;
        this.myApplication = myApplication;
        this.onAppOpenClose = onAppOpenClose;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        myids1 = 1;
    }


    public void fetchAd() {
        // Have unused ad, no need to fetch another.
        if (isAdAvailable()) {
            return;
        }
        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(AppOpenAd ad) {
                AppOpenManager.this.appOpenAd = ad;
                if (myids1 == 1) {
                    myids1 = 0;
                    showAdIfAvailable();
                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                onAppOpenClose.OnAppOpenFailToLoad();

            }
        };
        AdRequest request = getAdRequest();
        AppOpenAd.load(myApplication, app_id, request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    public void showAdIfAvailable() {
        if (!isShowingAd && isAdAvailable()) {
            FullScreenContentCallback fullScreenContentCallback =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
                            AppOpenManager.this.appOpenAd = null;
                            isShowingAd = false;
                            fetchAd();
                            onAppOpenClose.OnAppOpenClose();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            isShowingAd = true;
                        }
                    };

            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
            appOpenAd.show(activity);

        } else {
            fetchAd();
        }
    }


    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return appOpenAd != null;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        activity = activity;

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        activity = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        activity = null;
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
        showAdIfAvailable();
    }
}