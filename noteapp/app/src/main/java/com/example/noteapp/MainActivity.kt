package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.screen.NoteScreen
import com.example.noteapp.screen.NoteViewModel
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {

                Surface(color = MaterialTheme.colors.background) {
                       val noteViewModel= viewModel<NoteViewModel>()
                       NoteApp(noteViewModel)
                    }
                }
            }
        }
    }


@ExperimentalComposeUiApi
@Composable

fun NoteApp(noteViewModel: NoteViewModel ){
    val notesList = noteViewModel.noteList.collectAsState().value
       NoteScreen(notes = notesList,
        onRemoveNote = {
            noteViewModel.removeNote(it)},
        onAddNote = {
            noteViewModel.addNote(it)})

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {


    }
}