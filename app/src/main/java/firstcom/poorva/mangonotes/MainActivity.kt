package firstcom.poorva.mangonotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import firstcom.poorva.mangonotes.R.id

class MainActivity : AppCompatActivity() {

    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var btnAdd: ImageButton
    private lateinit var noteList: ArrayList<NotesModule>
    private lateinit var dbref: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRecyclerView = findViewById(R.id.recyclerView)
        notesRecyclerView.layoutManager = LinearLayoutManager(this)
        notesRecyclerView.setHasFixedSize(true)

        noteList= arrayListOf<NotesModule>()
        getNoteData()
        btnAdd = findViewById<ImageButton>(R.id.btnAdd)
        btnAdd.setOnClickListener(){
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    private fun getNoteData() {
        dbref = FirebaseDatabase.getInstance().getReference("Notes")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                noteList.clear()
                if (snapshot.exists()){
                    for (notesnap in snapshot.children){
                        val noteData = notesnap.getValue(NotesModule::class.java)
                        noteList.add(noteData!!)
                    }
                    val nAdapter = NoteAdapter(noteList)
                    notesRecyclerView.adapter = nAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}