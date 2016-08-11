package com.laurelkeys.userinformationtestingrobots.userinformation.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.laurelkeys.userinformationtestingrobots.R
import com.laurelkeys.userinformationtestingrobots.extensions.attachFragmentToScreen

class UserInformationActivity : AppCompatActivity() {

    companion object { private val TAG = UserInformationActivity::class.simpleName ?: "UserInformationActivity" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_information_activity)
        attachFragmentToScreen(UserInformationFragment(), R.id.user_information_container)
    }
}
