package com.brentam.coding.match.exception

class UserNotFoundException(workerId: Int) : RuntimeException("Worker $workerId not found")
