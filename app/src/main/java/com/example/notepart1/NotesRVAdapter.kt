package com.example.notepart1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter (val context: Context,
                      val NoteClickDeleteInterface:NoteClickDeleteInterface,
                      val NoteClickInterface:NoteClickInterface
                      ):RecyclerView.Adapter<NotesRVAdapter.ViewHolder>(){
    private val all_notes= arrayListOf<Note>()
                          inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                              val note_title = itemView.findViewById<TextView>(R.id.note_title)
                              val note_delete= itemView.findViewById<ImageView>(R.id.delete)
                              val note_time = itemView.findViewById<TextView>(R.id.time)
                          }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.note_view,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.note_title.setText(all_notes.get(position).noteTitle)
        holder.note_time.setText("Last updated:" +all_notes.get(position).noteTime)
        holder.note_delete.setOnClickListener { 
            NoteClickDeleteInterface.onDeleteClick(all_notes.get(position))
        }
        holder.itemView.setOnClickListener{
            NoteClickInterface.onNoteClick(all_notes.get(position))
        }
        
    }

    override fun getItemCount(): Int {
        return all_notes.size
    }
    fun updateList(new_list:List<Note>){
        all_notes.clear()
        all_notes.addAll(new_list)
        notifyDataSetChanged()
    }


}

interface NoteClickDeleteInterface{
    fun onDeleteClick(note: Note)
}
interface NoteClickInterface{
    fun onNoteClick(note: Note)
}