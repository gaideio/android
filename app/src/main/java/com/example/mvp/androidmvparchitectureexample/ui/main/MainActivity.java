package com.example.mvp.androidmvparchitectureexample.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.ui.chat.ChatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    TextToSpeech textToSpeech;
    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    assert result.getData() != null;
                    ArrayList<String> texts = result.getData().getStringArrayListExtra(
                            RecognizerIntent.EXTRA_RESULTS);
                    Toast
                            .makeText(getApplicationContext(), " " + Objects.requireNonNull(texts).get(0),
                                    Toast.LENGTH_SHORT)
                            .show();
                    textToSpeech.speak(Objects.requireNonNull(texts).get(0), TextToSpeech.QUEUE_FLUSH, null);
                }

                findViewById(R.id.micrecordingbtn).setVisibility(View.GONE);
                findViewById(R.id.micbtn).setVisibility(View.VISIBLE);
            });

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            Toast.makeText(this, "asdaasdasdasdsad", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        textToSpeech = new TextToSpeech(getApplicationContext(), i -> {
            if (i != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.US);
            }
        });
        findViewById(R.id.menubtn).setOnClickListener(click -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        findViewById(R.id.chatbtn).setOnClickListener(click -> {
            Intent i = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(i);
        });

        findViewById(R.id.micbtn).setOnClickListener(v -> {
            findViewById(R.id.micbtn).setVisibility(View.GONE);
            findViewById(R.id.micrecordingbtn).setVisibility(View.VISIBLE);

            openSomeActivityForResult();
        });

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        drawerLayout.setOnClickListener(click -> Toast.makeText(this, "What do do do?", Toast.LENGTH_SHORT).show());
//        drawerLayout.findViewById(R.id.logout).setOnClickListener(click -> {
//            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
//            startActivity(i);
//        });
//
//        drawerLayout.findViewById(R.id.logout).setOnClickListener(click -> {
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//            Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();
//            prefs.edit().putBoolean("loggedin", false).apply();
//            Intent ilogout = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(ilogout);
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    public void openSomeActivityForResult() {
        Intent intent
                = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");
        someActivityResultLauncher.launch(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Toast.makeText(this, "adsadadasdsadasdasdasdasdasdas", Toast.LENGTH_SHORT).show();

        return true;
    }
}
