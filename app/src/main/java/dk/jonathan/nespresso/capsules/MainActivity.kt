package dk.jonathan.nespresso.capsules

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dk.jonathan.nespresso.capsules.activity.EditCapsuleActivity
import dk.jonathan.nespresso.capsules.activity.NewCapsuleActivity
import dk.jonathan.nespresso.capsules.model.Capsule
import dk.jonathan.nespresso.capsules.service.CapsuleService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        //Generate context
        appContext = applicationContext

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        create_new_capsule.setOnClickListener { view ->
            val intent = Intent(this@MainActivity,
                NewCapsuleActivity::class.java)
            startActivityForResult(intent, newCapsuleActivityRequestCode)
        }

        listView = findViewById<ListView>(R.id.capsules_list_view)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, getAllObjects())
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            println("Position: $position, ID: $id")
            println("ErrorObject: ${listView.adapter.getItem(position)}")
            val capsule : Capsule = listView.adapter.getItem(position) as Capsule
            println("ID: $id\ncapsule: $capsule")
            intent = Intent(this@MainActivity, EditCapsuleActivity::class.java)
            intent.putExtra("id", capsule.id)
            startActivityForResult(intent, editCapsuleActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == newCapsuleActivityRequestCode || requestCode == editCapsuleActivityRequestCode) && resultCode == Activity.RESULT_OK) {
            val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, getAllObjects())
            listView.adapter = adapter
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getAllObjects():List<Capsule?> {
        var capsuleService = CapsuleService()
        val capsuleList = capsuleService.getAllCapsules()
        return capsuleList
    }

    companion object {
        lateinit var appContext: Context
        private const val newCapsuleActivityRequestCode = 1
        private const val editCapsuleActivityRequestCode = 2
    }

}
