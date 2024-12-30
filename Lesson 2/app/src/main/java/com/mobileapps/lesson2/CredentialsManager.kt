package com.mobileapps.lesson2

object CredentialsManager {
    private val accounts = mutableMapOf<String, String>()

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = "^[A-Za-z].*@[A-Za-z]+\\.[A-Za-z]+$"
        return email.matches(Regex(emailRegex))
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun areCredentialsValid(email: String, password: String): Boolean {
        return accounts[email] == password
    }

    fun register(email: String, password: String): Boolean {
        return if (accounts.containsKey(email)) {
            false
        } else {
            accounts[email] = password
            true
        }
    }
}
