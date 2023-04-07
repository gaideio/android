/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.ui.base.BaseActivity;
import com.example.mvp.androidmvparchitectureexample.ui.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ContractLogin.ContractView {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final int RC_SIGN_IN = 007;

    @Inject
    LoginPresenter mPresenter;

    private GoogleSignInClient mGoogleSignInClient;

    private void saveToSharedPreferences(GoogleSignInAccount account) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString("email", account.getEmail()).apply();
        prefs.edit().putString("fullname", account.getDisplayName()).apply();
        prefs.edit().putString("imgURI", String.valueOf(account.getPhotoUrl())).apply();
    }

    private void doLoginRequest(String googletoken) {
        mPresenter.login(googletoken);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            saveToSharedPreferences(account);
            doLoginRequest(account.getIdToken());
        } catch (ApiException e) {
            System.out.println(e.getMessage());
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void setUpGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.revokeAccess();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GaideioApp.getLoginComponent().inject(this);
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        onResume();
        setUpGoogleSignIn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean loggedinSharedprefs = prefs.getBoolean("loggedin", false);
        if (loggedinSharedprefs) {
            isAlreadyLoggedIn();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void isAlreadyLoggedIn() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mPresenter.checkjwtforautologin("Bearer " + prefs.getString("jwttoken", null));
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        signIn();
    }

    @Override
    protected void onDestroy() {
//        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onLoginDone(Profile profile) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString("jwttoken", profile.getAccess_token()).apply();
        prefs.edit().putBoolean("loggedin", true).apply();
        Boolean isnew = profile.getIsnew();
        if (isnew) {
            Toast.makeText(this, "Signed up and logged In", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
        }

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    @Override
    public void oncheckjwtforautologin(Boolean loggedinalready) {
        if (loggedinalready) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }
}

