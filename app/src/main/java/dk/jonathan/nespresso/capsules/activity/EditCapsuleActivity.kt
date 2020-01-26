package dk.jonathan.nespresso.capsules.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import dk.jonathan.nespresso.capsules.R
import dk.jonathan.nespresso.capsules.model.Capsule
import dk.jonathan.nespresso.capsules.repository.CapsuleRepository
import dk.jonathan.nespresso.capsules.service.CapsuleService

class EditCapsuleActivity : AppCompatActivity() {

    private var capsuleService = CapsuleService()

    private lateinit var capsuleBrandView: EditText
    private lateinit var capsuleNameView: EditText
    private lateinit var capsuleIntensityView: EditText
    private lateinit var capsuleRatingView: EditText
    private lateinit var capsuleBarcodeView: EditText
    private lateinit var capsuleColorView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val capsule = capsuleService.getCapsuleById(this.intent.extras?.get("id") as Int)

        setContentView(R.layout.activity_edit_capsule)
        capsuleBrandView = findViewById(R.id.capsule_brand)
        capsuleNameView = findViewById(R.id.capsule_name)
        capsuleIntensityView = findViewById(R.id.capsule_intensity)
        capsuleRatingView = findViewById(R.id.capsule_rating)
        capsuleBarcodeView = findViewById(R.id.capsule_barcode)
        capsuleColorView = findViewById(R.id.capsule_color)

        capsuleBrandView.setText(capsule.brand)
        capsuleNameView.setText(capsule.name)
        capsuleIntensityView.setText(capsule.intensity.toString())
        capsuleRatingView.setText(capsule.rating.toString())
        capsuleBarcodeView.setText(capsule.barcode)
        capsuleColorView.setText(capsule.color)

        val saveButton = findViewById<Button>(R.id.button_save)
        val deleteButton: Button = findViewById<Button>(R.id.button_delete)
        saveButton.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(capsuleBrandView.text) || TextUtils.isEmpty(capsuleNameView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                capsule.brand = capsuleBrandView.text.toString()
                capsule.name = capsuleNameView.text.toString()
                capsule.intensity = capsuleIntensityView.text.toString().toInt()
                capsule.rating = capsuleRatingView.text.toString().toInt()
                capsule.barcode = capsuleBarcodeView.text.toString()
                capsule.color = capsuleColorView.text.toString()
                capsuleService.updateCapsule(capsule)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        deleteButton.setOnClickListener {
            val replyIntent = Intent()
            capsuleService.deleteCapsule(capsule.id)
            setResult(Activity.RESULT_OK,replyIntent)
            finish()
        }
    }
}
