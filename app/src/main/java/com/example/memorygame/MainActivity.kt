package com.example.memorygame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import view.GameScreen
import com.example.memorygame.ui.theme.MemoryGameTheme
import viewmodel.GameViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import view.StartScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MemoryGameTheme {

                val gameViewModel: GameViewModel = viewModel()

                val startGame = remember { mutableStateOf(false) }

                if (startGame.value) {

                    GameScreen(gameViewModel)

                } else {

                    StartScreen {
                        startGame.value = true
                    }

                }

            }
        }
    }
}
