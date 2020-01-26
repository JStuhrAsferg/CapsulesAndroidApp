package dk.jonathan.nespresso.capsules.database

import androidx.room.Room
import dk.jonathan.nespresso.capsules.MainActivity

object DatabaseSingleton{

    val db: AppDatabase = Room.databaseBuilder(
        MainActivity.appContext,
        AppDatabase::class.java, "capsule_database"
    ).allowMainThreadQueries().build()

    fun getDB():AppDatabase{
        return this.db
    }
}