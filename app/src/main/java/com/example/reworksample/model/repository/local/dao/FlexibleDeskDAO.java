package com.example.reworksample.model.repository.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.reworksample.model.repository.local.entity.FlexibleDeskEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


@Dao
public interface FlexibleDeskDAO {

    @Query("SELECT * FROM FlexibleDeskEntity")
    Flowable<List<FlexibleDeskEntity>> getAll();
//    @Query("select * from FlexibleDeskEntity where id (:userId)")
//    Flowable<List<FlexibleDeskEntity>> loadAllById(int[] userId);

    @Insert
    Completable insertAll(FlexibleDeskEntity... deskEntities);
    @Delete
    Completable delete(FlexibleDeskEntity... deskEntities);
    @Update
    Completable update(FlexibleDeskEntity... deskEntities);
}
