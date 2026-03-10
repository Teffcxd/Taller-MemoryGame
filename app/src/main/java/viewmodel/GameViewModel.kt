package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Card

class GameViewModel : ViewModel() {

    var cards = mutableStateListOf<Card>()
        private set

    var moves by mutableStateOf(0)
        private set

    val maxMoves = 15

    var gameFinished by mutableStateOf(false)
        private set

    private var firstCardIndex: Int? = null
    private var SecondCardIndex: Int? = null
    private var blockInput = false

    init {
        startGame()
    }

    fun startGame() {

        val numbers = (1..8).toList()
        val shuffled = (numbers + numbers).shuffled()

        cards.clear()

        shuffled.forEach {
            cards.add(Card(it))
        }

        moves = 0
        gameFinished = false
        firstCardIndex = null
        SecondCardIndex = null
        blockInput = false
    }

    fun flipCard(index: Int) {

        // 🔴 bloquear si terminó el juego o superó intentos
        if (blockInput || gameFinished || moves >= maxMoves) return

        val card = cards[index]

        if (card.isFaceUp || card.isMatched) return

        card.isFaceUp = true

        if (firstCardIndex == null) {

            firstCardIndex = index

        } else if (SecondCardIndex == null) {

            SecondCardIndex = index
            moves++

            checkMatch()

            // 🔴 si llegó al límite y no ganó
            if (moves >= maxMoves && !cards.all { it.isMatched }) {
                gameFinished = true
            }
        }
    }

    private fun checkMatch() {

        val first = cards[firstCardIndex!!]
        val second = cards[SecondCardIndex!!]

        if (first.id == second.id) {

            first.isMatched = true
            second.isMatched = true

            resetTurn()

            if (cards.all { it.isMatched }) {
                gameFinished = true
            }

        } else {

            blockInput = true

            CoroutineScope(Dispatchers.Main).launch {

                delay(1000)

                first.isFaceUp = false
                second.isFaceUp = false

                resetTurn()

                blockInput = false
            }
        }
    }

    private fun resetTurn() {
        firstCardIndex = null
        SecondCardIndex = null
    }

    fun resetGame() {
        startGame()
    }
}