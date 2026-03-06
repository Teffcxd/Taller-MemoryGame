package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import model.Card

class GameViewModel : ViewModel() {
    var cards = mutableStateListOf<Card>()
        private set
    var moves by mutableStateOf(0)
        private set
    var gameFinished by mutableStateOf(false)
        private set
    private var firstCardIndex: Int? = null
    private var SecondCardIndex: Int? = null
    private var blockInput = false

    init{
        startGame()
    }
    fun startGame(){ // funcion para mezclar
        val numbers = (1..8).toList()
        val shuffled = (numbers+numbers).shuffled()
        cards.clear()

        shuffled.forEach {
            cards.add(Card(it))
        }
        moves=0
        gameFinished=false
        firstCardIndex=null
        SecondCardIndex=null
    }
    fun flipCard(index: Int) {

        if (blockInput) return

        val card = cards[index]

        if (card.isFaceUp || card.isMatched) return

        card.isFaceUp = true

        if (firstCardIndex == null) {

            firstCardIndex = index

        } else if (SecondCardIndex == null) {

            SecondCardIndex = index
            moves++

            checkMatch() //crear la funcion para ver si hacen match
        }
    }
    fun resetGame(){ //funcion para reiniciar juego
        startGame()
    }
}