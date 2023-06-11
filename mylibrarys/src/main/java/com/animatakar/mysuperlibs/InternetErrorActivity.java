package com.animatakar.mysuperlibs;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InternetErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_error);
    }

    public void check_internet(View view) {
        if (MyProHelperClass.isOnline(this)) {
            finish();
        } else {
            Toast.makeText(this, "Check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (MyProHelperClass.isOnline(this)) {
            finish();
        } else {
            Toast.makeText(this, "Check your internet", Toast.LENGTH_SHORT).show();
        }
    }

}