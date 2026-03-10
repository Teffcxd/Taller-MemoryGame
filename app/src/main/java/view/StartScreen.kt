package view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartScreen(
    onStartClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE1F5FE))
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🧠 Memory Game",
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { onStartClick() }
        ) {
            Text(
                text = "Start Game",
                fontSize = 20.sp
            )
        }
    }
}