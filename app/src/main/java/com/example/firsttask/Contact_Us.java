package com.example.firsttask;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contact_Us extends AppCompatActivity {

    ImageView instagram, facebook, xLogo, linkedin;
    TextView phoneNumber1,phoneNumber2, emailAddress1,emailAddress2, websiteLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us); // XML file name

        // 🔗 Social Media Icons
        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);
        xLogo = findViewById(R.id.twitter);
        linkedin = findViewById(R.id.linkedin);


        // 📞 Contact TextViews
        phoneNumber1 = findViewById(R.id.phoneNumber1);
        phoneNumber2 = findViewById(R.id.phoneNumber2);
        emailAddress1 = findViewById(R.id.emailAddress1);
        emailAddress2 = findViewById(R.id.emailAddress2);
        websiteLink = findViewById(R.id.websiteLink);

        // 🌐 Website click
        websiteLink.setOnClickListener(v ->
                openLink("https://www.safarmitra.in"));

        // 📞 Phone dialer
        phoneNumber1.setOnClickListener(v ->
                dialPhoneNumber("9021307634")); // your contact number
        phoneNumber2.setOnClickListener(v ->
                dialPhoneNumber("9021257305")); // your contact number

        // 📧 Email app
        emailAddress1.setOnClickListener(v ->
                openEmail("support@safarmitra.in", "Support Request"));
        emailAddress2.setOnClickListener(v ->
                openEmail("feedback@safarmitra.in", "Support Request"));


        // 🔗 Social Media click events
        instagram.setOnClickListener(v -> openLink("https://instagram.com/harshalpatil96k_"));
        facebook.setOnClickListener(v -> openLink("https://facebook.com/Harshal Jadhav"));
        xLogo.setOnClickListener(v -> openLink("https://x.com/harshalpatil96k_"));
        linkedin.setOnClickListener(v -> openLink("https://linkedin.com/in/HARSHAL jadhav"));
    }

    // 🔗 General method to open URLs
    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // 📞 Open dialer
    private void dialPhoneNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    // 📧 Open email app (like Gmail)
    private void openEmail(String to, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + to));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}
