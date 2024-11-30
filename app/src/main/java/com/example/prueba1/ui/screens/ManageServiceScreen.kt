package com.example.prueba1.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prueba1.R
import com.example.prueba1.data.controller.ServiceViewModel
import com.example.prueba1.data.dao.ServiceDao
import com.example.prueba1.data.database.AppDatabase
import com.example.prueba1.data.database.DatabaseProvider
import com.example.prueba1.data.model.ServiceModel
import com.example.prueba1.data.model.toServiceEntity
import com.example.prueba1.ui.components.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ManageServiceScreen(
    navController : NavController,
    serviceId: String?,
    viewModel: ServiceViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val serviceDao = db.serviceDao()
    val service = remember {mutableStateOf(ServiceModel())}
    val context = LocalContext.current
    var bar_title by remember {mutableStateOf("Create new service")}

    if(serviceId != null && serviceId != "0"){
        bar_title = "Update service"
        viewModel.showService(serviceId.toInt()){ response ->
            if(response.isSuccessful){
                service.value.name = response.body()?.name.toString()
                service.value.username = response.body()?.username.toString()
                service.value.password = response.body()?.password.toString()
                service.value.description = response.body()?.description.toString()
            } else {
                Toast.makeText(
                    context,
                    "Failed to load a service",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Scaffold(
        topBar = {TopBar(bar_title, navController, true)},
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.black))
                .padding(innerPadding)
        ){
            Spacer(modifier = Modifier.padding(0.dp, 5.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = service.value.name,
                maxLines = 1,
                onValueChange = { service.value = service.value.copy(name = it) },
                label = { Text("Service name") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(R.color.purple_500),
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = colorResource(R.color.black),
                    unfocusedTextColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    unfocusedSupportingTextColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedLabelColor = Color.White
                ),
            )

// Campo: Nombre de usuario
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = service.value.username,
                maxLines = 1,
                onValueChange = { service.value = service.value.copy(username = it) },
                label = { Text("Username") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(R.color.purple_500),
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = colorResource(R.color.black),
                    unfocusedTextColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    unfocusedSupportingTextColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedLabelColor = Color.White
                ),
            )

// Campo: Contraseña
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = service.value.password,
                maxLines = 1,
                onValueChange = { service.value = service.value.copy(password = it) },
                label = { Text("Password") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(R.color.purple_500),
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = colorResource(R.color.black),
                    unfocusedTextColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    unfocusedSupportingTextColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedLabelColor = Color.White
                ),
            )

// Campo: Descripción
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = service.value.description,
                maxLines = 1,
                onValueChange = { service.value = service.value.copy(description = it) },
                label = { Text("Description") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = colorResource(R.color.purple_500),
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = colorResource(R.color.black),
                    unfocusedTextColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    unfocusedSupportingTextColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedLabelColor = Color.White
                ),
            )
            FilledTonalButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.purple_500),
                    contentColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                shape = CutCornerShape(4.dp),
                onClick = {
                    val serviceTemp = ServiceModel(
                        name = service.value.name,
                        username = service.value.username,
                        password = service.value.password,
                        description = service.value.description
                    )
                    save(viewModel, context, serviceTemp, serviceId, db)
                }
            ) {
                Text(if (serviceId == "0") "CREATE SERVICE" else "SAVE CHANGES")
            }

            if (serviceId != null && serviceId.toInt() > 0) {
                OutlinedButton(
                    border = BorderStroke(
                        width = 3.dp,
                        color = colorResource(R.color.purple_500)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp),
                    shape = CutCornerShape(4.dp),
                    onClick = {
                        delete(viewModel, context, serviceId, navController, serviceDao)
                    }
                ) {
                    Text("DELETE")
                }
            }
        }
    }
}

fun save(
    viewModel: ServiceViewModel,
    context: Context,
    service: ServiceModel,
    serviceId: String?,
    db: AppDatabase
) {
    if (serviceId == "0") {
        val serviceDao = db.serviceDao()
        var lastID = -1

        try {
            CoroutineScope(Dispatchers.IO).launch {
                lastID = serviceDao.lastId()
                service.id = lastID + 1
                serviceDao.insert(service.toServiceEntity())
            }
            viewModel.createService(service) { response ->
                if (response.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Service created successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Error: $${response.body()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (exception: Exception) {
            println(exception)
        }



//
    } else if (serviceId != null) {
        val serviceDao = db.serviceDao()

        try {
            CoroutineScope(Dispatchers.IO).launch {
                service.id = serviceId.toInt()
                serviceDao.insert(service.toServiceEntity())
            }
            viewModel.updateService(serviceId.toInt(), service) { response ->
                if (response.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Service updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Error: $${response.body()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (exception: Exception) {
            println(exception)
        }
    }
}

fun delete(
    viewModel: ServiceViewModel,
    context: Context,
    serviceId: String?,
    navController: NavController,
    serviceDao: ServiceDao
) {

    if (serviceId != null && serviceId != "0") {
        try{
            CoroutineScope(Dispatchers.IO).launch {
                val service = serviceDao.show(serviceId.toInt())
                serviceDao.delete(service)
            }

            viewModel.deleteService(serviceId.toInt()) { response ->
                if (response.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Service deleted successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(
                        context,
                        "Failed to delete service",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (exception: Exception) {
            println(exception)
        }

    }
}