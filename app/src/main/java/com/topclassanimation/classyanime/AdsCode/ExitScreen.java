package com.topclassanimation.classyanime.AdsCode;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.animatakar.mysuperlibs.NextAnimation;

import com.topclassanimation.classyanime.R;


public class ExitScreen extends Activity {

    ImageView txt_rate, txt_no, txt_yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_screen);
        txt_rate = findViewById(R.id.txt_rate);
        txt_yes = findViewById(R.id.txt_yes);
        txt_no = findViewById(R.id.txt_no);

        txt_rate.setOnClickListener(v -> rate());
        txt_yes.setOnClickListener(v -> yes());
        txt_no.setOnClickListener(v -> no());
    }

    private void rate() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    private void yes() {
        finish();
        finishAffinity();
    }

    private void no() {
        NextAnimation.BackAnimation(ExitScreen.this, null);
    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(ExitScreen.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.back_dialog_exit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.findViewById(R.id.no_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.yes_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yes();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    protected void onResume() {
        super.onResume();

//        AdsClass.NativeAds( this, findViewById(R.id.native_detail),
//                findViewById(R.id.banner_native), findViewById(R.id.addcontain)
//                , findViewById(R.id.ad_native_fb));

    }
}
