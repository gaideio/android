/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Root;
import com.example.mvp.androidmvparchitectureexample.ui.about.AboutActivity;
import com.example.mvp.androidmvparchitectureexample.ui.chat.ChatActivity;
import com.example.mvp.androidmvparchitectureexample.ui.feedback.FeedbackActivity;
import com.example.mvp.androidmvparchitectureexample.ui.login.LoginActivity;
import com.example.mvp.androidmvparchitectureexample.ui.profile.ProfileActivity;
import com.example.mvp.androidmvparchitectureexample.ui.settings.SettingsActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, ContractMain.ContractView, RoutingListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    protected LatLng start = null;
    protected LatLng end = null;
    Location myLocation = null;

    @BindView(R.id.route)
    FloatingActionButton floatingActionButtonroute;

    @Inject
    MainPresenter mPresenter;
    TextToSpeech textToSpeech;
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
    private ArrayList<com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Route> myplaces;
    private ArrayList<LatLng> myplacestest;
    private GoogleMap mMap;
    private List<Polyline> polylines = null;

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void newChatMethod() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Are you sure? The current chat will be DELETED.");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            mPresenter.deleteChat(mPresenter.getStore().getAuthInfo().getJwttoken());
            dialog.dismiss();
        });

        alert.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        alert.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.about: {
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
                break;
            }

            case R.id.profile: {
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(i);
                break;
            }

            case R.id.newchat: {
                newChatMethod();
                break;
            }

            case R.id.help: {
                Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(i);
                break;
            }

            case R.id.settings: {
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
                break;
            }

            case R.id.logout: {
                logout();
            }
        }

        // close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        mPresenter.getStore().getAuthInfo().clear();
        Intent ilogout = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(ilogout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GaideioApp.getMainComponent().inject(this);
        mPresenter.attachView(this);
        textToSpeech = new TextToSpeech(getApplicationContext(), i -> {
            if (i != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.US);
            }
        });
        findViewById(R.id.menubtn).setOnClickListener(click -> drawerLayout.openDrawer(GravityCompat.START));

        findViewById(R.id.chatbtn).setOnClickListener(click -> {
            Intent i = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(i);
        });

        findViewById(R.id.micbtn).setOnClickListener(v -> {
            findViewById(R.id.micbtn).setVisibility(View.GONE);
            findViewById(R.id.micrecordingbtn).setVisibility(View.VISIBLE);

            openSomeActivityForResult();
        });

        setNavigationViewListener();

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
        mPresenter.getRoute(mPresenter.getStore().getAuthInfo().getJwttoken());
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
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showError(int errorId) {

    }

    @Override
    public void onDeleteChat(boolean successful) {
        Toast.makeText(this, "Old Chat deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void routeReady(Root root) {
        if (root != null) {
            // TODO
        }
    }

    //to get user location
    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMyLocationChangeListener(location -> {
            myLocation = location;
            LatLng ltlng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    ltlng, 16f);
            mMap.animateCamera(cameraUpdate);
        });

        //get destination location when user click on map
        mMap.setOnMapClickListener(latLng -> {
            mMap.clear();
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getMyLocation();
    }

    // function to find Routes.
    public void Findroutes() {
        if (mPresenter.getStore().getRoot().getRoute() != null) {
            myplacestest = new ArrayList<>();
//        if (mPresenter.getStore().getRoot().getRoute() == null) {
//            mPresenter.getRoute(mPresenter.getStore().getAuthInfo().getJwttoken());
//        } else {
            List<LatLng> waypoints = new ArrayList<>();

            start = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            end = new LatLng(mPresenter.getStore().getRoot().getRoute().get(mPresenter.getStore().getRoot().getRoute().size() - 1).getCoordinates().getLat(), mPresenter.getStore().getRoot().getRoute().get(mPresenter.getStore().getRoot().getRoute().size() - 1).getCoordinates().getLng());
            waypoints.add(start);
            mPresenter.getStore().getRoot().getRoute().forEach(route -> {
                waypoints.add(new LatLng(route.getCoordinates().getLat(), route.getCoordinates().getLng()));
            });
//
            for (int i = 0; i < mPresenter.getStore().getRoot().getRoute().size() - 1; i++) {
                myplacestest.add(new LatLng(mPresenter.getStore().getRoot().getRoute().get(i).getCoordinates().getLat(), mPresenter.getStore().getRoot().getRoute().get(i).getCoordinates().getLng()));
            }

//        mPresenter.getStore().getRoot().getRoute().forEach(route -> {
//            waypoints.add(new LatLng(route.getCoordinates().getLat(), route.getCoordinates().getLng()));
//        });

            Routing routing;
            routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.WALKING)
                    .withListener(this)
                    .alternativeRoutes(true)
                    .waypoints(waypoints)
                    .key("AIzaSyCDSSLtHiiRUttMp1DtCHOXPvoQUzbiTXg")
                    .build();
            routing.execute();
        }
    }
//    }
//    }

    @Override
    public void onRoutingFailure(RouteException e) {
        // The Routing request failed
        if (e != null) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {
    }

    // If Route finding success..
    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        Toast.makeText(MainActivity.this, "Routing...", Toast.LENGTH_LONG).show();

        CameraUpdateFactory.newLatLng(start);
        CameraUpdateFactory.zoomTo(16);
        if (polylines != null) {
            polylines.clear();
        }
        PolylineOptions polyOptions = new PolylineOptions();

        LatLng polylineStartLatLng = null;
        LatLng polylineEndLatLng = null;

        polylines = new ArrayList<>();
        for (int i = 0; i < route.size(); i++) {
            if (i == shortestRouteIndex) {
                polyOptions.color(getResources().getColor(R.color.quantum_googred));
                polyOptions.width(7);
                polyOptions.addAll(route.get(shortestRouteIndex).getPoints());
                Polyline polyline = mMap.addPolyline(polyOptions);
                polylineStartLatLng = polyline.getPoints().get(0);
                int k = polyline.getPoints().size();
                polylineEndLatLng = polyline.getPoints().get(k - 1);
                polylines.add(polyline);
            }
        }

        MarkerOptions startMarker = new MarkerOptions();
        startMarker.position(polylineStartLatLng);
        startMarker.title("Start");
        mMap.addMarker(startMarker);

        MarkerOptions startMarker1 = new MarkerOptions();
        startMarker1.position(polylineEndLatLng);
        startMarker1.title("End");
        mMap.addMarker(startMarker1);

        myplacestest.forEach(myplace -> {
            MarkerOptions startMar = new MarkerOptions();
            startMar.position(new LatLng(myplace.latitude, myplace.longitude));
            startMar.title("bitch");
            mMap.addMarker(startMar);
        });
    }

    @Override
    public void onRoutingCancelled() {
        Toast.makeText(this, "Error sorry : (", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.route)
    public void clickroute() {
        Findroutes();
    }
}

