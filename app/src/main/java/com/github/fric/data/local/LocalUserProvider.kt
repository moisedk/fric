package com.github.fric.data.local

import com.github.fric.data.User

fun getUser(id: Int = -1): User {
    // Get user from Firebase or local storage
    return User(1, "Steve", "Michel", "steve.michel@berea.edu", "password123")
}