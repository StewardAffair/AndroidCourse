package ua.od.okkt.androidcourse

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.room.Room
import ua.od.okkt.androidcourse.ui.archsample.AppDatabase
import ua.od.okkt.androidcourse.ui.archsample.BadScreen
import ua.od.okkt.androidcourse.ui.archsample.Good
import ua.od.okkt.androidcourse.ui.archsample.NoteRepository
import ua.od.okkt.androidcourse.ui.archsample.NoteViewModel
import ua.od.okkt.androidcourse.ui.theme.AndroidCourseTheme

class MainActivity : ComponentActivity() {

    val db by lazy {
        Room.databaseBuilder(
            this.applicationContext,
            AppDatabase::class.java, "notes-db"
        ).allowMainThreadQueries().build()
    }

    val noteDao by lazy {  db.noteDao() }

    val repo by lazy {
        NoteRepository(noteDao)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCourseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BadScreen(applicationContext)
//                    Good(
//                        modifier = Modifier.padding(innerPadding),
//                        viewModel = NoteViewModel(repo)
//                    )
                }
            }
        }
    }
}