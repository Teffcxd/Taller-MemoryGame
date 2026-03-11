package view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viewmodel.GameViewModel
val emojis = listOf("🐶","🐱","🐭","🐹", "🐰","🦊","🐻","🐼")
@Composable
fun GameScreen(viewModel: GameViewModel) {

    val cards = viewModel.cards

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0F14))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Memory match",
            fontSize = 34.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "You have 20 attempts to match",
            fontSize = 16.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Attempts",
            color = Color.White
        )

        LinearProgressIndicator(
            progress = viewModel.moves.toFloat() / viewModel.maxMoves,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${viewModel.moves}/${viewModel.maxMoves}",
            color = Color.White,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(8.dp)
        ) {

            itemsIndexed(cards) { index, card ->

                CardItem(
                    value = card.id,
                    isFaceUp = card.isFaceUp || card.isMatched,
                    enabled = viewModel.moves < viewModel.maxMoves && !viewModel.gameFinished
                ) {
                    viewModel.flipCard(index)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { viewModel.resetGame() }) {
            Text("Reset Game")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (viewModel.gameFinished && viewModel.moves < viewModel.maxMoves) {
            Text(
                text = "🎉 You Win!",
                fontSize = 28.sp,
                color = Color.Green
            )
        }

        if (viewModel.moves >= viewModel.maxMoves && !viewModel.gameFinished) {
            Text(
                text = "❌ Game Over",
                fontSize = 28.sp,
                color = Color.Red
            )
        }
    }
}

@Composable
fun CardItem(
    value: Int,
    isFaceUp: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {

    val cardColor = when (value) {
        1 -> Color(0xFFE74C3C) // rojo
        2 -> Color(0xFF8E44AD) // morado
        3 -> Color(0xFFF1C40F) // amarillo
        4 -> Color(0xFF27AE60) // verde
        5 -> Color(0xFFD35400) // naranja
        6 -> Color(0xFF16A085) // verde oscuro
        7 -> Color(0xFF2980B9) // azul
        else -> Color(0xFFC0392B)
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(70.dp)
            .clickable(enabled = enabled) { onClick() },
        shape = RoundedCornerShape(12.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isFaceUp) cardColor
                    else Color.White
                ),
            contentAlignment = Alignment.Center
        ) {

            if (isFaceUp) {
                Text(
                    text = emojis[value-1],
                    fontSize = 22.sp,
                    color = Color.White
                )
            } else {
                Text(
                    text = "❓",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}