package view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viewmodel.GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel) {

    val cards = viewModel.cards
    val maxMoves = 15
    val progress = viewModel.moves.toFloat() / maxMoves

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Memory match",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "You have $maxMoves attempts to match all new flavours.",
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Attempts",
                color = Color.White
            )

            Text(
                text = "${viewModel.moves}/$maxMoves",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color(0xFF22C55E),
            trackColor = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(30.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
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

    }
}

@Composable
fun CardItem(
    value: Int,
    isFaceUp: Boolean,
    onClick: () -> Unit
) {

    val cardColor = when (value % 6) {
        0 -> Color(0xFFEF4444)
        1 -> Color(0xFFF97316)
        2 -> Color(0xFFEAB308)
        3 -> Color(0xFF22C55E)
        4 -> Color(0xFF3B82F6)
        else -> Color(0xFFEC4899)
    }

    Box(
        modifier = Modifier
            .size(70.dp)
            .background(
                color = if (isFaceUp) cardColor else Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },

        contentAlignment = Alignment.Center
    ) {

        if (isFaceUp) {

            Text(
                text = value.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        } else {

            Text(
                text = "Ic",
                color = Color.Black
            )

        }

    }
}
