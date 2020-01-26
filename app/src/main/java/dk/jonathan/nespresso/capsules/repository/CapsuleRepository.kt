package dk.jonathan.nespresso.capsules.repository

import dk.jonathan.nespresso.capsules.database.CapsuleDao
import dk.jonathan.nespresso.capsules.database.DatabaseSingleton
import dk.jonathan.nespresso.capsules.model.Capsule

class CapsuleRepository {

    fun getAllCapsules():List<Capsule>{
        var db: CapsuleDao = DatabaseSingleton.getDB().capsuleDao()
        return db.getAll()
    }

    fun createCapsule(capsule: Capsule): Capsule{
       var db: CapsuleDao = DatabaseSingleton.getDB().capsuleDao()
        db.insertAll(capsule)
        return capsule
    }

    fun getCapsuleById(id: Int):Capsule{
        var db: CapsuleDao = DatabaseSingleton.getDB().capsuleDao()
        return db.getOneById(id)
    }

    fun putCapsule(capsule: Capsule) {
        var db: CapsuleDao = DatabaseSingleton.getDB().capsuleDao()
        db.update(capsule)
    }

    fun deleteCapsule(capsule: Capsule){
        var db: CapsuleDao = DatabaseSingleton.getDB().capsuleDao()
        db.delete(capsule)
    }

    fun deleteCapsule(id:Int){
        var capsule: Capsule = getCapsuleById(id)
        deleteCapsule(capsule)
    }
}