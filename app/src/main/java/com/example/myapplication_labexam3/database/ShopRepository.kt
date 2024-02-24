package com.example.myapplication_labexam3.database

import com.example.myapplication_labexam3.database.Shop
import com.example.myapplication_labexam3.database.ShopDataBase

class ShopRepository (
    private val db: ShopDataBase
){
    suspend fun insert(shop: Shop) = db.getShopDao().insert(shop)
    suspend fun delete(shop: Shop) = db.getShopDao().delete(shop)
    fun getAllShopItems():List<Shop> = db.getShopDao().getALLShopItems()
}

