/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.ui.base.BaseActivity;
import com.example.mvp.androidmvparchitectureexample.ui.main.MainActivity;
import com.example.mvp.androidmvparchitectureexample.utils.AuthInfo;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.N)
public class LoginActivity extends BaseActivity implements ContractLogin.ContractView {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final int RC_SIGN_IN = 007;

    @Inject
    LoginPresenter mPresenter;

    private GoogleSignInClient mGoogleSignInClient;

    private void saveToState(GoogleSignInAccount account) {
        mPresenter.getStore().getAuthInfo().setEmail(account.getEmail());
        mPresenter.getStore().getAuthInfo().setName(account.getDisplayName());
        mPresenter.getStore().getAuthInfo().setImgURI(String.valueOf(account.getPhotoUrl()));
    }

    private void doLoginRequest(String googletoken) {
        mPresenter.login(googletoken);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            saveToState(account);
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
        setUpGoogleSignIn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AuthInfo.isLoggedin()) {
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
        mPresenter.checkjwtforautologin(mPresenter.getStore().getAuthInfo().getJwttoken());
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
        mPresenter.detachView();
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
        mPresenter.getStore().getAuthInfo().setLoggedin(true);
        mPresenter.getStore().getAuthInfo().setJwttoken(profile.getAccess_token());
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

