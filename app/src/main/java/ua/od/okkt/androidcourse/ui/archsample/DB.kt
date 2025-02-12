package ua.od.okkt.androidcourse.ui.archsample

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String
)

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * FROM Note ORDER BY id DESC")
    fun getAllNotesFlow(): Flow<List<Note>>

    @Query("SELECT * FROM Note ORDER BY id DESC")
    fun getAllNotes(): List<Note>
}

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}