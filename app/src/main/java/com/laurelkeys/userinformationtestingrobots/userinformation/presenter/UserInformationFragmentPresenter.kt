package com.laurelkeys.userinformationtestingrobots.userinformation.presenter

import android.util.Log

import com.laurelkeys.userinformationtestingrobots.userinformation.UserInformationMVP
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation

/**
 * Created by Tiago in July 2016.
 */
class UserInformationFragmentPresenter(private val mView: UserInformationMVP.View) : UserInformationMVP.Presenter {

    companion object { private val TAG = UserInformationFragmentPresenter::class.simpleName ?: "UserInformationFragmentPresenter" }

    override fun onSendClick(userInformation: UserInformation) {
        Log.d(TAG, "userInformation: ${userInformation.toString()}")
        if (isValid(userInformation)) {
            mView.displaySuccess()
        } else {
            mView.displayFailure()
        }
    }

    private fun isValid(userInformation: UserInformation): Boolean {
        val validInformation = userInformation.isValidName(userInformation.firstName)
                && userInformation.isValidName(userInformation.lastName)
                && userInformation.isValidEmail(userInformation.email)
                && userInformation.isValidAge(userInformation.age)

        return when (userInformation.phoneNumber) {
            null -> validInformation
            else -> validInformation && userInformation.isValidPhoneNumber(userInformation.phoneNumber)
        }
    }
}
