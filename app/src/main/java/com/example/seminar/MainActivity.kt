package com.example.seminar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


@Database(entities = [Coffee::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDAO


}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "coffee_table"
            ).build()
            val inputStream = resources.openRawResource(R.raw.coffee_data)
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            val jsonObject = JSONObject(jsonString)

            val coffeeArray = jsonObject.getJSONArray("coffees")
            Log.d("ok","ok")

            val coffeeList = Gson().fromJson<List<Coffee>>(coffeeArray.toString(), object : TypeToken<List<Coffee>>() {}.type)

            val coffeeDao = db.coffeeDao()
            for (coffee in coffeeList) {
                coffeeDao.insert(coffee)
            }
            println(coffeeList[0].name);
        }
    }
}