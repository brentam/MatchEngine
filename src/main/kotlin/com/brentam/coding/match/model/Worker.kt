package com.brentam.coding.match.model

data class Worker(
    val rating: Int,
    val isActive: Boolean,
    val certificates: Set<String>,
    val skills: Set<String>,
    val jobSearchAddress: JobSearchAddress,
    val transportation: String,
    val hasDriversLicense: Boolean,
    val availability: Set<Availability>,
    val phone: String,
    val email: String,
    val name: Name,
    val age: Int,
    val guid: String,
    val userId: Int
)

data class JobSearchAddress(
    val unit: String,
    val maxJobDistance: Int,
    val longitude: Double,
    val latitude: Double
)

data class Availability(
    val title: String,
    val dayIndex: Int
)

data class Name(
    val last: String,
    val first: String
)