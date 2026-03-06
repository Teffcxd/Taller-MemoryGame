package model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
class Card(
    val id: Int
) {
    var isFaceUp by mutableStateOf(false)
    var isMatched by mutableStateOf(false)
}