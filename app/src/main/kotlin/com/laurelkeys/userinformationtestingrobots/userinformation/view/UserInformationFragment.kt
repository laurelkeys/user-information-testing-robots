package com.laurelkeys.userinformationtestingrobots.userinformation.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.laurelkeys.userinformationtestingrobots.R
import com.laurelkeys.userinformationtestingrobots.extensions.replaceAndCommitTransaction
import com.laurelkeys.userinformationtestingrobots.userinformation.UserInformationContract
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation
import com.laurelkeys.userinformationtestingrobots.userinformation.presenter.UserInformationFragmentPresenter

/**
 * Created by Tiago in July 2016.
 */
class UserInformationFragment : Fragment(), UserInformationContract.View {

    companion object {
        private val TAG = UserInformationFragment::class.simpleName
        val USER_INFORMATION = "userInformation"
    }

    private val presenter = UserInformationFragmentPresenter(this)

    private var firstName: EditText? = null
    private var lastName: EditText? = null
    private var email: EditText? = null
    private var age: EditText? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.user_information_fragment, container, false)

        if (view != null) initialiseInformationFields(view)
        checkForInformationToBeSet(savedInstanceState)

        view?.findViewById(R.id.send_button)?.setOnClickListener { presenter.onSendClick(userFromInformationFields()) }

        return view
    }

    fun initialiseInformationFields(view: View) {
        this.firstName = view.findViewById(R.id.first_name) as EditText
        this.lastName = view.findViewById(R.id.last_name) as EditText
        this.email = view.findViewById(R.id.email) as EditText
        this.age = view.findViewById(R.id.age) as EditText
    }

    private fun checkForInformationToBeSet(bundle: Bundle?) {
        if (bundle != null && bundle.containsKey(USER_INFORMATION)) {
            val userInformation = bundle.get(USER_INFORMATION) as? UserInformation
            if (userInformation != null) setInformationFields(userInformation)
        }
    }

    private fun setInformationFields(userInformation: UserInformation) {
        firstName?.setText(userInformation.firstName)
        lastName?.setText(userInformation.lastName)
        email?.setText(userInformation.email)
        age?.setText(userInformation.age)
    }

    private fun userFromInformationFields(): UserInformation {
        return UserInformation(firstName?.text.toString(), lastName?.text.toString(),email?.text.toString(), age?.text.toString())
    }

    override fun displaySuccess() {
        val successFragment = SuccessFragment()
        val bundle = Bundle()
        bundle.putSerializable(USER_INFORMATION, userFromInformationFields())
        successFragment.arguments = bundle

        fragmentManager.replaceAndCommitTransaction(R.id.user_information_container, successFragment)
    }

    override fun displayFailure() {
        val failureFragment = FailureFragment()
        val bundle = Bundle()
        bundle.putSerializable(USER_INFORMATION, userFromInformationFields())
        failureFragment.arguments = bundle

        fragmentManager.replaceAndCommitTransaction(R.id.user_information_container, failureFragment)
    }
}
