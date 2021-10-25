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
    private MutableLiveData<FlexibleDeskRespone> flexibleDeskResponeMutableLiveData;
    public ObservableBoolean isLoading=new ObservableBoolean(false);
    private RetrofitInterface retrofitInterface;
    private AppDbHelper appDbHelper;

    public HomeViewModel() {
    }
    public void initData(Context context)
    {
        if (retrofitInterface==null)
        {
            this.retrofitInterface= RetrofitAPI.getService();
        }
        if (appDbHelper==null)
        {
            this.appDbHelper=AppDbHelper.getsInstance(AppDatabase.getInstance(context));
        }
    }
    public Disposable getFlexibleDesk()
    {
        isLoading.set(true);
        Disposable disposable=retrofitInterface.getFlexibleDesk().
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(flexibleDeskRespone -> {
                    // onNext()
                    isLoading.set(false);
                    flexibleDeskResponeMutableLiveData.setValue(flexibleDeskRespone);

        }, throwable -> {
                    // onError()
                    isLoading.set(false);
                    Log.e("DDVH: ", throwable.getMessage());
                });
        return disposable;
    }
    public Disposable insertFlexibleDesk(FlexibleDeskEntity flexibleDeskEntity, SetOnClickItem setOnClickItem)
    {
        isLoading.set(true);
        Disposable disposable=appDbHelper.insertEntity(flexibleDeskEntity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            setOnClickItem.onSuccess(flexibleDeskEntity.name);
        }, throwable -> {
            setOnClickItem.onFail(throwable.getMessage());
        });
        return disposable;
    }

    public MutableLiveData<FlexibleDeskRespone> getFlexibleDeskResponeMutableLiveData() {
        return flexibleDeskResponeMutableLiveData;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }
}
