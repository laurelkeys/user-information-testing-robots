package com.laurelkeys.userinformationtestingrobots.userinformation

import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation

/**
 * Created by Tiago in July 2016.
 */
interface UserInformationMVP {

    interface Model {
        fun isValidName(name: String): Boolean

        fun isValidPhoneNumber(phoneNumber: String?): Boolean

        fun isValidEmail(email: String): Boolean

        fun isValidAge(age: String): Boolean
    }

    interface View {
        fun displaySuccess()

        fun displayFailure()
    }

    interface Presenter {
        fun onSendClick(userInformation: UserInformation)
    }
}
