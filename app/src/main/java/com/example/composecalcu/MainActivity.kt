package com.example.composecalcu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecalcu.ui.theme.ComposeCalcuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalcuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

val buttonList = listOf(
    "C","(",")","/",
    "7","8","9","*",
    "6","5","4","+",
    "3","2","1","-",
    "Ac","0",".","="
)

@Composable
fun Calculator(modifier: Modifier) {

    var input by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
    val columns = if (isLandscape) 5 else 4
    Box(modifier = modifier){
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ){

            Text(
                text = if (input.isEmpty()) "0" else input,
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "resultado",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
            )

            Spacer(modifier = Modifier.height(10.dp))


            LazyVerticalGrid(
                columns = GridCells.Fixed(4),

                ) {
                items(buttonList){btn ->
                    CalculatorButton(btn = btn){
                        when(btn){
                            "C" -> input = ""
                            "AC" -> input = ""
                            "=" -> {

                            }else ->{
                            input += btn
                        }
                        }
                    }
                }
            }



        }
    }
}
@Composable
fun CalculatorButton(btn : String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(4.dp).width(60.dp)) {
        FloatingActionButton(
            onClick = {onClick()},
            modifier = Modifier.size(50.dp)
        ) {
            Text(text = btn,
                fontSize = 20.sp)
        }

    }
}