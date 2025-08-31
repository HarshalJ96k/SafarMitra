package com.example.firsttask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.firsttask.common.urls;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class My_profile extends AppCompatActivity {
    TextView tvname, tvEmail, tvPhone, tvUsername, tvPassword, tvCity, tvToken;
    ImageView ivProfilePhoto;
    Button btnChangeProfilePhoto;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Uri uri;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_my_profile);

        preferences = PreferenceManager.getDefaultSharedPreferences(My_profile.this);
        editor = preferences.edit();

        tvname = findViewById(R.id.tvname);
        tvEmail = findViewById(R.id.tvemail);
        tvPhone = findViewById(R.id.tvphone);
        tvUsername = findViewById(R.id.tvusername);
        tvPassword = findViewById(R.id.tvpassword);
        tvCity = findViewById(R.id.tvcity);
        tvToken = findViewById(R.id.tvMyProfileToken);
        btnChangeProfilePhoto = findViewById(R.id.btnUpdateProfilePhoto);
        ivProfilePhoto = findViewById(R.id.ivProfilePhoto);

        String strName = preferences.getString("name", "");
        String strEmail = preferences.getString("email", "");
        String strPhone = preferences.getString("phoneno", "");
        String strUsername = preferences.getString("username", "");
        String strPassword = preferences.getString("password", "");
        String strCity = preferences.getString("city", "");

        tvname.setText(strName);
        tvEmail.setText(strEmail);
        tvPhone.setText(strPhone);
        tvUsername.setText(strUsername);
        tvPassword.setText(strPassword);
        tvCity.setText(strCity);

        // Load saved profile image if exists
        File imageFile = new File(getFilesDir(), "profile.jpg");
        if (imageFile.exists()) {
            Bitmap savedBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            ivProfilePhoto.setImageBitmap(savedBitmap);
        }

        // Firebase token for notificaion sending
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult();
                        tvToken.setText(token);
                        Toast.makeText(My_profile.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        btnChangeProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });
    }

    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ivProfilePhoto.setImageBitmap(bitmap);

                // Save locally
                File imageFile = new File(getFilesDir(), "profile.jpg");
                FileOutputStream outputStream = openFileOutput(imageFile.getName(), MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();

                // 🔹 Upload to server
                uploadProfileToServer(imageFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 🔹 Upload function with AsyncHttpClient
    private void uploadProfileToServer(File file) {
        String email = preferences.getString("email", "");


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        try {
            params.put("email", email);
            params.put("profile_image", file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        client.post(urls.userProfileWebService, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                Toast.makeText(My_profile.this, "Uploading...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                Toast.makeText(My_profile.this, "Server: " + res, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(My_profile.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
