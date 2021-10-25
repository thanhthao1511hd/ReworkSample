package com.example.reworksample.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reworksample.model.model.Result;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter({"imageUrl", "error"})
    public static void loadImageWithError(ImageView img, String url, Drawable error){
        Glide.with(img.getContext()).load(url).error(error).into(img);
    }
    @androidx.databinding.BindingAdapter("imageUrl")
    public static void loadImage(ImageView img, String url)
    {
        Glide.with(img.getContext()).load(url).into(img);
    }
    @androidx.databinding.BindingAdapter("setTextInPoint")
    public static void setTextInPoint(TextView tv, String point)
    {
        tv.setText(point+" P");
    }
    @androidx.databinding.BindingAdapter("setTextAddress")
    public static void setTextAddress(TextView tv, Result result)
    {
        String text="";
        text=result.getSpaceMeta().getAddress()+", "+result.getSpaceMeta().getShortenAddress();
        tv.setText(text);

    }
}
