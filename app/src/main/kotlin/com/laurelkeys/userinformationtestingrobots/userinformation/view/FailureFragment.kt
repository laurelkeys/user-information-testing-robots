package com.laurelkeys.userinformationtestingrobots.userinformation.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.laurelkeys.userinformationtestingrobots.R
import com.laurelkeys.userinformationtestingrobots.extensions.replaceAndCommitTransaction
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation

/**
 * Created by Tiago in July 2016.
 */
class FailureFragment : Fragment() {

    companion object { private val TAG = FailureFragment::class.simpleName ?: "FailureFragment" }

    private var userInformation: UserInformation? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.failure_fragment, container, false)

        if (savedInstanceState != null && savedInstanceState.containsKey(UserInformationFragment.USER_INFORMATION)) {
            if (savedInstanceState.get(UserInformationFragment.USER_INFORMATION) != null) {
                userInformation = savedInstanceState.get(UserInformationFragment.USER_INFORMATION) as UserInformation
            }
        }

        view?.setOnClickListener { displayUserInformation() }

        return view
    }

    private fun displayUserInformation() {
        val userInformationFragment = UserInformationFragment()
        if (userInformation != null) {
            val args = Bundle()
            args.putSerializable(UserInformationFragment.USER_INFORMATION, userInformation)
            userInformationFragment.arguments = args
        }

        fragmentManager.replaceAndCommitTransaction(R.id.user_information_container, userInformationFragment)
    }
}
