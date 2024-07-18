package com.esmasen.autopark

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Screen2(navController: NavHostController) {
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
                onClick = { navController.navigate("screen5")  },
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(Icons.Filled.Call, "Help Icon")
            }
            IconButton(
                onClick = { navController.navigate("screen4")  },
                modifier = Modifier.padding(end = 16.dp, top = 16.dp)
            ) {
                Icon(Icons.Default.Settings, "Settings Icon")
            }

        }
        Text(
            text = "Info",
            style = TextStyle(fontSize = 35.sp),
            modifier = Modifier.padding(30.dp)
        )



        Text(
            text = "The areas marked in red in the parking area are marked as full.",
            style = TextStyle(fontSize = 25.sp),
            modifier = Modifier.padding(16.dp)

        )


        Text(
            text = "The fee will be calculated based on the time difference between exit time and entry time",
            style = TextStyle(fontSize = 25.sp),
            modifier = Modifier.padding(16.dp)

        )

        Text(
            text = "Fee schedule: \n0-1-> h car: 5 $  motocycle:2 $.\n2-4 h-> car: 6  $  motocycle: 3 $.\n4-8 h-> car:  7 $  motocycle: 3.5 $.\n8-12 h-> car: 8 $  motocycle: 4 $.\n 12+ h-> car: 10 $  motocycle:8 $.",

            style = TextStyle(fontSize = 25.sp),
            modifier = Modifier.padding(16.dp)


        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("screen6") }) {
            Text("Got it")
        }
    }
}
@Composable
fun Icon(icon: ImageVector, contentDescription: String) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        tint = Color.Black // veya istediğiniz renk
    )
}



