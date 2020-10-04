package com.example.adminott.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.adminott.R;
import com.example.adminott.activities.MainActivity;
import com.example.adminott.model.User;
import com.example.adminott.model.Useradmin;
import com.example.adminott.services.MyInterface;
import com.example.adminott.services.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment {
    MyInterface myInterface;
    EditText orgid,name_input,email_input,phone_input,password_input,conpassword_input;
    Button register_button;
    public  String u_name, u_email, u_phone, u_password,u_confirmpassword,u_organizationId;
    LinearLayout linearlayout;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registration,container,false);
        name_input = view.findViewById(R.id.nameInput);
        email_input = view.findViewById(R.id.emailInput);
        phone_input = view.findViewById(R.id.phoneInput);
        password_input = view.findViewById(R.id.passwordInput);
        orgid=view.findViewById(R.id.organizationIdInput);

        linearlayout = view.findViewById(R.id.linlat);
        conpassword_input = view.findViewById(R.id.conpasswordInput);
        register_button = view.findViewById(R.id.regBtn);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {regiterUser();
            }
        });

        return view;
    }

    private void regiterUser() {
       u_name = name_input.getText().toString().trim();
       u_email = email_input.getText().toString().trim();
        u_phone = phone_input.getText().toString().trim();
        u_password = password_input.getText().toString().trim();
         u_confirmpassword = conpassword_input.getText().toString().trim();
         u_organizationId = orgid.getText().toString().trim();


        if (validinput()) {
            final String name = u_name;
            final String email = u_email;
            final String phone = u_phone;
            final String password = u_password;
            final String confirmpassword = u_confirmpassword;
            final String organizationId = u_organizationId;
            Call<User> userCall = MainActivity.serviceApi.doRegisteration(name,email,phone,password,confirmpassword,organizationId);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call,@NonNull Response<User> response) {
                    if(response.body().getResponse().matches("inserted"))
                    {
                        //Chandan get otp and auth here . If match than change dancing status. At Loging check its doing masti or not .
                        // show_Message("Successfully Registered!!","Welcome "+name);


                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Successfully Registered..Check the email for verification");
                        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
                        View view=layoutInflater.inflate(R.layout.alertdialog,null);
                        builder.setView(view);
                        final EditText otpinput=view.findViewById(R.id.userotp);


                        builder.setPositiveButton("Submit",new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,int id) {

                                String otp=otpinput.getText().toString().trim();

                                Call<User> userCall = MainActivity.serviceApi.doVerify(otp);
                                userCall.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        if(response.body().getResponse().matches("sucess"))
                                        {
                                            Toast.makeText(getContext(), "Successfully Verify OTP", Toast.LENGTH_SHORT).show();

                                            getFragmentManager()
                                                    .beginTransaction()
                                                    .replace(R.id.fragment_container, new LoginFragment())

                                                    .commit();
                                        }
                                        else
                                        {
                                            Toast.makeText(getContext(), "Invalid OTP ", Toast.LENGTH_SHORT).show();
                                            verifyotp();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                                        System.out.println("MyError"+t.getMessage());
                                    }

                                });

                            }


                        });


                        builder.setNegativeButton("Re-try",new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,int id) {
                                verifyotp();

                            }


                        });
                        builder.show();
                        clearText();
                        // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    }else if(response.body().getResponse().matches("exists"))
                    {
                        show_Message("Already registered user!!","Welcome "+name);
                        clearText();
                        //  Toast.makeText(getActivity(), "User already exists!!!!", Toast.LENGTH_SHORT).show();
                    }

                    Log.i("My response",response.body().getResponse());
                }

                @Override
                public void onFailure(@NonNull Call<User> call,@NonNull Throwable t) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    System.out.println("MyError"+t.getMessage());
                }
            });
        }
    }

    private void verifyotp() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Successfully Registered..Check the email for verification");
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.alertdialog,null);
        builder.setView(view);
        final EditText otpinput=view.findViewById(R.id.userotp);


        builder.setPositiveButton("Submit",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

                String otp=otpinput.getText().toString().trim();

                Call<User> userCall = MainActivity.serviceApi.doVerify(otp);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body().getResponse().matches("sucess"))
                        {
                            Toast.makeText(getContext(), "Successfully Verify OTP", Toast.LENGTH_SHORT).show();

                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, new LoginFragment())

                                    .commit();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Invalid OTP ", Toast.LENGTH_SHORT).show();
                            verifyotp();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                        System.out.println("MyError"+t.getMessage());
                    }

                });



            }


        });


        builder.setNegativeButton("Re-try",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {
                verifyotp();

            }


        });
        builder.show();
        clearText();

    }


    private void show_Message(String title, String input)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(input);
        builder.setCancelable(true);
        builder.show();
    }

    private void clearText () {

        name_input.setText("");
        email_input.setText("");
        phone_input.requestFocus();
        phone_input.setText("");
        password_input.setText("");
        conpassword_input.setText("");

    }
    private boolean validinput() {
        if (u_name.isEmpty() || u_email.isEmpty() || u_phone.isEmpty() || u_password.isEmpty()||u_password.length()>6|| u_organizationId.isEmpty()|| !u_confirmpassword.equals(u_password)) {
            showMsg("Error", "Kindly Fill Details Properly");
            return false;
        } else {
            return true;
        }
    }

    private void showMsg(String titel, String message) {
        Snackbar snackbar = Snackbar.make(linearlayout, titel + "\n" + message, Snackbar.LENGTH_SHORT);
        snackbar.setAction("Re-Try", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearText();
            }
        });
        snackbar.setTextColor(Color.WHITE);
        snackbar.setBackgroundTint(Color.TRANSPARENT);
        snackbar.setActionTextColor(Color.BLUE);
        snackbar.show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity= (Activity) context;
        myInterface= (MyInterface) activity;
    }
}
