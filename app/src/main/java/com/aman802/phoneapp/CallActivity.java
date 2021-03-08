package com.aman802.phoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CallActivity extends AppCompatActivity {

    private TextView userTextImage, userName, userNumber;
    private ImageView userImageView;
    private OutgoingCallObject outgoingCallObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        outgoingCallObject = new OutgoingCallObject();
        userTextImage = findViewById(R.id.activity_call_user_text_image);
        userImageView = findViewById(R.id.activity_call_user_image_view);
        userName = findViewById(R.id.activity_call_user_name_text_view);
        userNumber = findViewById(R.id.activity_call_user_number_text_view);
        ImageView endCallButton = findViewById(R.id.activity_call_end_button_image_view);

        endCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outgoingCallObject.hangup();
                onBackPressed();
            }
        });

        getUserDataFromIntent();
    }

    private void  getUserDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null) {
            String displayName = intent.getStringExtra("userName");
            String number = intent.getData().getSchemeSpecificPart();
            int color = Util.getColorFromName(this, displayName);
            if (!"Unknown".equals(displayName)) {
                userTextImage.setText(String.valueOf(displayName.charAt(0)));
                Drawable background = userTextImage.getBackground();
                background.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
                userTextImage.setVisibility(View.VISIBLE);
                userImageView.setVisibility(View.GONE);
            }
            else {
                userImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_person));
                Drawable background = userImageView.getBackground();
                background.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
                userImageView.setVisibility(View.VISIBLE);
                userTextImage.setVisibility(View.GONE);
            }

            userName.setText(displayName);
            userNumber.setText(number);
        }
    }
}