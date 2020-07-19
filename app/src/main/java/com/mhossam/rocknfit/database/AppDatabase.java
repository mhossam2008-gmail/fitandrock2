package com.mhossam.rocknfit.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mhossam.rocknfit.dao.AccountInfoDao;
import com.mhossam.rocknfit.dao.LoggedInUserDao;
import com.mhossam.rocknfit.dao.RegionDAO;
import com.mhossam.rocknfit.model.AccountInfo;
import com.mhossam.rocknfit.model.Country;
import com.mhossam.rocknfit.model.District;
import com.mhossam.rocknfit.model.Governorate;
import com.mhossam.rocknfit.model.LoggedInUser;

@Database(entities = {AccountInfo.class,
        LoggedInUser.class,
        Country.class,
        Governorate.class,
        District.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountInfoDao accountInfoDao();
    public abstract LoggedInUserDao loggedInUserDao();
    public abstract RegionDAO regionDAO();
}

