package com.example.reworksample.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.reworksample.model.model.FlexibleDeskRespone;
import com.example.reworksample.model.repository.local.database.AppDatabase;
import com.example.reworksample.model.repository.local.database.AppDbHelper;
import com.example.reworksample.model.repository.local.entity.FlexibleDeskEntity;
import com.example.reworksample.model.repository.network.RetrofitAPI;
import com.example.reworksample.model.repository.network.RetrofitInterface;
import com.example.reworksample.ui.contact.SetOnClickItem;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<FlexibleDeskRespone> flexibleDeskResponeLiveData  = new MutableLiveData<>();
    public ObservableBoolean isLoading = new ObservableBoolean(false);

    private RetrofitInterface retrofit;
    private AppDbHelper appDbHelper;


    public HomeViewModel() {

    }

    public void initData(Context context){
        if(retrofit == null){
            this.retrofit = RetrofitAPI.getService();
        }
        if(appDbHelper == null){
            this.appDbHelper = AppDbHelper.getsInstance(AppDatabase.getInstance(context));
        }

    }

    public Disposable getFlexibleDesk(){
        isLoading.set(true);
        Disposable disposable = retrofit.getFlexibleDesk()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flexibleDesk -> {
                    //onNext
                    isLoading.set(false);
                    flexibleDeskResponeLiveData.setValue(flexibleDesk);
                }, throwable -> {
                    //onError
                    isLoading.set(false);
                    Log.e("DDVH", throwable.getMessage());
                });

        return  disposable;
    }

    public Disposable insertFlexibleDesk(FlexibleDeskEntity flexibleDeskEntity, SetOnClickItem onClickBtnBookCallBack){
        isLoading.set(true);
        Disposable disposable = appDbHelper.insertEntity(flexibleDeskEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    //on Complete
                    // updateUI
                    onClickBtnBookCallBack.onSuccess(flexibleDeskEntity.name);
                },throwable -> {
                    //onError
                    onClickBtnBookCallBack.onFail(throwable.getMessage());
                });

        return  disposable;
    }



    public MutableLiveData<FlexibleDeskRespone> getFlexibleDeskResponeLiveData() {
        return flexibleDeskResponeLiveData;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }
}