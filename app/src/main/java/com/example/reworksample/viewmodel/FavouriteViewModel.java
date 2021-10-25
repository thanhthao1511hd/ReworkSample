package com.example.reworksample.viewmodel;

import android.content.Context;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.reworksample.model.repository.local.database.AppDatabase;
import com.example.reworksample.model.repository.local.database.AppDbHelper;
import com.example.reworksample.model.repository.local.entity.FlexibleDeskEntity;
import com.example.reworksample.model.repository.network.RetrofitAPI;
import com.example.reworksample.model.repository.network.RetrofitInterface;
import com.example.reworksample.ui.contact.SetOnClickItem;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavouriteViewModel extends ViewModel {
    private MutableLiveData<List<FlexibleDeskEntity>> fleListMutableLiveData=new MutableLiveData<>();
    public ObservableBoolean isLoading=new ObservableBoolean(false);
    private RetrofitInterface retrofitInterface;
    private AppDbHelper appDbHelper;

    public FavouriteViewModel() {
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<List<FlexibleDeskEntity>> getFleListMutableLiveData() {
        return fleListMutableLiveData;
    }
    public void initData(Context context)
    {
        if (retrofitInterface==null)
        {
            this.retrofitInterface= RetrofitAPI.getService();

        }
        if(appDbHelper==null)
        {
            this.appDbHelper=AppDbHelper.getsInstance(AppDatabase.getInstance(context));
        }
    }
    public Disposable deleteFlexibleDesk(FlexibleDeskEntity flexibleDeskEntity, SetOnClickItem onClickItem)
    {
        isLoading.set(true);
        Disposable disposable=appDbHelper.deleteEntity(flexibleDeskEntity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            // conComplete
            // updateUI
            onClickItem.onSuccess(flexibleDeskEntity.name);
        }, throwable -> {
            // onError
            onClickItem.onFail(throwable.getMessage());
        });
        return disposable;

    }
    public Disposable updateFlexibleDesk(FlexibleDeskEntity flexibleDeskEntity, SetOnClickItem onClickItem)
    {
        isLoading.set(true);
        Disposable disposable=appDbHelper.updateEntity(flexibleDeskEntity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            onClickItem.onSuccess(flexibleDeskEntity.name);
        }, throwable -> {
            onClickItem.onFail(throwable.getMessage());
        });
        return disposable;
    }
    public Disposable getAllFlexibleDesk()
    {
        isLoading.set(true);
        Disposable disposable=appDbHelper.getAllEntities().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(flexibleDeskEntities ->
        {
            fleListMutableLiveData.setValue(flexibleDeskEntities);
        }, throwable -> {

        }, () -> {

        });
        return disposable;
    }
}
