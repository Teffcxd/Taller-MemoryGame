package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
    fun startGame(){
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

}