package com.example.myapplication_labexam3.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity()
data class Shop(
    var item:String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
