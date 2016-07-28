package com.laurelkeys.userinformationtestingrobots.userinformation.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laurelkeys.userinformationtestingrobots.R;
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation;

/**
 * Created by Tiago in July 2016.
 */
public class FailureFragment extends Fragment {

    private static final String TAG = FailureFragment.class.getSimpleName();

    private UserInformation mUserInformation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.failure_fragment, container, false);

        if (savedInstanceState != null && savedInstanceState.containsKey(UserInformationFragment.USER_INFORMATION)) {
            if (savedInstanceState.get(UserInformationFragment.USER_INFORMATION) != null) {
                mUserInformation = (UserInformation) savedInstanceState.get(UserInformationFragment.USER_INFORMATION);
            }
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayUserInformation();
            }
        });

        return view;
    }

    private void displayUserInformation() {
        UserInformationFragment userInformationFragment = new UserInformationFragment();
        if (mUserInformation != null) {
            Bundle args = new Bundle();
            args.putSerializable(UserInformationFragment.USER_INFORMATION, mUserInformation);
            userInformationFragment.setArguments(args);
        }

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.user_information_container, userInformationFragment)
                .commit();
    }
}
