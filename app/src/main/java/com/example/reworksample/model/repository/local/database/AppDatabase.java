package com.example.reworksample.model.repository.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.reworksample.model.repository.local.dao.FlexibleDeskDAO;
import com.example.reworksample.model.repository.local.entity.FlexibleDeskEntity;

@Database(entities = {FlexibleDeskEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FlexibleDeskDAO getDAO();

    private static AppDatabase appDatabase;
    private static final String DATABASE_NAME = "RoomDatabase_demo";

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();

        }
        return appDatabase;
    }


}
