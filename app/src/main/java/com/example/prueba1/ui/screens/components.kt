package com.example.prueba1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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