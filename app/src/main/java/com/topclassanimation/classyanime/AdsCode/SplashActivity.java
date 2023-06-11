package com.topclassanimation.classyanime.AdsCode;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.animatakar.mysuperlibs.Splash;
import com.topclassanimation.classyanime.MainActivity;
import com.topclassanimation.classyanime.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Splash.StartAnimation(this, new Intent(this, MainActivity.class),
                "Test", "1", 0, getResources().getString(R.string.token));
    }
}