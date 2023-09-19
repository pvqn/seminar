package com.example.seminar.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.seminar.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun dao(): ItemDao

    companion object {
        private var INSTANCE: ItemDatabase? = null

        fun getInstance(context: Context) : ItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ItemDatabase::class.java,
                    "item_db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                (0..100).forEach {
                                    database.dao().insert(Item(it, "Item $it"))
                                }
                            }
                        }
                    }
                }).build()

                INSTANCE = instance
                instance
            }
        }
    }
}