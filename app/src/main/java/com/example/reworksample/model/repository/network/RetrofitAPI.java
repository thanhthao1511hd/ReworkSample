package com.example.reworksample.model.repository.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    static Retrofit retrofit;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            final OkHttpClient client = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS).build();
            client.dispatcher().setMaxRequests(8);
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://develop.api.spaceshare.vn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client).build();
        }
        return retrofit;
    }
    public static RetrofitInterface getService()
    {
        return getRetrofit().create(RetrofitInterface.class);
    }
}
