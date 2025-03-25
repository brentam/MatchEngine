package com.brentam.coding.match.exception

class UserNotFoundException(userId: Int) : RuntimeException("User $userId not found")
