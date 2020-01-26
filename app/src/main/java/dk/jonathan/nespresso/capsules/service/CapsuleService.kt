package dk.jonathan.nespresso.capsules.service

import android.graphics.Paint
import dk.jonathan.nespresso.capsules.model.Capsule
import dk.jonathan.nespresso.capsules.repository.CapsuleRepository


class CapsuleService {

    private var capsuleRepository: CapsuleRepository? = CapsuleRepository()

    fun getAllCapsules():List<Capsule>{
        return capsuleRepository!!.getAllCapsules()
    }

    fun getCapsuleById(id: Int): Capsule {
        return capsuleRepository!!.getCapsuleById(id)
    }

    fun createCapsule(capsule: Capsule): Capsule? {
        return this.capsuleRepository!!.createCapsule(capsule)
    }

    fun updateCapsule(capsule: Capsule){
        capsuleRepository!!.putCapsule(capsule)
    }

    fun deleteCapsule(id:Int){
        capsuleRepository!!.deleteCapsule(id)
    }

}