package dk.jonathan.nespresso.capsules.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dk.jonathan.nespresso.capsules.model.Capsule

@Database(entities = arrayOf(Capsule::class) ,version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun capsuleDao(): CapsuleDao

}