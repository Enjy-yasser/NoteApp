package com.example.noteapp.data

//import android.content.Context
import android.content.Context
import androidx.room.Database
import androidx.room.Room
//import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.domain.models.Note

/*
3ndna awal haga el schema ..> refers how room database structured (Tables, columns , data type ,...)
ay Modifications fy schema lazem n update version
exportSchema ..> Da ykhly room t export json file of database schema used dayman tracking w el validation
 */

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
//provide access l DAO y interact with table
//}
    /*
    Fy HILTVIEW MODEL ...
    The manual singleton implementation in NoteDatabase
    (using @Volatile, synchronized, etc.) is unnecessary
     3LSHAN Hilt by manage lifecycle and singleton scope of NoteDatabase.
      By using Hilt's @Provides and @Singleton annotations in the NotesModule,
       Hilt ensures there is only one instance of the database created for the entire application.
     */
    // One Instance of NoteDatabase created across app
//    companion object {
//    @Volatile
//    //values of volatile never cached and all read and write are to and from main memory
//    //private variable hold database instance by start b null w by initialize ma3 getDatabase
//    private var Instance: NoteDatabase? = null
//
//    fun getDatabase(context: Context): NoteDatabase {
//
//        // check law instance null y create new instance
//        return Instance ?: synchronized(this) {
//            Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
//                .build()
//                .also { Instance = it }
//        }
//    }
//}}


//    }
//    }

//}