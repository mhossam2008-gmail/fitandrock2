package com.mhossam.rocknfit.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mhossam.rocknfit.model.PredefinedClass;

import java.util.List;

@Dao
public interface LookupsDao {
    @Query("SELECT * FROM predefinedclass")
    List<PredefinedClass> getAllPredefinedClasses();

    @Query("SELECT * FROM predefinedclass WHERE classID IN (:classIds)")
    List<PredefinedClass> loadAllPredefinedClassesByIds(int[] classIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PredefinedClass... predefinedClass);

    @Delete
    void delete(PredefinedClass predefinedClass);
}



