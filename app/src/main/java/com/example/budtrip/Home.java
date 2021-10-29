package com.example.budtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView nav_view;

    FirebaseAuth fAuth;
    FirebaseUser fUser;

    //Initialize variable
    EditText etSource,etDestination;
    Button btTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        drawerLayout = findViewById(R.id.drawer);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        View headerView = nav_view.getHeaderView(0);
        TextView userName = headerView.findViewById(R.id.userDisplayName);
        TextView userEmail = headerView.findViewById(R.id.userDisplayEmail);
        if (fAuth.getCurrentUser() != null) {
            userEmail.setText(fUser.getEmail());
            userName.setText(fUser.getDisplayName());
        }

        //Assign Variable
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value from edit text
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //Check condition
                if (sSource.equals("") && sDestination.equals("")){
                    //When both value blank
                    Toast.makeText(getApplicationContext(),
                            "Enter both location", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(sDestination)){
                    //When Destination value is empty
                    etDestination.setError("Distance is Required.");
                }
                else{
                    //When both value fill
                    //Display track
                    DisplayTrack(sSource,sDestination);
                }
            }

            private void DisplayTrack(String sSource, String sDestination) {
                //If the device does not have a map installed, then redirect it to play store
                try {
                    //When google map is installed
                    //Initialize url
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/" + sDestination);
                    //Initialize intent with action view
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    //Set Package
                    intent.setPackage("com.google.android.apps.maps");
                    //Set flags
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Start Activity
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    //When google map is not installed
                    //Initialize uri
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    //Initialize intent with action view
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    //Set flag
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Start activity'
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch(item.getItemId()){
            case R.id.trackDisply:
                break;

            case R.id.cityWeather:
                startActivity(new Intent(this, cityName.class));
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                break;

            case R.id.expAssistant:
                startActivity(new Intent(this, budgetForm.class));
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                break;

            case R.id.suggestions:
                startActivity(new Intent(this, travelSuggestion.class));
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                break;

            case R.id.shareapp:
                ApplicationInfo api = getApplicationContext().getApplicationInfo();
                String apkpath = api.sourceDir;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(apkpath));
                startActivity(Intent.createChooser(intent,"ShareVia"));
                break;

            case R.id.logout:
                checkUser();
                break;

            default:
                Toast.makeText(this, "Coming soon.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void checkUser() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,Login.class));
        overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fAuth.getCurrentUser() != null) {
            finish();
        } else {
            startActivity(new Intent(Home.this,Login.class));
            finish();
        }
    }
}