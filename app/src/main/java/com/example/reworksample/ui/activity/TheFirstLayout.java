package com.example.reworksample.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.reworksample.MainActivity;
import com.example.reworksample.R;
import com.example.reworksample.databinding.ActivityTheFirstLayoutBinding;

public class TheFirstLayout extends AppCompatActivity {
    ActivityTheFirstLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_the_first_layout);
        Handler handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent =new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }, 2000);
    }
}