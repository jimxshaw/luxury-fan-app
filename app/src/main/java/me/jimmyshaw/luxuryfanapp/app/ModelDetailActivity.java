package me.jimmyshaw.luxuryfanapp.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import me.jimmyshaw.luxuryfanapp.R;

public class ModelDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MAKE = "make";
    private static final String EXTRA_MODEL = "model";
    private static final String EXTRA_STYLE_ID = "style_id";

    private String mMake;
    private String mModel;
    private String mStyleId;

    public static Intent newIntent(Context packageContext, String make, String model, String styleId) {
        Intent intent = new Intent(packageContext, ModelDetailActivity.class);
        intent.putExtra(EXTRA_MAKE, make);
        intent.putExtra(EXTRA_MODEL, model);
        intent.putExtra(EXTRA_STYLE_ID, styleId);
        return intent;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_detail);

        mMake = getIntent().getStringExtra(EXTRA_MAKE);
        mModel = getIntent().getStringExtra(EXTRA_MODEL);
        mStyleId = getIntent().getStringExtra(EXTRA_STYLE_ID);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mMake + " - " + mModel);
        }

    }


}
