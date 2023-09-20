package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

public class ActivityTwitter2 extends AppCompatActivity {
    FirebaseUser user;
    TextView username, useremail;
    ImageView profile;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter2);
        username = findViewById(R.id.userName);
        useremail = findViewById(R.id.userEmail);
        logout = findViewById(R.id.logout);
        profile = findViewById(R.id.photo);
        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
            Picasso.get().load(user1.getPhotoUrl()).placeholder(R.drawable.persongoogle).resize(400,400).into(profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                useremail.setText(uid);
                username.setText(name);
            }
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ActivityTwitter2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}