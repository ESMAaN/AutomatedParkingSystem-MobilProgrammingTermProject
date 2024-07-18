package com.esmasen.autopark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.esmasen.autopark.ui.theme.AutoParkTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoParkTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "screen1") {
                        composable("screen1") { Screen1(navController) }
                        composable("screen2") { Screen2(navController) }
                        composable("screen3") { Screen3(navController) }
                        composable("screen4") { Screen4(navController) }
                        composable("screen5") { Screen5(navController) }
                        composable("screen6") { Screen6(navController) }
                        composable(
                            "Screen7/{autoparkJson}",
                            arguments = listOf(navArgument("autoparkJson") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val autoparkJson = backStackEntry.arguments?.getString("autoparkJson")
                            val autopark = Gson().fromJson(autoparkJson, Autopark::class.java)
                            Screen7(navController = navController, autopark = autopark)
                        }
                        composable(
                            route = "map/{country}/{city}/{town}",
                            arguments = listOf(
                                navArgument("country") { defaultValue = "" },
                                navArgument("city") { defaultValue = "" },
                                navArgument("town") { defaultValue = "" }
                            )
                        ) { backStackEntry ->
                            val country = backStackEntry.arguments?.getString("country") ?: ""
                            val city = backStackEntry.arguments?.getString("city") ?: ""
                            val town = backStackEntry.arguments?.getString("town") ?: ""
                            //MapScreen(country, city, town)
                        }
                    }
                }
            }
        }
    }
}
