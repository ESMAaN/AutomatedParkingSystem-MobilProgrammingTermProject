package com.esmasen.autopark

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Screen5(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {  navController.navigate("screen2") },
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, "Help Icon")
            }


        }

        Text(
            text = "HELP CENTER",
            style = TextStyle(fontSize = 30.sp),
            modifier = Modifier.padding(30.dp)
        )

        RectangularOutlinedTextFieldPreview()

        SendButton(onClick = { /* Send button click action */ })

    }
}



@Composable
fun RectangularOutlinedTextField(
    text: String,
    onTextChanged: (String) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(text) }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onTextChanged(it)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        label = { Text("Enter text") },
        shape = RectangleShape,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    )
}

@Preview
@Composable
fun RectangularOutlinedTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    RectangularOutlinedTextField(
        text = text,
        onTextChanged = { newText -> text = newText }
    )
}
@Composable
fun SendButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Text(text = "Send")
    }
}

