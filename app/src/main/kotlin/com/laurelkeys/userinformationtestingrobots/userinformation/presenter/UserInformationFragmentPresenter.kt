package com.laurelkeys.userinformationtestingrobots.userinformation.presenter

import android.util.Log

import com.laurelkeys.userinformationtestingrobots.userinformation.UserInformationContract
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation

/**
 * Created by Tiago in July 2016.
 */
class UserInformationFragmentPresenter(private val mView: UserInformationContract.View) : UserInformationContract.Presenter {

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
        return userInformation.isValidName(userInformation.firstName)
                && userInformation.isValidName(userInformation.lastName)
                && userInformation.isValidEmail(userInformation.email)
                && userInformation.isValidAge(userInformation.age)
    }
}
