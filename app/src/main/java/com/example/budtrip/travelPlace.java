package com.example.budtrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class travelPlace extends AppCompatActivity {

    TextView nameholder, placeholder, stateholder, aboutholder;
    ImageView imageholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_travel_place);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        imageholder=findViewById(R.id.imagegholder);
        nameholder=findViewById(R.id.nameholder);
        placeholder=findViewById(R.id.placeholder);
        stateholder=findViewById(R.id.stateholder);
        aboutholder=findViewById(R.id.aboutholder);

        String travelName,travelPlace,travelState,travelPurl,travelAbout;

        travelName = getIntent().getStringExtra("name");
        travelPlace = getIntent().getStringExtra("place");
        travelState = getIntent().getStringExtra("state");
        travelPurl = getIntent().getStringExtra("purl");
        travelAbout = getIntent().getStringExtra("about");

        nameholder.setText(travelName);
        placeholder.setText(travelPlace);
        stateholder.setText(travelState);
        aboutholder.setText(travelAbout);
        Glide.with(getApplicationContext()).load(travelPurl).into(imageholder);
    }
}