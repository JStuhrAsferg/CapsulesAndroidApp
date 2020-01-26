package dk.jonathan.nespresso.capsules.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import dk.jonathan.nespresso.capsules.R
import dk.jonathan.nespresso.capsules.model.Capsule
import dk.jonathan.nespresso.capsules.service.CapsuleService

class NewCapsuleActivity : AppCompatActivity() {

    private var capsuleService = CapsuleService()

    private lateinit var capsuleBrandView: EditText
    private lateinit var capsuleNameView: EditText
    private lateinit var capsuleIntensityView: EditText
    private lateinit var capsuleRatingView: EditText
    private lateinit var capsuleBarcodeView: EditText
    private lateinit var capsuleColorView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_capsule)
        capsuleBrandView = findViewById(R.id.capsule_brand)
        capsuleNameView = findViewById(R.id.capsule_name)
        capsuleIntensityView = findViewById(R.id.capsule_intensity)
        capsuleRatingView = findViewById(R.id.capsule_rating)
        capsuleBarcodeView = findViewById(R.id.capsule_barcode)
        capsuleColorView = findViewById(R.id.capsule_color)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(capsuleBrandView.text) || TextUtils.isEmpty(capsuleNameView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val capsule = Capsule()
                capsule.brand = capsuleBrandView.text.toString()
                capsule.name = capsuleNameView.text.toString()
                capsule.intensity = capsuleIntensityView.text.toString().toInt()
                capsule.rating = capsuleRatingView.text.toString().toInt()
                capsule.barcode = capsuleBarcodeView.text.toString()
                capsule.color = capsuleColorView.text.toString()
                capsuleService.createCapsule(capsule)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
