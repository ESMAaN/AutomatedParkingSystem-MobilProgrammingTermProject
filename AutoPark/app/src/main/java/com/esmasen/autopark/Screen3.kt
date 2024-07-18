package com.esmasen.autopark

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Screen3(navController: NavHostController) {
    // Kullanıcı girişleri için state'leri oluştur
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val dobState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create New Account",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()

        )

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dobState.value,
            onValueChange = { dobState.value = it },
            label = { Text("Date of Birth") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )



        Button(
            onClick = {
                val name = nameState.value
                val email = emailState.value
                val dob = dobState.value
                val password = passwordState.value

                // Burada kullanıcı bilgilerini işle (örneğin kaydet, gönder)
                // saveUser(name, email, dob, password)
                navController.navigate("screen2")
            }, modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        ) {
            Text("Register")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Geri ok ikonu


        }
    }
}





