package com.example.adminott.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.adminott.R;


public class AppPreference {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public AppPreference(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(String.valueOf(R.string.s_pre_file),Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
    }
    //Login status
    public void setLoginStatus(boolean status)
    {
        editor.putBoolean(String.valueOf(R.string.s_pre_login_status),status);
        editor.commit();
    }
    public boolean getLoginStatus()
    {
        return sharedPreferences.getBoolean(String.valueOf(R.string.s_pre_login_status),false);
    }
    //Name
    public void setDisplayName(String name)
    {
        editor.putString(String.valueOf(R.string.s_pre_login_name),name);
        editor.commit();
    }
    public String getDisplayName()
    {
        return sharedPreferences.getString(String.valueOf(R.string.s_pre_login_name),"Name");

    }
//Email
    public void setDisplayemail(String email)
    {
        editor.putString(String.valueOf(R.string.s_pre_login_email),email);
        editor.commit();
    }
    public String getDisplayemail()
    {
        return sharedPreferences.getString(String.valueOf(R.string.s_pre_login_email),"Email");

    }
//Date
    public void setDisplaydate(String date)
    {
        editor.putString(String.valueOf(R.string.s_pre_login_date),date);
        editor.commit();
    }

    public void showToast(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }


    public String getDisplaydate() {
        return sharedPreferences.getString(String.valueOf(R.string.s_pre_login_date),"Date");
    }
    }

