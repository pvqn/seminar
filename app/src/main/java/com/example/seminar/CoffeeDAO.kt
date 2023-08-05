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
}