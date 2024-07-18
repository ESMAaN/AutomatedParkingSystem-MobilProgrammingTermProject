package com.esmasen.autopark

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AutoparkApiService {
    @GET("/read_data")
    suspend fun getAutoparks(@Query("town") town: String): AutoparkResponse
}

interface AutoparkService {
    @POST("/update_data")
    fun updateParkingSpot(@Body request: UpdateParkingSpotRequest): Call<Void>
}

data class UpdateParkingSpotRequest(val id: Int, val spotNumber: Int, val isOccupied: Boolean)

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:80/"   //write your local host id.//

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AutoparkApiService by lazy {
        retrofit.create(AutoparkApiService::class.java)
    }

    val updateApi: AutoparkService by lazy {
        retrofit.create(AutoparkService::class.java)
    }
}

data class AutoparkResponse(val data: List<Autopark>)
data class Autopark(
    val id: Int,
    val town: String,
    val autoparkName: String,
    val latitude: Double,
    val longitude: Double,
    val occupiedParkingSpot: Int,
    val maxCapacity: Int
)
