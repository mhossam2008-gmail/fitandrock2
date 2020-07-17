package com.mhossam.rocknfit.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mhossam.rocknfit.model.AccountInfo;
import com.mhossam.rocknfit.model.LoggedInUser;

import java.util.List;

@Dao
public interface LoggedInUserDao {
    @Query("SELECT * FROM loggedinuser")
    List<LoggedInUser> getAll();

    @Query("SELECT * FROM loggedinuser WHERE accountID IN (:userIds)")
    List<LoggedInUser> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM loggedinuser WHERE accountFirstName LIKE :first AND " +
            "accountLastName LIKE :last LIMIT 1")
    LoggedInUser findByName(String first, String last);

    @Query("SELECT * FROM loggedinuser LIMIT 1")
    LoggedInUser getLoggedInUser();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(LoggedInUser... users);

    @Query("DELETE FROM loggedinuser")
    void delete();
}
