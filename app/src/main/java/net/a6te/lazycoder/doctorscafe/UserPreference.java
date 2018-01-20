package net.a6te.lazycoder.doctorscafe;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mobile App Develop on 4/5/2017.
 */

public class UserPreference {
    public static final String USER_AUTHENTICATION = "user_authentication";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASS = "user_pass";
    public static final String USER_NAME = "user_name";
    public static final String USER_CONFIRM_PASS = "user_confirm_pass";

    private SharedPreferences userPreference;
    private SharedPreferences.Editor editor;
    private Context context;


    public UserPreference(Context context) {
        this.context = context;
        userPreference = context.getSharedPreferences(USER_AUTHENTICATION,Context.MODE_PRIVATE);
        editor = userPreference.edit();
    }

    public void saveUser(String email, String pass,String userName, String confirmPass){
        editor.putString(USER_EMAIL,email);
        editor.putString(USER_PASS,pass);
        editor.putString(USER_CONFIRM_PASS,confirmPass);
        editor.putString(USER_NAME,userName);
        editor.commit();
    }

    public void clearUserData(){
        editor.clear();
        editor.commit();
    }


    public String getUserName(){
        return userPreference.getString(USER_NAME,null);
    }
    public String getUserPassword(){
        return userPreference.getString(USER_PASS,null);
    }


    public static String getUserAuthentication() {
        return USER_AUTHENTICATION;
    }

    public static String getUserEmail() {
        return USER_EMAIL;
    }

    public static String getUserPass() {
        return USER_PASS;
    }

    public static String getUserConfirmPass() {
        return USER_CONFIRM_PASS;
    }
}
