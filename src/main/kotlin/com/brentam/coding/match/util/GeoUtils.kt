package com.brentam.coding.match.util

import com.brentam.coding.match.model.Job
import com.brentam.coding.match.model.Worker
import org.locationtech.spatial4j.distance.DistanceUtils
import org.locationtech.spatial4j.context.SpatialContext
import org.locationtech.spatial4j.shape.Point

fun calculateDistanceInKm(worker: Worker, job: Job): Double {
    val ctx = SpatialContext.GEO
    val workerLat = worker.jobSearchAddress.latitude
    val workerLon = worker.jobSearchAddress.longitude
    val jobLat = job.location.latitude
    val jobLon = job.location.longitude

    val workerPoint: Point = ctx.shapeFactory.pointXY(workerLon, workerLat)
    val jobPoint: Point = ctx.shapeFactory.pointXY(jobLon, jobLat)
    return ctx.distCalc.distance(workerPoint, jobPoint) * DistanceUtils.DEG_TO_KM
}

fun isJobWithinBounds(worker: Worker, job: Job): Boolean {
    val maxDistance = worker.jobSearchAddress.maxJobDistance.toDouble()
    val unit = worker.jobSearchAddress.unit

    val distanceInKm = calculateDistanceInKm(worker, job)

    val maxDistanceInKm = when (unit) {
        "km", "kilometers" -> maxDistance
        "miles", "mi" -> maxDistance * 1.60934
        else -> maxDistance // Assuming the distance is already in kilometers if the unit is no specified
    }

    return distanceInKm <= maxDistanceInKm
}
