package firstcom.poorva.mangonotes

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import firstcom.poorva.mangonotes.R.id.btnadded

class MainActivity2 : AppCompatActivity() {

    private lateinit var addcategory: EditText
    private lateinit var add_notes: EditText
    private lateinit var btnadded: Button

    private lateinit var dbref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        addcategory = findViewById(R.id.addcategory)
        add_notes = findViewById(R.id.add_notes)
        btnadded = findViewById(R.id.btnadded)

        dbref = FirebaseDatabase.getInstance().getReference("notes")

        btnadded.setOnClickListener {
            saveNotes()
        }


    }
    private fun saveNotes() {

        val addcat = addcategory.text.toString()
        val addnotes = add_notes.text.toString()

        if (addcat.isEmpty()) {
            addcategory.error = " Please enter the category"
        }
        if (addnotes.isEmpty()) {
            add_notes.error = " Please enter the Notes"
        }

        val notesid = dbref.push().key!!
        val notes = NotesModule(notesid, addcat, addnotes)

        dbref.child(notesid).setValue(notes)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted Successfully", Toast.LENGTH_LONG).show()
                addcategory.text.clear()
                add_notes.text.clear()



            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}