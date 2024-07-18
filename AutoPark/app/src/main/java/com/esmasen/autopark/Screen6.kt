package com.esmasen.autopark

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch


@Composable
fun Screen6(navController: NavHostController) {

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
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back Icon")
            }
        }

        Text(
            text = "LOCATION",
            style = TextStyle(fontSize = 40.sp),
            modifier = Modifier.padding(30.dp)
        )

        CountryCityTownSelection(navController)


    }
}



@Composable
fun CountryCityTownSelection(navController: NavHostController) {
    var selectedCountry by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf("") }
    var selectedTown by remember { mutableStateOf("") }
    var autoparks by remember { mutableStateOf(emptyList<Autopark>()) }

    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(40.978853,29.055380), 12f)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CountryDropdown(selectedCountry) { country ->
            selectedCountry = country
            selectedCity = ""
            selectedTown = ""
        }
        CityDropdown(selectedCountry, selectedCity) { city ->
            selectedCity = city
            selectedTown = ""
        }
        TownDropdown(selectedCity, selectedTown) { town ->
            selectedTown = town
        }
        Button(
            onClick = {
                scope.launch {
                    val response = RetrofitInstance.api.getAutoparks(selectedTown)
                    autoparks = response.data
                }
            }
        ) {
            Text("SEARCH")

        }
    }

    Box(modifier = Modifier
        .requiredSize(400.dp, 300.dp)
        .padding(16.dp)
        .offset(y = -200.dp)) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            autoparks.forEach { autopark ->
                val markerColor = when {
                    autopark.occupiedParkingSpot < autopark.maxCapacity / 2 -> BitmapDescriptorFactory.HUE_GREEN
                    autopark.occupiedParkingSpot == autopark.maxCapacity / 2 -> BitmapDescriptorFactory.HUE_YELLOW
                    else -> BitmapDescriptorFactory.HUE_RED
                }
                Marker(
                    state = MarkerState(position = LatLng(autopark.latitude, autopark.longitude)),
                    title = autopark.autoparkName,
                    snippet = "${autopark.occupiedParkingSpot}/${autopark.maxCapacity} occupied",
                    onInfoWindowClick = {
                        val autoparkJson = Gson().toJson(autopark)
                        navController.navigate("Screen7/$autoparkJson")
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(markerColor)
                )
            }
        }
    }
}


@Composable
fun CountryDropdown(selectedCountry: String, onCountrySelected: (String) -> Unit) {
    val countries = listOf("Türkiye", "UK", "US")
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedCountry,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Country") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = { Text(country) },
                    onClick = {
                        onCountrySelected(country)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CityDropdown(selectedCountry: String, selectedCity: String, onCitySelected: (String) -> Unit) {
    val cities = when (selectedCountry) {
        "Türkiye" -> listOf("İstanbul", "Ankara", "İzmir")
        "UK" -> listOf("London", "Bristol", "York")
        "US" -> listOf("Los Angeles", "Dallas", "Las Vegas")
        else -> emptyList()
    }
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            value = selectedCity,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("City") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            cities.forEach { city ->
                DropdownMenuItem(
                    text = { Text(city) },
                    onClick = {
                        onCitySelected(city)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TownDropdown(selectedCity: String, selectedTown: String, onTownSelected: (String) -> Unit) {
    val towns = when (selectedCity) {
        "İstanbul" -> listOf("Kadıköy", "Pendik", "Ataşehir")
        "Ankara" -> listOf("Çankaya", "Mamak", "Etimesgut")
        "İzmir" -> listOf("Ödemiş", "Bergama", "Foça")

        else -> emptyList()
    }
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            value = selectedTown,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Town") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            towns.forEach { town ->
                DropdownMenuItem(
                    text = { Text(town) },
                    onClick = {
                        onTownSelected(town)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Preview
@Composable
fun PreviewCountryCityTownSelection() {
    Screen6(navController = rememberNavController())
}
