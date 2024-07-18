package com.esmasen.autopark

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Screen7(
    navController: NavHostController,
    autopark: Autopark
) {
    var spots by remember { mutableStateOf(List(autopark.maxCapacity) { it < autopark.occupiedParkingSpot }) }
    var occupiedCount by remember { mutableStateOf(autopark.occupiedParkingSpot) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selected Location",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Town: ${autopark.town}",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Autopark Name: ${autopark.autoparkName}",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Latitude: ${autopark.latitude}",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Longitude: ${autopark.longitude}",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Occupied Parking Spots: $occupiedCount",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Max Capacity: ${autopark.maxCapacity}",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(8.dp)
        )

        ParkingGrid(spots, onSpotSelected = { index ->
            if (!spots[index]) {
                updateParkingSpot(autopark.id, index, true) { success ->
                    if (success) {
                        spots = spots.mapIndexed { i, occupied ->
                            if (i == index) true else occupied
                        }
                        occupiedCount++
                    }
                }
            }
        })

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back Icon")
            Text("Go Back")
        }
    }
}

@Composable
fun ParkingGrid(spots: List<Boolean>, onSpotSelected: (Int) -> Unit) {
    Column {
        spots.chunked(5).forEachIndexed { rowIndex, row ->
            Row(modifier = Modifier.padding(4.dp)) {
                row.forEachIndexed { columnIndex, isOccupied ->
                    val index = rowIndex * 5 + columnIndex
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(if (isOccupied) Color.Red else Color.Green, CircleShape)
                            .clickable(enabled = !isOccupied) { onSpotSelected(index) }
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

fun updateParkingSpot(autoparkId: Int, spotNumber: Int, isOccupied: Boolean, callback: (Boolean) -> Unit) {
    val service = RetrofitInstance.updateApi
    val request = UpdateParkingSpotRequest(autoparkId, spotNumber, isOccupied)
    Log.d("updateParkingSpot", "Request: $request")

    val call = service.updateParkingSpot(request)
    call.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful) {
                Log.d("Screen7", "Parking spot updated successfully")
                callback(true)
            } else {
                Log.e("Screen7", "Failed to update parking spot: ${response.code()}")
                callback(false)
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.e("Screen7", "Error updating parking spot", t)
            callback(false)
        }
    })
}
