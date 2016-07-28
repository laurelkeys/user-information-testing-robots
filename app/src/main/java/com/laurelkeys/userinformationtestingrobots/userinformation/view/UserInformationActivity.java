package com.laurelkeys.userinformationtestingrobots.userinformation.view;

import android.os.Bundle;

import com.laurelkeys.userinformationtestingrobots.R;
import com.laurelkeys.userinformationtestingrobots.base.view.BaseActivity;

public class UserInformationActivity extends BaseActivity {

    private static final String TAG = UserInformationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information_activity);
        attachFragmentToScreen(new UserInformationFragment(), R.id.user_information_container);
    }
}
