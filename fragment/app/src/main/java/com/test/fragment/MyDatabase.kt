package com.test.fragment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ContactModel::class], version = 1, exportSchema = false)
public abstract class MyDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao


    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {

            INSTANCE?.let {
                return it
            }

            return synchronized(MyDatabase::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "fragment_db"
                ).build()

                INSTANCE = instance

                instance
            }
        }

    }


}