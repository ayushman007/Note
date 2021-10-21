package com.example.notepart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
    lateinit var notesRecycler: RecyclerView
    lateinit var addButton: FloatingActionButton
    lateinit var viewModal: NoteViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRecycler = findViewById(R.id.notesRecycler)
        addButton = findViewById(R.id.addButton)
        notesRecycler.layoutManager=LinearLayoutManager(this)

        val notesRVAdapter=NotesRVAdapter(this,this,this)
        notesRecycler.adapter=notesRVAdapter
        viewModal=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NoteViewModal::class.java)
        viewModal.noteList.observe(this, Observer { list->
            list?.let {
                notesRVAdapter.updateList(it)
            }
        }
        )
        addButton.setOnClickListener {
             val intent= Intent(this@MainActivity, AddEditNote::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteClick(note: Note) {
         viewModal.deleteNote(note)
         Toast.makeText(this,"${note.noteTitle} deleted",Toast.LENGTH_LONG).show()

    }

    override fun onNoteClick(note: Note) {
        val intent= Intent(this@MainActivity,AddEditNote::class.java)
        intent.putExtra("notetype","Edit")
        intent.putExtra("notetitle",note.noteTitle)
        intent.putExtra("notedescp",note.noteDescription)
        intent.putExtra("noteid",note.id)
        startActivity(intent)
        this.finish()

    }
}