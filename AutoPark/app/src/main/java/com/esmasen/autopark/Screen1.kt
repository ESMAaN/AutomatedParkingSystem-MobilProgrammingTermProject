package com.esmasen.autopark

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Screen1(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        rectangle_2()

        Spacer(modifier = Modifier.height(32.dp))
        val nameState = remember { mutableStateOf(TextFieldValue()) }
        val passwordState = remember { mutableStateOf(TextFieldValue()) }
        UserInputField("Name", nameState.value) { nameState.value = it }
        Spacer(modifier = Modifier.height(16.dp))
        UserInputField("Password", passwordState.value) { passwordState.value = it }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Burada kullanıcı adı ve şifreyi alabilirsiniz
                val name = nameState.value.text
                val password = passwordState.value.text
                navController.navigate("screen2") }) {
            Text("login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("screen3") }) {
            Text("do you have an account?")
            Spacer(modifier = Modifier.height(8.dp))
            Text("sign up !")
        }

    }
}

@Composable
fun UserInputField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth()
    )
}
@Composable
fun ForgotPasswordDoNotYouHaveAnAccountSignUp(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Forgot password?",
            style = TextStyle(
                color = Color(0xffcd4ab8),
                fontSize = 15.sp
            )
        )
        Text(
            text = "Do not you have an account?",
            style = TextStyle(
                color = Color(0xFFC93A5B),
                fontSize = 15.sp
            )
        )
        Text(
            text = "Sign up!",
            style = TextStyle(
                color = Color(0xFF2196F3),
                fontSize = 15.sp
            ),
            modifier = Modifier.clickable { onSignUpClick() }
        )
    }
}
@Composable
fun rectangle_2() {
    Image(
        painter = painterResource(id = R.drawable.rectangle_2),
        contentDescription = null, // Opsiyonel, resmin içeriğini açıklar
        modifier = Modifier.size(300.dp),
        contentScale = ContentScale.FillBounds
    )// Resmi ekranın boyutuna uyacak şekilde ölçeklendirir
}
