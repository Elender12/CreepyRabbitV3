package com.ecirstea.creepyrabbit.utils

import java.util.regex.Pattern

object RegistrationUtil {

    private val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    private fun isValidString(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

    fun validateRegistrationInput(
        name: String,
        email: String,
        username: String,
        password: String
    ): Boolean {
        if(username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()){
            return false
        }
        if(password.count { it.isDigit() } < 2){
            return false
        }
        if (!isValidString(email)){
            return false
        }

        return true
    }
}