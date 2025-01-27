package com.example.noteapp.daggerHilt

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.NoteDao
import com.example.noteapp.data.NoteDatabase
import com.example.noteapp.domain.repository.NoteRepository
import com.example.noteapp.domain.repository.OfflineNoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // 3la mostwa el app kolo
object NotesModule {
    // create singleton instance of notedatabase

    @Provides
    @Singleton
    fun  provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase{
     return Room.databaseBuilder(
         context.applicationContext,
         NoteDatabase::class.java,
         "note_database"
     ).build()
    }
    // DAO ...> call notedao from database instance to retrive DAO

    @Provides
    @Singleton
    fun  provideNoteDao(noteDatabase: NoteDatabase): NoteDao{
        return noteDatabase.noteDao()
        }

    /*
    @Binds ..>Specifiy en NoteRepositoryImpl should be used when interface noteRepository injected
    @Provides mesh hnstkhdmha bnstkhdmha bas law 3yzen n create new instance
    @Binds..>Hn2olo estkkhdm class da ka2no interface
     */

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule{
        @Binds //maps interface to specific implementation
        @Singleton
        abstract fun bindNoteRepository (noteRepositoryImpl: OfflineNoteRepository) : NoteRepository
    }

}
