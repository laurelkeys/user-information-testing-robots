package com.laurelkeys.userinformationtestingrobots.userinformation.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.laurelkeys.userinformationtestingrobots.userinformation.UserInformationMVP;
import com.laurelkeys.userinformationtestingrobots.utils.Utils;

import java.io.Serializable;

/**
 * Created by Tiago in July 2016.
 */
public class UserInformation implements UserInformationMVP.Model, Serializable {

    private static final String TAG = UserInformation.class.getSimpleName();

    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public String age;

    public UserInformation(@NonNull String firstName, @NonNull String lastName,
                           @Nullable String phoneNumber, @NonNull String email, @NonNull String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }


    @Override
    public boolean isValidName(@NonNull String name) {
        return name.matches(Utils.Regex.NAME);
    }

    @Override
    public boolean isValidPhoneNumber(@Nullable String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches(Utils.Regex.PHONE_NUMBER);
    }

    @Override
    public boolean isValidEmail(@NonNull String email) {
        return email.matches(Utils.Regex.EMAIL);
    }

    @Override
    public boolean isValidAge(@NonNull String age) {
        return age.matches(Utils.Regex.AGE);
    }


    @Override
    public String toString() {
        return "[firstName: " + firstName
                + ", lastName: " + lastName
                + ", phoneNumber: " + (phoneNumber == null ? "null" : phoneNumber)
                + ", email: " + email
                + ", age: " + age
                + "]";
    }
}
