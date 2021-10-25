package com.example.reworksample.model.repository.network;

import com.example.reworksample.model.model.FlexibleDeskRespone;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("flexible-desk/v4/")
    Flowable<FlexibleDeskRespone> getFlexibleDesk();
}
