package com.example.retodisenoui

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retodisenoui.ui.theme.RetoDisenoUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetoDisenoUITheme {
//                Scaffold(
//                    // topBar -> zona superior de la app (donde suele ir el titulo de appp)
//                    topBar = { TopBarEdited("Firs App Bar") },
//                )
                StructureHome()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Esta funcion es para editar a parte el topBar
fun TopBar(name: String) {
    // CenterAlignedTopAppBar -> sirve para centrar el topBar
    CenterAlignedTopAppBar(
        // Cambia los colores del topBar
        colors = TopAppBarDefaults.topAppBarColors(
            // containerColor -> color de fondo
            containerColor = Color.Black,
            // titleContentColor -> color del texto
            titleContentColor = Color.White,
        ),
        // Configuración del titulo, en este caso CENTRADO
        title = {
            Text(
                name,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun StructureHome() {
   Column (
       modifier = Modifier.fillMaxSize(),
   ) {
       TopBar("Firs App Bar")
       Row (
           modifier = Modifier.fillMaxWidth()
               .background(color = Color.Yellow)
               .border(border = BorderStroke(5.dp, Color.White))
               .weight(0.25f),
           verticalAlignment = Alignment.CenterVertically
       ) {
           Column (
               modifier = Modifier.fillMaxSize()
                   .background(color = Color.Yellow),
               verticalArrangement = Arrangement.Center
           ) {
               Greeting("Capa 1", Modifier.padding(all = 7.dp))
           }
       }
       Row (
           modifier = Modifier.fillMaxWidth()
               .weight(0.5f),
           verticalAlignment = Alignment.CenterVertically
       ) {
           Column(
               modifier = Modifier.weight(0.6f)
                   .background(color = Color.Green)
                   .border(border = BorderStroke(5.dp, Color.White))
                   .padding(all = 7.dp)
                   .fillMaxHeight(),
               verticalArrangement = Arrangement.Center
           ) {
               Greeting("Capa 2", align = TextAlign.Start)
               Greeting("Capa 2", align = TextAlign.Start)
           }
           Column(
               modifier = Modifier.weight(1f)
                   .background(color = Color.Cyan)
                   .border(border = BorderStroke(5.dp, Color.White))
                   .fillMaxHeight(),
               verticalArrangement = Arrangement.Center
           ) {
               Greeting("Capa 3", Modifier.padding(all = 7.dp))
           }

       }
       Row (
           modifier = Modifier.fillMaxWidth()
               .weight(1f)
               .border(border = BorderStroke(5.dp, Color.White)),
       ) {
           Column (
               modifier = Modifier.fillMaxSize()
                   .background(color = Color.Magenta),
               verticalArrangement = Arrangement.Bottom
           ) {
               Greeting("Capa 4", Modifier.padding(all = 7.dp))
           }
       }
   }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, align: TextAlign = TextAlign.Center) {
    Text(
        text = name,
        modifier = modifier.fillMaxWidth(),
        textAlign = align
    )
}

@Preview(
    showBackground = true,
    widthDp = 300,
    heightDp = 300
)
@Composable
fun GreetingPreview() {
    RetoDisenoUITheme {
        StructureHome()
    }
}