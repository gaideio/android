/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.ui.base.BaseActivity;
import com.example.mvp.androidmvparchitectureexample.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("NonConstantResourceId")
public class ProfileActivity extends BaseActivity implements ContractProfile.ContractView {

    @BindView(R.id.fulltext)
    EditText editText;

    @BindView(R.id.profilepic)
    CircleImageView profilepic;

    @BindView(R.id.name)
    TextView textView;

    @BindView(R.id.editbtn)
    Button editbtn;

    @BindView(R.id.clearbtn)
    Button clearbtn;

    @BindView(R.id.savebtn)
    Button savebtn;

    @BindView(R.id.deletebtn)
    Button deletebtn;

    @Inject
    ProfilePresenter mPresenter;

    @OnClick(R.id.editbtn)
    public void onEditButtonClicked() {
        editText.setVisibility(View.VISIBLE);
        editText.setText(mPresenter.getStore().getAuthInfo().getName());
        textView.setVisibility(View.GONE);
        editText.setEnabled(true);
        editbtn.setVisibility(View.GONE);
        savebtn.setVisibility(View.VISIBLE);
        clearbtn.setVisibility(View.VISIBLE);
        deletebtn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.clearbtn)
    public void onClearButtonClicked() {
        editText.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        editText.setEnabled(false);
        editbtn.setVisibility(View.VISIBLE);
        savebtn.setVisibility(View.GONE);
        clearbtn.setVisibility(View.GONE);
        deletebtn.setVisibility(View.GONE);
    }

    @OnClick(R.id.savebtn)
    public void onSaveButtonClicked() {
        textView.setVisibility(View.VISIBLE);
        editText.setVisibility(View.GONE);
        editText.setEnabled(false);
        editbtn.setVisibility(View.VISIBLE);
        savebtn.setVisibility(View.GONE);
        clearbtn.setVisibility(View.GONE);
        deletebtn.setVisibility(View.GONE);
        mPresenter.updateProfile(mPresenter.getStore().getAuthInfo().getJwttoken(), String.valueOf(editText.getText()));
    }

    @OnClick(R.id.deletebtn)
    public void onDeleteButtonClicked() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Are you sure you want to delete?");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            mPresenter.deleteAccount(mPresenter.getStore().getAuthInfo().getJwttoken());
            dialog.dismiss();
        });

        alert.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        alert.show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        GaideioApp.getProfileComponent().inject(this);
        mPresenter.attachView(this);

        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUp();
    }

    @Override
    protected void setUp() {
        String name = mPresenter.getStore().getAuthInfo().getName();
        String imgURI = mPresenter.getStore().getAuthInfo().getImgURI();
        textView.setText(name);
        Glide.with(this)
                .load(imgURI)
                .override(200, 200)
                .centerCrop()
                .into(profilepic);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onProfileReady(Profile profile) {
        textView.setText(profile.getName());
        editText.setText(profile.getName());
        mPresenter.getStore().getAuthInfo().setName(profile.getName());
        Toast.makeText(this, "Profile successfully updated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProfileUpdated(Boolean isUpdateSuccessful) {
        if (isUpdateSuccessful) {
            mPresenter.getProfile(this, mPresenter.getStore().getAuthInfo().getJwttoken());
        } else {
            Toast.makeText(this, "Could not update profile succesffully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccountDeleted(Boolean isAccountDeleted) {
        mPresenter.getStore().getAuthInfo().clear();
        Toast.makeText(this, "Your accout is deleted", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}
