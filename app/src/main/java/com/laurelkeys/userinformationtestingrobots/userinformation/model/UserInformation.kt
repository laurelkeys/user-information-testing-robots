package com.laurelkeys.userinformationtestingrobots.userinformation.model

import com.laurelkeys.userinformationtestingrobots.userinformation.UserInformationMVP
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation.Regex.*
import java.io.Serializable

/**
 * Created by Tiago in July 2016.
 */
data class UserInformation(var firstName: String, var lastName: String,
                           var phoneNumber: String?, var email: String, var age: String) : UserInformationMVP.Model, Serializable {

    companion object { private val TAG = UserInformation::class.simpleName ?: "UserInformation" }

    // TODO test Kotlin .toRegex()
    enum class Regex(val regex: kotlin.text.Regex) {
        NAME("^[\\p{L} .'-]+$".toRegex()),
        PHONE_NUMBER("^[A-Za-z0-9_.]+@[A-Za-z0-9_.]+\\.[A-Za-z]{2,3}$".toRegex()),
        EMAIL("^[A-Za-z0-9_.]+@[A-Za-z0-9_.]+\\.[A-Za-z]{2,3}$".toRegex()),
        AGE("^[0-9]{1,2}$".toRegex()),
    }

    override fun isValidName(name: String): Boolean {
        return name.matches(NAME.regex)
    }

    override fun isValidPhoneNumber(phoneNumber: String?): Boolean {
        return phoneNumber != null && phoneNumber.matches(PHONE_NUMBER.regex)
    }

    override fun isValidEmail(email: String): Boolean {
        return email.matches(EMAIL.regex)
    }

    override fun isValidAge(age: String): Boolean {
        return age.matches(AGE.regex)
    }
}
