package ua.od.okkt.androidcourse.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ua.od.okkt.androidcourse.ui.archsample.AppDatabase
import ua.od.okkt.androidcourse.ui.archsample.NoteDao
import ua.od.okkt.androidcourse.ui.archsample.NoteRepository
import ua.od.okkt.androidcourse.ui.archsample.NoteViewModel

val appModule = module {
    single { NoteRepository(get()) }

    single<NoteDao> { get<AppDatabase>().noteDao() }

    single {  Room.databaseBuilder(
        androidApplication(),
        AppDatabase::class.java, "notes-db"
    ).allowMainThreadQueries().build() }

    viewModel { NoteViewModel(get()) }
    // "get()" означає, що MyViewModel отримує DataRepository як залежність
}