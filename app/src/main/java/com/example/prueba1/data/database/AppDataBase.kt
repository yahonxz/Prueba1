package com.example.prueba1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prueba1.data.dao.ServiceDao
import com.example.prueba1.data.model.ServiceEntity

@Database(entities = [ServiceEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
}