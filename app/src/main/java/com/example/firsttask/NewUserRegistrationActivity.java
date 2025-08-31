package com.example.firsttask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firsttask.common.urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class NewUserRegistrationActivity extends AppCompatActivity
{
    EditText etEmail,etName,etPhoneNo,etusername,etPassword,etcity;
    Button btnRegister;
    TextView tvSignIn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_registration);
        setTitle("New User ");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


      etEmail=findViewById(R.id.etnewuseremail);
      etName=findViewById(R.id.etnewuserFullname);
      etPhoneNo=findViewById(R.id.etnewuserphoneno);
      etusername=findViewById(R.id.etnewuserusername);
      etPassword=findViewById(R.id.etnewuserpass);
      btnRegister=findViewById(R.id.btnnewuserRegister);
      tvSignIn=findViewById(R.id.tvnewuserSignIn);
      etcity=findViewById(R.id.etnewusercity);

      preferences= PreferenceManager.getDefaultSharedPreferences(NewUserRegistrationActivity.this);
      editor= preferences.edit();


        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if (etName.getText().toString().length()<8)
                {
                    etName.setError("Name must be greater than 8");
                }
                else if(etName.getText().toString().isEmpty())
                {
                    etName.setError("Please Enter Full Name");
                }
                else if(etEmail.getText().toString().isEmpty())
                {
                    etEmail.setError("Please Enter E-mail");
                }
                else if (!etEmail.getText().toString().contains("@")||!etEmail.getText().toString().contains(".com"))
                {
                    etEmail.setError("Please Enter Valid Email-Id");
                }
                else if (etPhoneNo.getText().toString().isEmpty())
                {
                    etPhoneNo.setError("Please Enter Phone no ");
                }
                else if (etPhoneNo.getText().toString().length()!=10)
                {
                    etPhoneNo.setError("Please Enter Valid Number");
                }
                else if(etusername.getText().toString().isEmpty())
                {
                    etusername.setError("Please Enter Username");
                }
                else if (etusername.getText().toString().length()<8)
                {
                    etusername.setError("Username Must be greater than 8");
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
                else if(etcity.getText().toString().isEmpty())
                {
                    etcity.setError("Please Enter City Name");
                }
                else
                {
                    progressDialog = new ProgressDialog(NewUserRegistrationActivity.this);
                    progressDialog.setTitle("Please wait...");
                    progressDialog.setMessage("Registration is in process");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();

                    editor.putString("name",etName.getText().toString());
                    editor.putString("phoneno",etPhoneNo.getText().toString());
                    editor.putString("email",etEmail.getText().toString());
                    editor.putString("username",etusername.getText().toString());
                    editor.putString("password",etPassword.getText().toString());
                    editor.putString("city",etcity.getText().toString());
                    editor.commit();
                    Toast.makeText(NewUserRegistrationActivity.this," Registered Successfully ",Toast.LENGTH_LONG).show();
                    Intent i =new Intent(NewUserRegistrationActivity.this,LoginActivity.class);
                    startActivity(i);
                    userRegisterDetails();
                }

            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i =new Intent(NewUserRegistrationActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }

    private void userRegisterDetails() {
//        //client and server communication ,// over network data transfer or manipulate
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();//client and server communication
        RequestParams requestParams = new RequestParams(); //put data
        requestParams.put("name", etName.getText().toString());//2 parameter , key and value
        requestParams.put("number", etPhoneNo.getText().toString());
         requestParams.put("email", etEmail.getText().toString());
        requestParams.put("username", etusername.getText().toString());
        requestParams.put("password", etPassword.getText().toString());
        requestParams.put("address", etcity.getText().toString());


        asyncHttpClient.post(urls.registerUserWebService, requestParams, new JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {
                            String status = response.getString("success");
                            if (status.equals("1")) {
                                Toast.makeText(NewUserRegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(NewUserRegistrationActivity.this, LoginActivity.class);
                                startActivity(i);
                                progressDialog.dismiss();


                            } else {
                                Toast.makeText(NewUserRegistrationActivity.this, "Already data exist", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }


                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                        Toast.makeText(NewUserRegistrationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }
        );
    }
}