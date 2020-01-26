package dk.jonathan.nespresso.capsules.database

import androidx.room.*
import dk.jonathan.nespresso.capsules.model.Capsule

@Dao
interface CapsuleDao {
    @Query("SELECT * FROM capsule")
    fun getAll(): List<Capsule>

    @Query("SELECT * FROM capsule WHERE id = :id")
    fun getOneById(id: Int): Capsule

    @Insert
    fun insertAll(vararg capsule: Capsule)

    @Delete
    fun delete(capsule: Capsule)

    @Update
    fun update(capsule: Capsule)
}