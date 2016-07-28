package com.laurelkeys.userinformationtestingrobots.userinformation;

import android.support.annotation.NonNull;

import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation;

/**
 * Created by Tiago in July 2016.
 */
public interface UserInformationMVP {

    interface Model {
        boolean isValidName(String name);

        boolean isValidPhoneNumber(String phoneNumber);

        boolean isValidEmail(String email);

        boolean isValidAge(String age);
    }

    interface View {
        void displaySuccess();

        void displayFailure();
    }

    interface Presenter {
        void onSendClick(@NonNull UserInformation userInformation);
    }
}
