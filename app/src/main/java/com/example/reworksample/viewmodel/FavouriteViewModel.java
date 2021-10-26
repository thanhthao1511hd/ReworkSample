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
    private MutableLiveData<List<FlexibleDeskEntity>> flexibleDeskLiveData  = new MutableLiveData<>();
    public ObservableBoolean isLoading = new ObservableBoolean(false);

    private RetrofitInterface retrofit;
    private AppDbHelper appDbHelper;


    public FavouriteViewModel() {

    }

    public void initData(Context context){
        if(retrofit == null){
            this.retrofit = RetrofitAPI.getService();
        }
        if(appDbHelper == null){
            this.appDbHelper = AppDbHelper.getsInstance(AppDatabase.getInstance(context));
        }
    }

    public Disposable deleteFlexibleDesk(FlexibleDeskEntity flexibleDeskEntity, SetOnClickItem onClickItemFlexibleDeskCallBack){
        isLoading.set(true);
        Disposable disposable = appDbHelper.deleteEntity(flexibleDeskEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    //on Complete
                    // updateUI
                    onClickItemFlexibleDeskCallBack.onSuccess(flexibleDeskEntity.name);
                },throwable -> {
                    //onError
                    onClickItemFlexibleDeskCallBack.onFail(throwable.getMessage());
                });

        return  disposable;
    }

    public Disposable upDateFlexibleDesk(FlexibleDeskEntity flexibleDeskEntity, SetOnClickItem onClickItemFlexibleDeskCallBack){
        isLoading.set(true);
        Disposable disposable = appDbHelper.updateEntity(flexibleDeskEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    //on Complete
                    // updateUI
                    onClickItemFlexibleDeskCallBack.onSuccess(flexibleDeskEntity.name);
                },throwable -> {
                    //onError
                    onClickItemFlexibleDeskCallBack.onFail(throwable.getMessage());
                });

        return  disposable;
    }

    public Disposable getAllFlexibleDesk(){
        isLoading.set(true);
        Disposable disposable = appDbHelper.getAllEntities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flexibleDeskEntities -> {
                    // onNext
                    flexibleDeskLiveData.setValue(flexibleDeskEntities);
                }, throwable -> {
                    //on Error
                },() -> {
                    //on Complete
                });

        return  disposable;
    }

    public MutableLiveData<List<FlexibleDeskEntity>> getFlexibleDeskLiveData() {
        return flexibleDeskLiveData;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }
}
