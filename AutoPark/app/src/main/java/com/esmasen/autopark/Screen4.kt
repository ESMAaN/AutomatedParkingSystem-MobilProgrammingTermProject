package com.esmasen.autopark

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Screen4(navController: NavHostController) {
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
                onClick = { navController.navigate("screen2") },
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, "Help Icon")
            }
            Spacer(modifier = Modifier.height(16.dp))




        }
        SettingsScreen()


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

        SettingsScreen()

}
@Composable
fun SettingsScreen() {
    var selectedLanguage by remember{ mutableStateOf("English") }
    val languageOptions = listOf("English", "Spanish", "French")

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "SETTINGS",
            style = TextStyle(fontSize = 35.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Language",
            style = TextStyle(fontSize = 20.sp)
        )

        languageOptions.forEach { language ->
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .selectable(
                        selected = (selectedLanguage == language),
                        onClick = {
                            selectedLanguage = language
                        }
                    )
            ) {
                RadioButton(
                    selected = (selectedLanguage == language),
                    onClick = {
                        selectedLanguage = language
                    }
                )
                Text(
                    text = language,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}



