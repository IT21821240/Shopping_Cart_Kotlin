package com.example.myapplication_labexam3.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShopDao {
    @Insert
    suspend fun insert(shop: Shop)

    @Delete
    suspend fun delete(shop: Shop)

    @Query("SELECT * FROM Shop")
    fun getALLShopItems():List<Shop>

    @Query("SELECT * FROM Shop WHERE id=:id")
    fun getOne(id:Int):Shop
}