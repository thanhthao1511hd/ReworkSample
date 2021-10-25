package com.example.reworksample.model.repository.local.database;

import com.example.reworksample.model.repository.local.entity.FlexibleDeskEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class AppDbHelper implements DbHelper{

    private static AppDbHelper sInstance;
    private AppDatabase appDatabase;


    public AppDbHelper(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public static AppDbHelper getsInstance(AppDatabase appDatabase) {
        if (sInstance==null)
        {
            sInstance=new AppDbHelper(appDatabase);
        }
        return sInstance;
    }
    @Override
    public Flowable<List<FlexibleDeskEntity>> getAllEntities() {
        return appDatabase.getDAO().getAll();
    }

    @Override
    public Completable insertEntity(FlexibleDeskEntity... flexibleDeskEntities) {
        return appDatabase.getDAO().insertAll(flexibleDeskEntities);
    }

    @Override
    public Completable deleteEntity(FlexibleDeskEntity... flexibleDeskEntities) {
        return appDatabase.getDAO().delete(flexibleDeskEntities);
    }

    @Override
    public Completable updateEntity(FlexibleDeskEntity... flexibleDeskEntities) {
        return appDatabase.getDAO().update(flexibleDeskEntities);
    }
}
