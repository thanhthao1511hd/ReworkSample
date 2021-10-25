package com.example.reworksample.model.repository.local.database;

import com.example.reworksample.model.repository.local.entity.FlexibleDeskEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface DbHelper {

    // recent search
    Flowable<List<FlexibleDeskEntity>> getAllEntities();
    Completable insertEntity(FlexibleDeskEntity... flexibleDeskEntities);
    Completable deleteEntity(FlexibleDeskEntity... flexibleDeskEntities);
    Completable updateEntity(FlexibleDeskEntity... flexibleDeskEntities);
}
