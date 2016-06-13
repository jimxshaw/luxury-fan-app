package me.jimmyshaw.luxuryfanapp.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.jimmyshaw.luxuryfanapp.R;

public class ModelDetailActivity extends AppCompatActivity {

    private static final String EXTRA_STYLE_ID = "style_id";

    private String mStyleId;

    private static Intent newIntent(Context packageContext, String styleId) {
        Intent intent = new Intent(packageContext, ModelDetailActivity.class);
        intent.putExtra(EXTRA_STYLE_ID, styleId);
        return intent;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_detail);


    }


}
