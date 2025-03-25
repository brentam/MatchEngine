package com.brentam.coding.match.model

data class Job(
    val driverLicenseRequired: Boolean,
    val requiredCertificates: Set<String>,
    val location: Location,
    val billRate: String,
    val workersRequired: Int,
    val startDate: String,
    val about: String,
    val jobTitle: String,
    val company: String,
    val guid: String,
    val jobId: Int
)

data class Location(
    val longitude: Double,
    val latitude: Double
)