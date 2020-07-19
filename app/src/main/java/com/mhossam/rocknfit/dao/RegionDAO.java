package com.mhossam.rocknfit.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mhossam.rocknfit.model.Country;
import com.mhossam.rocknfit.model.District;
import com.mhossam.rocknfit.model.Governorate;

import java.util.List;

@Dao
public interface RegionDAO {
    @Query("SELECT * FROM country")
    List<Country> getAllCountries();

    @Query("SELECT * FROM country WHERE countryID IN (:countryIds)")
    List<Country> loadAllCountriesByIds(int[] countryIds);

    @Query("SELECT * FROM country WHERE countryID = :countryID")
    Country findCountryByID(int countryID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCountries(Country ... country);

    @Query("SELECT * FROM Governorate")
    List<Governorate> getAllGovernorate();

    @Query("SELECT * FROM Governorate WHERE governorateID IN (:governerateIds)")
    List<Governorate> loadAllGovernoratesByIds(int[] governerateIds);

    @Query("SELECT * FROM Governorate WHERE governorateID = :governerateId")
    Governorate findGovernerateByID(int governerateId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllGovernorates(Governorate ... governorate);


    @Query("SELECT * FROM District")
    List<District> getAllDistricts();

    @Query("SELECT * FROM District WHERE districtID IN (:districtIds)")
    List<District> loadAllDistrictssByIds(int[] districtIds);

    @Query("SELECT * FROM District WHERE districtID = :districtID")
    District findDistrictByID(int districtID);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllDistricts(District ... district);
}
