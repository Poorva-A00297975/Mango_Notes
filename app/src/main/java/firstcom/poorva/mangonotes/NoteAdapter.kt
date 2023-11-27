package firstcom.poorva.mangonotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val notelist:ArrayList<NotesModule>):RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notelist[position]
        holder.tvTitle.text = note.addcat
        holder.tvNote.text = note.addnotes
    }

    override fun getItemCount(): Int {
        return notelist.size
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val tvNote:TextView = itemView.findViewById(R.id.txtNote)
    }
}