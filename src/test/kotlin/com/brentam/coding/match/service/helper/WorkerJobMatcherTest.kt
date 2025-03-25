package com.brentam.coding.match.service.helper

import com.brentam.coding.match.model.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WorkerJobMatcherTest {

    private val worker: Worker = Worker(
        rating = 5,
        isActive = true,
        certificates = setOf("CertY"),
        skills = setOf("JobX"),
        jobSearchAddress = JobSearchAddress(unit = "km", maxJobDistance = 200, longitude = 1.0, latitude = 1.0),
        transportation = "Car",
        hasDriversLicense = true,
        availability = setOf(Availability(title = "Full-time", dayIndex = 1)),
        phone = "123-456-7890",
        email = "worker@example.com",
        name = Name(last = "Doe", first = "John"),
        age = 30,
        guid = "guidWorker",
        userId = 1
    )

    private val job: Job = Job(
        jobId = 1,
        jobTitle = "JobX",
        driverLicenseRequired = true,
        requiredCertificates = setOf("CertY"),
        location = Location(latitude = 1.0, longitude = 1.0),
        billRate = "20",
        workersRequired = 1,
        startDate = "2023-01-01",
        about = "Job X",
        company = "Company X",
        guid = "guidJob"
    )

    @Test
    fun `test matchesSkills`() {
        assertTrue(matchesSkills(worker, job))
        val wk = worker.copy(skills = setOf("SkillX"))
        assertFalse(matchesSkills(wk, job))
    }

    @Test
    fun `test matchesDriverLicenseRequirement`() {
        assertTrue(matchesDriverLicenseRequirement(worker, job))
        val jb = job.copy(driverLicenseRequired = false)
        assertTrue(matchesDriverLicenseRequirement(worker, jb))
        val wk = worker.copy(hasDriversLicense = false)
        assertFalse(matchesDriverLicenseRequirement(wk, job))
    }

    @Test
    fun `test matchesCertificates`() {
        assertTrue(matchesCertificates(worker, job))
        val wk = worker.copy(certificates = setOf("CertX"))
        assertFalse(matchesCertificates(wk, job))
    }

    @Test
    fun `test matchesGeoLocation`() {
        assertTrue(matchesGeoLocation(worker, job))
        val jb = job.copy(location = Location(latitude = 10.0, longitude = 10.0))
        assertFalse(matchesGeoLocation(worker, jb))
    }

    @Test
    fun `test worker matches job`() {
        assertTrue(worker.isAMatch(job))
    }

}