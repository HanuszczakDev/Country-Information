package com.hanuszczak.countryinformation.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hanuszczak.countryinformation.model.entity.CountryEntity

@Dao
interface CountryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countriesArray: Array<CountryEntity>)

    @Update
    suspend fun update(countryEntity: CountryEntity)

    @Delete
    suspend fun delete(countryEntity: CountryEntity)

    @Query("SELECT * FROM country ORDER BY name_official ASC")
    fun getAll(): LiveData<List<CountryEntity>>
}