package ua.od.okkt.androidcourse.ui.archsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteRepository(private val dao: NoteDao) {

    val notes: Flow<List<Note>> = dao.getAllNotesFlow()

    suspend fun insert(text: String) {
        dao.insert(Note(text = text))
    }
}

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    val notes = repository.notes.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addNote(text: String) {
        viewModelScope.launch { repository.insert(text) }
    }
}

@Composable
fun Good(
    modifier: Modifier,
    viewModel: NoteViewModel
) {

    var inputText by remember { mutableStateOf("") }
    val notes by viewModel.notes.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = inputText, onValueChange = { inputText = it })
        Button(onClick = { viewModel.addNote(inputText); inputText = "" }) {
            Text("Зберегти")
        }
        LazyColumn {
            this.items(items = notes) {
                Text(text = it.text)
            }
        }
    }
}