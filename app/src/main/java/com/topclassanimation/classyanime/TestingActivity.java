package com.topclassanimation.classyanime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.animatakar.mysuperlibs.BigAnimation;
import com.animatakar.mysuperlibs.NextAnimation;


public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        BigAnimation.TopAnimation(this, findViewById(R.id.top_animation),"big");
//        SmallAnimation.BottomAnimation( this, findViewById(R.id.bottom_animation));
    }

    @Override
    public void onBackPressed() {
         NextAnimation.BackAnimation(TestingActivity.this,null);
    }

    public void ADS(View view) {
//        startActivity(new Intent(this,MainActivity.class));
//        NextAnimation.NextSliderAnimation(this);
        NextAnimation.NextSliderAnimation(this, new Intent(this, MainActivity.class),0);
    }


}