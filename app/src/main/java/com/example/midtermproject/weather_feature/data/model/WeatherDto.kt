package com.example.midtermproject.weather_feature.data.model

import com.squareup.moshi.Json

data class WeatherDto(
    @Json(name = "hourly")
    val hourlyData : WeatherDataDto
)