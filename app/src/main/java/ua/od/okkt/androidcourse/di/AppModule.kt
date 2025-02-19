package ua.od.okkt.androidcourse.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ua.od.okkt.androidcourse.ui.archsample.AppDatabase
import ua.od.okkt.androidcourse.ui.archsample.NoteDao
import ua.od.okkt.androidcourse.ui.archsample.NoteRepository
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "notes-db"
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun provideRepository(dao: NoteDao) : NoteRepository {
        return NoteRepository(dao)
    }
}
