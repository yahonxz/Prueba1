package com.example.prueba1.ui.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.ejemplo1.ui.components.PostCard
import com.example.prueba1.R
import com.example.prueba1.data.model.MenuModel
import com.example.prueba1.data.model.PostModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@Composable
fun ComponentsScreen(navController: NavController){
    Column {

        var menuOptions = arrayOf(
            MenuModel(1,"Buttons","Buttons", Icons.Filled.Email),
            MenuModel(1,"Floating Buttons","FloatingButtons", Icons.Filled.AccountBox),
            MenuModel(1,"Chips","Chips", Icons.Filled.Info),
            MenuModel(1,"Progress","Progress", Icons.Filled.Build),
            MenuModel(1,"Sliders","Sliders", Icons.Filled.Settings),
            MenuModel(1,"Switches","Switches", Icons.Filled.Send),
            MenuModel(1,"Badges","Badges", Icons.Filled.ThumbUp),
            MenuModel(1,"Date Picker","DatePickers", Icons.Filled.DateRange),
            MenuModel(1,"Time Picker","TimePickers", Icons.Filled.Info),
            MenuModel(1,"SnackBar","SnackBars", Icons.Filled.AccountBox),
            MenuModel(1,"Alert Dialogs","AlertDialogs", Icons.Filled.Lock),
            MenuModel(1,"Bars","Bars", Icons.Filled.Menu),

            )
        var component by rememberSaveable { mutableStateOf("") }//Es para hacer reactiva la variable commo en vue
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,//Current state of drawer
            //Drawer content
            drawerContent = {
                ModalDrawerSheet {
                    Text("Menu", modifier = Modifier.padding(16.dp))
                    HorizontalDivider()
                    LazyColumn {
                        items(menuOptions){item ->
                            NavigationDrawerItem(
                                icon = { Icon(item.icon, contentDescription = "") },
                                label = { Text(text =  item.title)},
                                selected = false,
                                onClick = {
                                    component = item.option
                                    scope.launch {
                                        drawerState.apply {
                                            close()
                                        }
                                    }
                                }
                            )

                        }
                    }
                    /*

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
                        label = { Text(text = "Floating Buttons") },
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
                    NavigationDrawerItem(
                        label = { Text(text = "Badges") },
                        selected = false,
                        onClick = {
                            component = "Badges"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Date Picker") },
                        selected = false,
                        onClick = {
                            component = "DatePickers"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Time Picker") },
                        selected = false,
                        onClick = {
                            component = "TimePickers"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "SnackBar") },
                        selected = false,
                        onClick = {
                            component = "SnackBars"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Alert Dialogs") },
                        selected = false,
                        onClick = {
                            component = "AlertDialogs"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Bars") },
                        selected = false,
                        onClick = {
                            component = "Bars"
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                    */
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
                    "FloatingButtons" -> {
                        FloatingButtons()
                    }
                    "Chips" -> {
                        Chips()
                    }
                    "Progress" -> {
                        Progress()
                    }
                    "Sliders" -> {
                        Sliders()
                    }
                    "Switches" -> {
                        Switches()
                    }
                    "Badges" -> {
                        Badges()
                    }
                    "DatePickers" -> {
                        DatePickers()
                    }
                    "TimePickers" -> {
                        TimePickers()
                    }
                    "SnackBars" -> {
                        SnackBars()
                    }
                    "AlertDialogs" -> {
                        AlertDialogs()
                    }
                    "Bars" -> {
                        Bars()
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
    ){
        Button(onClick = {}) {//Color primary
            Text("filled")
        }
        FilledTonalButton(onClick = {}) {//Color secondary
            Text("Tonal")
        }
        OutlinedButton(onClick = {}) {
            Text("Outlined")
        }
        ElevatedButton(onClick = {}) {//Color secondary
            Text("Elevated")
        }
        TextButton(onClick = {}) {//Color secondary
            Text("Text")
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
    ){
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Check, contentDescription = "")
        }
        SmallFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Check, contentDescription = "")
        }
        LargeFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Check, contentDescription = "")
        }
        ExtendedFloatingActionButton(onClick = {},
            icon = { Icon(Icons.Filled.Check, contentDescription = "") },
            text = { Text(text = "Extended FAB") })
    }
}


@Composable
fun Chips() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AssistChip(
            onClick = {},
            label = { Text("Assist Chip") },
            leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "",
                modifier = Modifier.size(AssistChipDefaults.IconSize))},
        )
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            selected = selected,
            onClick = {selected=!selected},
            label = { Text("Filter Chip") },
            leadingIcon = if(selected){
                {
                    Icon(
                        Icons.Filled.Check, contentDescription = "",
                        modifier = Modifier.size(AssistChipDefaults.IconSize)
                    )
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
        var sliderPosition by remember { mutableStateOf(50f) }
        Column {
            Slider(
                value = sliderPosition ,
                onValueChange = {sliderPosition = it},
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
        var checked by remember { mutableStateOf(true) }
        Switch(
            checked = checked,
            onCheckedChange = {
                checked= it
            }
        )
        var checked2 by remember { mutableStateOf(true) }
        Switch(
            checked = checked2,
            onCheckedChange = {
                checked2= it
            },
            thumbContent = if (checked2){
                {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            }else{
                null
            }
        )
        var checked3 by remember { mutableStateOf(true) }
        Checkbox(
            checked = checked3,
            onCheckedChange = {checked3 = it}
        )
    }
}

@Composable
fun Badges(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        var itemCount by remember { mutableStateOf(0) }

        BadgedBox(
            badge= {
                if(itemCount>0){
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ){
                        Text(text = "$itemCount")
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Shopping cart"
            )
        }
        Button(
            onClick = {itemCount++}
        ){
            Text(text = "Add item")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickers(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()
        val selectedDate = datePickerState.selectedDateMillis?.let {
            convertMillisToDate(it)
        } ?: ""

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("DOB") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            )

            if (showDatePicker) {
                Popup(
                    onDismissRequest = { showDatePicker = false },
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 64.dp)
                            .shadow(elevation = 4.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                    }
                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long) : String{
    val formatter = SimpleDateFormat("MM/dd/yyyy")
    return formatter.format(Date(millis))
}
@Composable
fun TimePickers(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        DialExample({},{})
        InputExample({}, {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialExample(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimePicker(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(onClick = onConfirm) {
            Text("Confirm selection")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExample(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimeInput(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(onClick = onConfirm) {
            Text("Confirm selection")
        }
    }
}

@Composable
fun SnackBars(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        val snackState = remember { SnackbarHostState() }
        val snackScope = rememberCoroutineScope()

        SnackbarHost(hostState = snackState, Modifier)

        fun launchSnackBar(){
            snackScope.launch { snackState.showSnackbar("the message was sent") }
        }

        Button(::launchSnackBar) {
            Text("Show Snackbar")
        }
    }
}

@Composable
fun AlertDialogs() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var showAlertDialog by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }

        if(showAlertDialog){

            AlertDialog(
                icon = { Icon(Icons.Filled.Warning, contentDescription = "")},
                title = { Text(text = "Confirm deletion") },
                text = { Text(text = "Are you sure you want to delete the file?") },
                onDismissRequest = {},
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Confirm"
                            showAlertDialog = false
                        }
                    ) { Text(text = "Confirm") }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Dismiss"
                            showAlertDialog = false
                        }
                    ) { Text(text = "Dismiss") }
                }
            )
        }

        Text(selectedOption)
        Button(onClick = {showAlertDialog = true}) {
            Text("Show alert dialog")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Bars(){
    Box(modifier =
    Modifier.fillMaxSize()
        .background(Color.DarkGray)) {
        Row(modifier = Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth()
            .background(Color.Black)
            .padding(10.dp,50.dp,10.dp,10.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Icon(Icons.Filled.Menu, contentDescription = "", tint = Color.White)
            Text(
                text = "App Title",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Icon(Icons.Filled.Settings, contentDescription = "", tint = Color.White)
        }
        var post = arrayOf(
            PostModel(1,"title 1","text 1", painterResource(R.drawable.android_logo)),
            PostModel(2,"title 2","text 2", painterResource(R.drawable.android_logo)),
            PostModel(3,"title 3","text 3", painterResource(R.drawable.android_logo)),
            PostModel(4,"title 4","text 4", painterResource(R.drawable.android_logo)),
            PostModel(4,"title 5","text 5", painterResource(R.drawable.android_logo)),
            PostModel(4,"title 6","text 6", painterResource(R.drawable.android_logo)),
            PostModel(4,"title 7","text 7", painterResource(R.drawable.android_logo)),
            PostModel(4,"title 8","text 8", painterResource(R.drawable.android_logo)),
            PostModel(4,"title 9","text 9", painterResource(R.drawable.android_logo)),

            )
        Column(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(10.dp,90.dp,10.dp,50.dp)
            .fillMaxSize()
        ) {
            PostsGrid(post)
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(65.dp)
                .background(Color.Black)
                .padding(2.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Column {
                IconButton(onClick = {}, modifier = Modifier.size(30.dp)) {
                    Icon(
                        Icons.Outlined.Search,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("Search", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, modifier = Modifier.size(30.dp)) {
                    Icon(
                        Icons.Outlined.Menu,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("Men", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, modifier = Modifier.size(30.dp)) {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("Home", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, modifier = Modifier.size(30.dp)) {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("Settings", color = Color.White)
            }
        }
    }
}

@Composable
fun Posts(arrayPosts:Array<PostModel> ){
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)

    ){
        items(arrayPosts){ post ->
            PostCard(post.id, post.title, post.text, post.image)
        }
    }
}

@Composable
fun PostsGrid(arrayPosts: Array<PostModel>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)

    ){
        items(arrayPosts){ post ->
            PostCard(post.id, post.title, post.text, post.image)
        }
    }
}