package com.mhossam.rocknfit.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mhossam.rocknfit.dao.AccountInfoDao;
import com.mhossam.rocknfit.model.AccountInfo;

@Database(entities = {AccountInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountInfoDao accountInfoDao();
}

