package com.mhossam.rocknfit.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mhossam.rocknfit.model.AccountInfo;

import java.util.List;

@Dao
public interface AccountInfoDao {
    @Query("SELECT * FROM accountInfo")
    List<AccountInfo> getAll();

    @Query("SELECT * FROM accountInfo WHERE accountID IN (:userIds)")
    List<AccountInfo> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM accountinfo WHERE accountFirstName LIKE :first AND " +
            "accountLastName LIKE :last LIMIT 1")
    AccountInfo findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(AccountInfo... users);

    @Delete
    void delete(AccountInfo user);
}



