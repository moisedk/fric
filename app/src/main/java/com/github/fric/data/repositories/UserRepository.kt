package com.github.fric.data.repositories

// Handle user sign-up, login, and (for later) preferences updates.
// Interact with UserDao for local database operations and Firebase for authentication.
interface UserRepository {
    fun login()
    fun signUp()
    fun updatePreferences()
}

