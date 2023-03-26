package com.example.mvp.androidmvparchitectureexample.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.mvp.androidmvparchitectureexample.R;


/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class DialogUtil {

    public static ProgressDialog showLoadingDialog(Context context) {

        ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.show();

        if (mProgressDialog.getWindow() != null) {
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        mProgressDialog.setContentView(R.layout.progress_dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);

        return mProgressDialog;
    }
}
