package com.topclassanimation.classyanime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.animatakar.mysuperlibs.BigAnimation;
import com.animatakar.mysuperlibs.NextAnimation;
import com.animatakar.mysuperlibs.SmallAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BigAnimation.TopAnimation(this, findViewById(R.id.top_animation), "big");//Native Code

        SmallAnimation.BottomAnimation(this, findViewById(R.id.bottom_animation));//Banner Code
    }

    public void ADS(View view) {
//        NextAnimation.NextSliderAnimation(this, new Intent(this, TestingActivity.class), 0);//Next Interstitial
        NextAnimation.NextSliderAnimation(this, new Intent(this,TestingActivity.class), 0);//Next Interstitial
    }

    @Override
    public void onBackPressed() {
        NextAnimation.BackAnimation(this, null);//Back Interstitial
    }
}
