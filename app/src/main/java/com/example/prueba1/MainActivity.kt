package com.example.prueba1

import android.graphics.Picture
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.prueba1.ui.theme.Prueba1Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba1.ui.biometrics.BiometricsScreen
import com.example.prueba1.ui.camera.CameraScreen
import com.example.prueba1.ui.contacts.ContactScreen
import com.example.prueba1.ui.screens.ComponentsScreen
import com.example.prueba1.ui.screens.HomeScreen
import com.example.prueba1.ui.screens.LoginScreen
import com.example.prueba1.ui.screens.MenuScreen
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.prueba1.ui.background.CustomWorker
import com.example.prueba1.ui.location.viewModel.SearchViewModel
import com.example.prueba1.ui.location.views.HomeView
import com.example.prueba1.ui.location.views.MapsSearchView
import com.example.prueba1.ui.network.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.prueba1.ui.screens.ManageServiceScreen
import java.time.Duration
import java.util.concurrent.TimeUnit

//import androidx.navigation.compose.NavHostController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Internet
    // Inicializamos los objetos que vamos a usar para el monitoreo de la red
    private lateinit var wifiManager: WifiManager  // Para gestionar el Wi-Fi
    private lateinit var connectivityManager: ConnectivityManager  // Para gestionar las conexiones de red
    private lateinit var networkMonitor: NetworkMonitor  // Clase que monitorea el estado de la red
    //--------------------------------------------

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //WorkManager
        //------------------------------------------
        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>()
            .setInitialDelay(Duration.ofSeconds(10))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(15)
            )
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        //By adding this, message "Hello from worker!" should be seen from LogCat
        //Internet
        // Obtenemos los servicios necesarios para controlar Wi-Fi y la conectividad de red
        wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        // Creamos una instancia de NetworkMonitor, pasando los servicios y la actividad actual
        networkMonitor = NetworkMonitor(wifiManager, connectivityManager, this)
        //Maps
        //Instancia del ViewModel
        val viewModel: SearchViewModel by viewModels()
        setContent {
            ComposeMultiScreenApp(searchVM = viewModel,this,networkMonitor)
            /*
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ){
                Text(text = "simple text")
                ModifierExample()
                ModifierExample2()
                ModifierExample3()
            }
            //Layouts
            /*Column {
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
            customText()
            picture()
            content1()
            */
        }
    }
    //Internet
// Función para solicitar permisos si no han sido concedidos


    fun requestPermissionsIfNeeded() {
        val permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,  // Permiso para la ubicación precisa
            Manifest.permission.ACCESS_COARSE_LOCATION  // Permiso para la ubicación aproximada
        ).filter {
            // Verificamos si alguno de los permisos no ha sido concedido
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        // Si falta algún permiso, solicitamos los permisos necesarios
        if (permissions.isNotEmpty()) {
            requestPermissionsLauncher.launch(permissions.toTypedArray())
        }
    }

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            // Verificamos si los permisos de ubicación fueron concedidos
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                // Si los permisos son concedidos, mostramos un mensaje
                Toast.makeText(this, "Permisos necesarios concedidos", Toast.LENGTH_SHORT).show()
            } else {
                // Si no se conceden, mostramos un mensaje de error
                Toast.makeText(this, "Permisos necesarios no concedidos", Toast.LENGTH_SHORT).show()
            }
        }

}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview() {
    Prueba1Theme {
        Greeting("Jorge")
    }
}

@Composable
fun ModifierExample(){
    Column(
        modifier = Modifier
            .padding(24.dp)
    ) {
Text(text = "Hello world")
    }
}

@Composable
fun ModifierExample2(){
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable(onClick = {clickAction()})
    ) {
        Text(text = "Hello world")
    }
}

fun clickAction(){
    println("Column Clicked")
}

@Composable
fun ModifierExample3(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color.Cyan)
            .border(width = 2.dp, color = Color.Magenta)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "item 1")
        Text(text = "item 2")
        Text(text = "item 3")
        Text(text = "item 4")
        Text(text = "item 5")
    }

}

@Composable
fun customText(){
    Column {
        Text(
            stringResource(R.string.hello_world_text),
            color = colorResource(R.color.purple_700),
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors = listOf(Color.Cyan, Color.Blue, Color.Red)
        Text(
            stringResource(R.string.hello_world_text),
            style = TextStyle(brush = Brush.linearGradient(colors = gradientColors))
        )
    }
}

@Composable
fun picture(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)

    ){
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(R.drawable.android_logo),
            contentDescription = "Logo Android",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun content1(){
    Card(modifier = Modifier
        .background(Color.LightGray)
        .fillMaxWidth()
        .padding(5.dp)){
        Text(text = "This is a title",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Android Logo",
            contentScale = ContentScale.Crop)
        Text(
            stringResource(R.string.text_card),
            textAlign = TextAlign.Justify,
            lineHeight = 18.sp,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Composable
fun content2() {

    Card(modifier = Modifier
        .background(Color.LightGray)
        .fillMaxWidth()
        .padding(5.dp)){
        Row {
            Image(
                modifier = Modifier
                    .width(60.dp)
                    .height(100.dp)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = "Android Logo",
                contentScale = ContentScale.Crop
            )
            Column {
            Text(
                text = "This is a title",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(7.dp)
            )



                Text(
                    stringResource(R.string.text_card),
                    textAlign = TextAlign.Justify,
                    lineHeight = 18.sp,
                    fontSize = 10.sp,
                    maxLines = 3,
                    modifier = Modifier
                        .padding(7.dp)

                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample1(){
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
            .padding(5.dp)
    ){
        Image(painterResource(R.drawable.android_logo),
            contentDescription = "Android Head Logo",
            contentScale = ContentScale.FillBounds)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 50.dp),
            horizontalArrangement = Arrangement.Center) {

            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = "Icon Account"
            )
            Text( text = "text")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample2(){
    Box(modifier = Modifier
        .background(Color.Magenta)
        .padding(5.dp)
        .size(250.dp)){
        Text(text = "TopStart",
            Modifier.align(Alignment.TopStart))

        Text(text = "TopEnd",
            Modifier.align(Alignment.TopEnd))

        Text(text = "CenterStart",
            Modifier.align(Alignment.CenterStart))

        Text(text = "Center",
            Modifier.align(Alignment.Center))

        Text(text = "CenterEnd",
            Modifier.align(Alignment.CenterEnd))


        Text(text = "BottomStart",
            Modifier.align(Alignment.BottomStart))

        Text(text = "BottomEnd",
            Modifier.align(Alignment.BottomEnd))

    }
}
*/

@Composable
fun ComposeMultiScreenApp(searchVM: SearchViewModel, activity: AppCompatActivity,networkMonitor: NetworkMonitor){
    val navController = rememberNavController()
    Surface(color=Color.White){
        SetupNavGraph(navController=navController,searchVM,activity,networkMonitor) //función propia //crea el grafo recordando el navcontroller donde nos encontramos
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController, searchVM: SearchViewModel, activity: AppCompatActivity, networkMonitor: NetworkMonitor){

    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "menu"){ //índice de pantallas //Usa el nav controller de ahorita y empieza desde el índice definido
        composable("menu"){ MenuScreen(navController) } //Rutas
        composable("home"){ HomeScreen(navController) }
        composable("components"){ ComponentsScreen(navController) }
        composable("login"){ LoginScreen(navController = navController)}

        composable("Camera"){ CameraScreen(context = context,navController)}

        composable("internet"){networkMonitor.NetworkMonitorScreen(navController)}

        // Rutas de contactos

        composable("contacts"){ ContactScreen(navController = navController) }

        //Biometricos
        composable("biometrics"){ BiometricsScreen(navController = navController, activity = activity)}

        // Ruta para `MapsSearchView` que recibe latitud, longitud y dirección como argumentos
        composable("homeMaps"){ HomeView(navController = navController, searchVM = searchVM)}
        composable("MapsSearchView/{lat}/{long}/{address}", arguments = listOf(
            navArgument("lat") { type = NavType.FloatType },
            navArgument("long") { type = NavType.FloatType },
            navArgument("address") { type = NavType.StringType }
        )) {
            // Obtención de los argumentos con valores predeterminados en caso de que falten
            val lat = it.arguments?.getFloat("lat") ?: 0.0
            val long = it.arguments?.getFloat("long") ?: 0.0
            val address = it.arguments?.getString("address") ?: ""
            MapsSearchView(lat.toDouble(), long.toDouble(), address )
        }
        //API SERVICES
        composable("manage-service/{serviceId}"){backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            ManageServiceScreen(navController, serviceId = serviceId)
        }
    }

}