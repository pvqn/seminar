package com.example.seminar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoffeeDAO {
    @Insert
    suspend fun insert(coffee: Coffee)

    @Query("SELECT * FROM coffee_table")
    suspend fun getAllCoffee(): List<Coffee>

    @Query("SELECT * FROM coffee_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int): List<Coffee>
}