package com.ecirstea.creepyrabbit.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
       val result = RegistrationUtil.validateRegistrationInput("My name","user@email.com","", "password12")

        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput("My name","user@email.com","username", "")

        assertThat(result).isFalse()
    }
    @Test
    fun `less than 2 char password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput("My name","user@email.com","username", "password")

        assertThat(result).isFalse()
    }

    @Test
    fun `invalid email returns false`() {
        val result = RegistrationUtil.validateRegistrationInput("My name","user.mail.com","username", "password12")

        assertThat(result).isFalse()
    }




    @Test
    fun `valid username and correct password returns true`() {
        val result = RegistrationUtil.validateRegistrationInput("My name","user@email.com","username", "password12")

        assertThat(result).isTrue()
    }




}