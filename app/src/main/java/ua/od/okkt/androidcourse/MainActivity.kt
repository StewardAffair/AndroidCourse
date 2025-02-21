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
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.od.okkt.androidcourse.ui.archsample.Good
import ua.od.okkt.androidcourse.ui.archsample.NoteViewModel
import ua.od.okkt.androidcourse.ui.theme.AndroidCourseTheme

class MainActivity : ComponentActivity() {

    private val noteViewModel: NoteViewModel by viewModel()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCourseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    BadScreen(applicationContext)
                    Good(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = noteViewModel
                    )
                }
            }
        }
    }
}