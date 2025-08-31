package com.example.firsttask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.firsttask.common.urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity
{

    SharedPreferences preferences ;
    SharedPreferences.Editor editor;
    EditText etUsername,etPassword;
    CheckBox cbShowPass;
    Button btn;
    TextView tvNewUser;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editor=preferences.edit();


        if (preferences.getBoolean("isLogin",false))
        {
            Intent i=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(i);
        }



        etUsername=findViewById(R.id.etloginusername);
        etPassword=findViewById(R.id.etloginpassword);
       // cbShowPass=findViewById(R.id.cbshowhidepass);
        btn = findViewById(R.id.btnlogin);
        tvNewUser=findViewById(R.id.tvregisternewuser);

//        cbShowPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//         {
//             @Override
//             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//             {
//                 if(isChecked)
//                 {
//                     etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                 }
//                 else
//                 {
//                     etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                 }
//             }
//        });





        btn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                        if(etUsername.getText().toString().isEmpty())
                        {
                            etUsername.setError("Please Enter The Username");
                        }
                        else if(etPassword.getText().toString().isEmpty())
                        {
                            etPassword.setError("Please Enter Password");
                        }
                        else if (etPassword.getText().toString().length()<8)
                        {
                            etPassword.setError("Password must be greater than 8");
                        }
                        else if (!etPassword.getText().toString().matches("(.*[A-Z].*)")||!etPassword.getText().toString().matches("(.*[A-Z].*)")||!etPassword.getText().toString().matches("(.*[!@#$%^&*].*)"))
                        {
                            etPassword.setError("Password must contain Uppercase(A-Z),lowercase(a-z),number(0-9),special symbols(!@#$%^&*)");
                        }
                        else if (etUsername.getText().toString().length()<8)
                        {
                            etUsername.setError("Username Must be greater than 8");
                        }
                        else
                        {
                            progressDialog = new ProgressDialog(LoginActivity.this);
                            progressDialog.setTitle("Please wait");
                            progressDialog.setMessage("Login under process");
                            progressDialog.show();
                            userLogin();
                            editor.putString("username",etUsername.getText().toString()).commit();
                        }

                    }

                });

        tvNewUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(LoginActivity.this,NewUserRegistrationActivity.class);
                startActivity(i);
            }
        });
    }

    private void userLogin() {
        //Client and Server Communication over network data transfer or manipulate
        AsyncHttpClient client = new AsyncHttpClient(); //Client and Server Communication
        RequestParams params = new RequestParams();

        params.put("username", etUsername.getText().toString());
        params.put("password", etPassword.getText().toString());

        client.post(urls.loginWebService, params,
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            String status = response.getString("success");

                            if (status.equals("1")) {
                                String id = response.getString("id");
                                String name = response.getString("name");
                                String mobile_no = response.getString("number");
                                String email_id = response.getString("email");
                                String username = response.getString("username");
                                String password = response.getString("password");
                                String address = response.getString("address");

                                Toast.makeText(LoginActivity.this, "Login Successfully Done", Toast.LENGTH_LONG).show();

                                editor.putString("username", username).commit();
                                editor.putBoolean("isLogin", true).commit();
                                editor.putString("id", id).commit();
                                editor.putString("name", name).commit();
                                editor.putString("number", mobile_no).commit();
                                editor.putString("email", email_id).commit();
                                editor.putString("password", password).commit();
                                editor.putString("address", address).commit();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Parsing error", Toast.LENGTH_SHORT).show();
                        } finally {
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    }


                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });

    }
}