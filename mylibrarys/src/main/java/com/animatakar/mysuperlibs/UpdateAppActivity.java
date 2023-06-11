package com.animatakar.mysuperlibs;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_app);
    }

    public void Update(View view) {

        if (MyProHelperClass.Entery_UpdateApps == 1) {
            MyProHelperClass.LinkopenChromeCustomTabUrl(this, "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());

        } else if (MyProHelperClass.Entery_UpdateApps == 2) {
            MyProHelperClass.LinkopenChromeCustomTabUrl(this, MyProHelperClass.getOtherAppsShowLink());
        }
    }

    @Override
    public void onBackPressed() {

    }
}