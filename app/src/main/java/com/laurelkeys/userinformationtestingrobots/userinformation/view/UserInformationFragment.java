package com.laurelkeys.userinformationtestingrobots.userinformation.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.laurelkeys.userinformationtestingrobots.R;
import com.laurelkeys.userinformationtestingrobots.userinformation.UserInformationMVP;
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation;
import com.laurelkeys.userinformationtestingrobots.userinformation.presenter.UserInformationFragmentPresenter;

/**
 * Created by Tiago in July 2016.
 */
public class UserInformationFragment extends Fragment implements UserInformationMVP.View {

    public static final String USER_INFORMATION = "userInformation";
    private static final String TAG = UserInformationFragment.class.getSimpleName();
    private UserInformationMVP.Presenter mPresenter = new UserInformationFragmentPresenter(this);

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mPhoneNumber;
    private EditText mEmail;
    private EditText mAge;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.user_information_fragment, container, false);

        initialiseInformationFields(view);
        checkForInformationToBeSet(savedInstanceState);

        view.findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onSendClick(userFromInformationFields());
            }
        });

        return view;
    }

    public void initialiseInformationFields(View view) {
        this.mFirstName = (EditText) view.findViewById(R.id.first_name);
        this.mLastName = (EditText) view.findViewById(R.id.last_name);
        this.mPhoneNumber = (EditText) view.findViewById(R.id.phone_number);
        this.mEmail = (EditText) view.findViewById(R.id.email);
        this.mAge = (EditText) view.findViewById(R.id.age);
    }

    private void checkForInformationToBeSet(@Nullable Bundle bundle) {
        if (bundle != null && bundle.containsKey(USER_INFORMATION)) {
            UserInformation userInformation = (UserInformation) bundle.get(USER_INFORMATION);
            if (userInformation != null) setInformationFields(userInformation);
        }
    }

    private void setInformationFields(@NonNull UserInformation userInformation) {
        mFirstName.setText(userInformation.firstName);
        mLastName.setText(userInformation.lastName);
        mPhoneNumber.setText(userInformation.phoneNumber == null ? "" : userInformation.phoneNumber);
        mEmail.setText(userInformation.email);
        mAge.setText(userInformation.age);
    }

    private UserInformation userFromInformationFields() {
        String phoneNumber = mPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) phoneNumber = null;

        return new UserInformation(mFirstName.getText().toString(), mLastName.getText().toString(), phoneNumber, mEmail.getText().toString(), mAge.getText().toString());
    }

    @Override
    public void displaySuccess() {
        SuccessFragment successFragment = new SuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_INFORMATION, userFromInformationFields());
        successFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.user_information_container, successFragment)
                .commit();
    }

    @Override
    public void displayFailure() {
        FailureFragment failureFragment = new FailureFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_INFORMATION, userFromInformationFields());
        failureFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.user_information_container, failureFragment)
                .commit();
    }
}
