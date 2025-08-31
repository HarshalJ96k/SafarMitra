SafarMitra – A Ride Management & User Service App
SafarMitra is an Android application built to provide seamless ride and service management features with user authentication, profile management, and vehicle details.
It is backed by a PHP + MySQL API hosted on XAMPP, with image storage in the server’s /uploads/ directory.



✨ Features

🔑 Authentication
User Registration & Login using PHP Web Services & MySQL.
Credentials stored securely in DB (customer_detail table).
Session maintained in app using SharedPreferences.

🧑‍💼 My Profile
Displays user details:
Name, Email, Phone, Username, Password, Address
Shows & updates profile photo:
User can select image from gallery.
Image uploaded to server via AsyncHttpClient.
Stored in /uploads/ folder and URL saved in profile_image column.
Profile image loaded locally (if cached) and from server (Glide/Picasso).

🚗 Car Details
Fetch vehicle details via SafarMitraCarDetails.php.
Data retrieved in JSON and displayed using RecyclerView.



☁️ Backend (PHP + MySQL + XAMPP)
Database: safarmitra
Table: customer_detail
Columns: id, name, number, email, username, password, address, profile_image
Web services:
SafarMitraUserLogin.php
SafarMitraUserRegisterDetail.php
SafarMitraCarDetails.php
upload_profile.php


🔔 Firebase Integration
FCM token generation for push notifications.



🛠️ Tech Stack
Android
Java (Android Studio Meerkat Feature Drop 2024.3.2)
RecyclerView for lists
AsyncHttpClient for API calls
Glide/Picasso (for image loading)
SharedPreferences for session & user info
Backend
PHP (XAMPP, Apache)
MySQL (phpMyAdmin)
REST APIs returning JSON responses



Project Structure
Android
com.example.firsttask
 ┣ common
 ┃ ┗ urls.java          # API endpoints
 ┣ LoginActivity.java
 ┣ RegistrationActivity.java
 ┣ My_profile.java      # User profile (with photo upload)
 ┣ CarDetailsActivity.java
 ┗ ...



 PHP (SafarMitraAPI/)
SafarMitraAPI/
 ┣ connection.php       # DB connection
 ┣ SafarMitraUserLogin.php
 ┣ SafarMitraUserRegisterDetail.php
 ┣ SafarMitraCarDetails.php
 ┣ upload_profile.php   # Profile photo upload & DB update
 ┗ uploads/        


 Setup Instructions
Backend Setup (XAMPP)
Install XAMPP and start Apache & MySQL.

Place SafarMitraAPI/ folder inside:
C:\xampp\htdocs\

Create database in phpMyAdmin:
CREATE DATABASE safarmitra;

Import table:

CREATE TABLE customer_detail (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  number VARCHAR(20),
  email VARCHAR(100),
  username VARCHAR(100),
  password VARCHAR(100),
  address VARCHAR(200),
  profile_image TEXT
);

Update connection.php with your DB credentials.


Android Setup

Clone this repo into Android Studio.

Update IP address in urls.java:
public static String webServiceAddress = "http://<YOUR_LOCAL_IP>/SafarMitraAPI/";

Build & Run on emulator or physical device.
Ensure device is connected to the same WiFi network as XAMPP server.



Author
Harshal Jadhav
Android Developer (GDGC Applicant)

![WhatsApp Image 2025-08-31 at 10 05 42_22494fd3](https://github.com/user-attachments/assets/b1fb1951-c637-449d-a3ec-3d9ce10d4c20)
![WhatsApp Image 2025-08-31 at 10 05 43_0edd0f8c](https://github.com/user-attachments/assets/fc3bce83-78d1-4d83-b1a3-3cc327430d17)
![WhatsApp Image 2025-08-31 at 10 05 36_39082222](https://github.com/user-attachments/assets/1122825a-be17-4a55-a0ae-b323b91d0d98)
![WhatsApp Image 2025-08-31 at 10 05 29_886ea395](https://github.com/user-attachments/assets/6b1115ee-35d3-42f7-b648-053f6d4d5a8b)
![WhatsApp Image 2025-08-31 at 10 05 44_925852b7](https://github.com/user-attachments/assets/0d163096-0446-4657-b6df-d7699ea0a20f)




