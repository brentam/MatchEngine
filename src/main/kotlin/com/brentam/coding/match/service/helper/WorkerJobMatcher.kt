package com.brentam.coding.match.service.helper

import com.brentam.coding.match.model.Job
import com.brentam.coding.match.model.Worker
import com.brentam.coding.match.util.isJobWithinBounds

/**
 * this function receives a worker and a job and returns a list of predicates
 */
private fun getPredicates(worker: Worker, job: Job): List<() -> Boolean> {
    return listOf(
        { matchesSkills(worker, job) },
        { matchesDriverLicenseRequirement(worker, job) },
        { matchesCertificates(worker, job) },
        { matchesGeoLocation(worker, job) }
    )
}

/**
 * this extension function receives a worker and try to match it with a job
 * by checking all the predicates defined in the getPredicates function
 */
fun Worker.isAMatch(job: Job): Boolean {
    val predicates = getPredicates(this, job)
    return predicates.all { it() }
}

// the functions below didn't need to be declared individually, they
// could be anonymous functions/lambdas in the getPredicates function.
// made them functions to test them individually
fun matchesSkills(worker: Worker, job: Job): Boolean {
    return worker.skills.contains(job.jobTitle)
}

fun matchesDriverLicenseRequirement(worker: Worker, job: Job): Boolean {
    return (job.driverLicenseRequired && worker.hasDriversLicense) || job.driverLicenseRequired.not()
}

fun matchesCertificates(worker: Worker, job: Job): Boolean {
    return worker.certificates.containsAll(job.requiredCertificates)
}

fun matchesGeoLocation(worker: Worker, job: Job): Boolean {
    return isJobWithinBounds(worker, job)
}

