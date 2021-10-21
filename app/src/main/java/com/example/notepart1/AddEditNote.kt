package com.example.notepart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNote : AppCompatActivity() {
    lateinit var EditNoteTitle:EditText
    lateinit var EditNoteDescription:EditText
    lateinit var UpdateSaveNote:Button
    lateinit var ViewModal: NoteViewModal
    var noteid=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        EditNoteTitle=findViewById(R.id.EditNoteTitle)
        EditNoteDescription=findViewById(R.id.EditNoteDescription)
        UpdateSaveNote=findViewById(R.id.UpdateSaveNote)
        ViewModal= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModal::class.java)
        val notetype=intent.getStringExtra("notetype")
        if (notetype.equals("Edit")){
            val notetitle=intent.getStringExtra("notetitle")
            val notedescp=intent.getStringExtra("notedescp")
            noteid=intent.getIntExtra("noteid",-1)
            UpdateSaveNote.setText("Update")
            EditNoteTitle.setText(notetitle)
            EditNoteDescription.setText(notedescp)

        }else{
            UpdateSaveNote.setText("Save")
        }
        UpdateSaveNote.setOnClickListener {
            val notetitle=EditNoteTitle.text.toString()
            val notedescp=EditNoteDescription.text.toString()
            if (notetype.equals("Edit")){
                if (notetitle.isNotEmpty() &&  notedescp.isNotEmpty()){
                    val datetime=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=datetime.format(Date())
                    val updateNote=Note(notetitle,notedescp,currentDate)
                    updateNote.id=noteid
                    ViewModal.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated...",Toast.LENGTH_LONG).show()


                }
            }else{
                if (notetitle.isNotEmpty() &&  notedescp.isNotEmpty()){
                    val datetime=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=datetime.format(Date())
                    ViewModal.addNote(Note(notetitle,notedescp,currentDate))
                    Toast.makeText(this,"Note Saved...",Toast.LENGTH_LONG).show()

                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }


    }
}