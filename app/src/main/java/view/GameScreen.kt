package view


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viewmodel.GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel) {

    val cards = viewModel.cards

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text("Movements: ${viewModel.moves}", fontSize = 22.sp)

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(16.dp)
        ) {

            itemsIndexed(cards) { index, card ->

                CardItem(
                    value = card.id,
                    isFaceUp = card.isFaceUp || card.isMatched
                ) {
                    viewModel.flipCard(index)
                }
            }
        }

        Button(onClick = { viewModel.resetGame() }) {
            Text("Reset")
        }

        if (viewModel.gameFinished) {
            Text("You Win!", fontSize = 28.sp)
        }
    }
}

@Composable
fun CardItem(
    value: Int,
    isFaceUp: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(70.dp)
            .background(if (isFaceUp) Color.White else Color.Gray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        if (isFaceUp) {
            Text(value.toString(), fontSize = 24.sp)
        }
    }
}