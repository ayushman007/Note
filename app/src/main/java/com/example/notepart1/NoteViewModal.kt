package com.example.notepart1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notepart1.Note
import com.example.notepart1.NoteDatabase
import com.example.notepart1.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 class NoteViewModal(application:Application):AndroidViewModel(application) {
    val noteList:LiveData<List<Note>>
    val repository: NoteRepository

    init{
        val dao= NoteDatabase.getDatabase(application).getNoteDao()
        repository= NoteRepository(dao)
        noteList=repository.allNotes
    }

    fun deleteNote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }
    fun updateNote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }
    fun addNote(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }




}