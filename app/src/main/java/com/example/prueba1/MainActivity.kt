package com.example.prueba1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prueba1.ui.theme.Prueba1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            //Layouts
           /* Column {
                //Los pone en filas
                Text(text = "First Row")
                Text(text = "Second Row")
                Text(text = "Third Row")

                //Los pone todos seguidos en la misma linea
                Row {
                    Text(text = "TEXT 1")
                    Text(text = "TEXT 2")
                    Text(text = "TEXT 3")
                    Text(text = "TEXT 4")
                }

                //Enpalmatodo y lo puedes poner izquierda dercha etc
                Box {
                    Text(text = "label 1")
                    Text(text = "label 2")
                }

                Greeting(name = "World!")
            }*/
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment= androidx.compose.ui.Alignment.CenterHorizontally
                
                
            ){
                Text(text = "simple text")
                ModifierExample2()
                ModifierExample2()
                ModifierExample3()


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Prueba1Theme {
        Greeting("Caro")
    }
}
@Preview(showBackground = true)
@Composable
fun  ModifierExample2(){
    
    Column (
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable(onClick = { clickAction() })
        
    ){
        Text(text = "hello world")
    }

     
}

fun clickAction() {
    println("Colum clicked")
}

@Preview(showBackground = true)
@Composable
fun  ModifierExample3(){

    Column (
     modifier = Modifier
         .fillMaxHeight()
         .padding(16.dp)
         .background(Color.Blue)
         .border(width = 2.dp, color = Color.Green)
         .width(200.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(text = "item 1")
        Text(text = "item 2")
        Text(text = "item 3")
        Text(text = "item 4")
        Text(text = "item 5")
    }


}
