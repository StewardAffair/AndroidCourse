package ua.od.okkt.androidcourse.ui.archsample

import android.content.Context
import android.provider.Settings.Global
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun BadScreen(context: Context) {
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "notes-db-bad"
    ).allowMainThreadQueries().build()
    val noteDao = db.noteDao()

    var inputText by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(Unit) {
        notes = noteDao.getAllNotes().map { it.text }
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        TextField(value = inputText, onValueChange = { inputText = it })
        Button(onClick = {
            GlobalScope.launch {  noteDao.insert(Note(text = inputText)) }
            notes = noteDao.getAllNotes().map { it.text }
            inputText = ""
        }) {
            Text("Зберегти")
        }
        LazyColumn {
            items(notes) {
                note -> Text(note)
            }
        }
    }
}