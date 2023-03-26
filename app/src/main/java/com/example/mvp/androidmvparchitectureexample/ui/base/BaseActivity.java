package com.example.mvp.androidmvparchitectureexample.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.ComponentActivity;

import com.example.mvp.androidmvparchitectureexample.utils.DialogUtil;
import com.google.android.material.snackbar.Snackbar;

import butterknife.Unbinder;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public abstract class BaseActivity extends ComponentActivity implements IBaseView {
    protected ProgressDialog mProgressDialog;
    protected Unbinder mUnBinder;

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = DialogUtil.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showError(int errorId) {
        Snackbar.make(findViewById(android.R.id.content), getString(errorId), Snackbar.LENGTH_LONG)
                .show();
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

        super.onDestroy();
    }

    protected abstract void setUp();

}
