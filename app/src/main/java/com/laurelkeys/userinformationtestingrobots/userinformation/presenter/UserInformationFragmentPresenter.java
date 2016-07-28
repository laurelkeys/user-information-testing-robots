package com.laurelkeys.userinformationtestingrobots.userinformation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.laurelkeys.userinformationtestingrobots.userinformation.UserInformationMVP;
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation;

/**
 * Created by Tiago in July 2016.
 */
public class UserInformationFragmentPresenter implements UserInformationMVP.Presenter {

    private static final String TAG = UserInformationFragmentPresenter.class.getSimpleName();

    private UserInformationMVP.View mView;

    public UserInformationFragmentPresenter(@NonNull final UserInformationMVP.View view) {
        mView = view;
    }

    @Override
    public void onSendClick(@NonNull UserInformation userInformation) {
        Log.d(TAG, "userInformation: " + userInformation.toString());
        if (isValid(userInformation)) {
            mView.displaySuccess();
        } else {
            mView.displayFailure();
        }
    }

    private boolean isValid(@NonNull UserInformation userInformation) {
        boolean validInformation = userInformation.isValidName(userInformation.firstName)
                && userInformation.isValidName(userInformation.lastName)
                && userInformation.isValidEmail(userInformation.email)
                && userInformation.isValidAge(userInformation.age);

        return userInformation.phoneNumber == null
                ? validInformation
                : validInformation && userInformation.isValidPhoneNumber(userInformation.phoneNumber);
    }
}
