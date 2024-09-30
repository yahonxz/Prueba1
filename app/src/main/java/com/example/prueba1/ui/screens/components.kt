package com.example.prueba1.ui.screens

import android.transition.Slide
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
@Composable
fun ComponentsScreen(navController: NavController){
    Column {
        var component by remember { mutableStateOf("") }//Es para hacer reactiva la variable commo en vue
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerState = drawerState,//Current state of drawer
            //Drawer content
            drawerContent = {
                ModalDrawerSheet {
                    Text("Menu", modifier = Modifier.padding(16.dp))
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = { Text(text = "Content 1") },
                        selected = false,
                        onClick = {
                            component = "Content1"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Content 2") },
                        selected = false,
                        onClick = {
                            component = "Content2"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Buttons") },
                        selected = false,
                        onClick = {
                            component = "Buttons"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "FloatingButtons") },
                        selected = false,
                        onClick = {
                            component = "FloatingButtons"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Chips") },
                        selected = false,
                        onClick = {
                            component = "Chips"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Progress") },
                        selected = false,
                        onClick = {
                            component = "Progress"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Sliders") },
                        selected = false,
                        onClick = {
                            component = "Sliders"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Switches") },
                        selected = false,
                        onClick = {
                            component = "Switches"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                }
            }
        ) {
            Column {
                when(component){
                    "Content1" -> {
                        Content1()
                    }
                    "Content2" -> {
                        content2()
                    }
                    "Buttons" -> {
                        Buttons()
                    }
                    "FloatingButtons"-> {
                        FloatingButtons()
                    }
                    "Chips" ->{
                        Chips()
                    }
                    "Progress" ->{
                        Progress()
                    }
                    "Sliders" ->{
                        Sliders()
                    }
                    "Switches" ->{
                        Switches()
                    }
                }
            }
        }
        Button(onClick = {navController.navigate("home")}) { }
    }
}
@Composable
fun Content1(){
    Text(text = "Content 1")
}
@Composable
fun content2(){
    Text(text = "Content 2")
}

@Composable
fun Buttons(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()

    ) {
        Button(onClick = {}) {
            Text(text = "Filled")
        }
        FilledTonalButton(onClick = {}) {
            Text(text = "Tonal")

        }
        OutlinedButton(onClick = {}) {
            Text(text = "Outlined")
        }
        TextButton (onClick = {}) {
            Text(text = "Text")

        }
    }
}

        @Composable
        fun FloatingButtons(){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()

            ) {
            }


            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
            SmallFloatingActionButton (onClick = {}) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
            LargeFloatingActionButton (onClick = {}) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
            ExtendedFloatingActionButton  (
            onClick = {},
                icon = {Icon(Icons.Filled.Add, contentDescription = "")},
                    text = {Text (text = "Extended FAB")}
            )
            }
        @Composable
        fun Chips(){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()

            ) {
                AssistChip(
                    onClick = {},
                    label = { Text(text = "Assist Chip")},
                    leadingIcon = {
                        Icon(Icons.Filled.AccountBox, contentDescription = "",
                            Modifier.size(AssistChipDefaults.IconSize))
                    }

                )
                var selected by remember { mutableStateOf(value = false) }
                FilterChip(
                    selected = selected,
                    onClick = {selected  =!selected},
                    label = { Text(text = "Filter Chip") },
                    leadingIcon = if (selected){
                        {
                        Icon(Icons.Filled.AccountBox, contentDescription = "",
                        Modifier.size(AssistChipDefaults.IconSize))
                        }

                    }else{
                    null
                    }

                )
                InputChipEmple("Dismiss",{})
            }

        }

@Composable
fun InputChipEmple(text: String,
                   onDismiss: () -> Unit){

    var enabled by remember { mutableStateOf(true) }
    if(!enabled) return

    InputChip(
        label = { Text(text) },
        selected = enabled,
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }

    )
}

@Composable
fun Progress() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()

    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp)
        )

    }
}
@Composable
fun Sliders() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()

    ) {
        var sliderPosition by remember { mutableStateOf(value = 50f) }
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = {sliderPosition = it },
                steps = 10,
                valueRange = 0f..100f
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = sliderPosition.toString()
            )
        }

    }
}
@Composable
fun Switches() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()

    ) {
        var checked by remember { mutableStateOf(value = true) }
        Switch(
            checked =checked,
            onCheckedChange = {
                checked = it
            }
        )
        var checked2 by remember { mutableStateOf(value = true) }
        Switch(
            checked =checked2,
            onCheckedChange = {
                checked = it
            },
            thumbContent = if (checked2){
                {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            }else {
                null
            }
        )
        var checked3 by remember { mutableStateOf(value = true) }
        Checkbox(
            checked = checked3,
            onCheckedChange = {checked3 = it}

        )
    }
}