package com.example.notepart1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="notes")
class Note (@ColumnInfo(name="title")val noteTitle:String,
            @ColumnInfo(name="description")val noteDescription:String,
            @ColumnInfo(name="time")val noteTime:String
            ){
    @PrimaryKey(autoGenerate=true)
    var id=0
}