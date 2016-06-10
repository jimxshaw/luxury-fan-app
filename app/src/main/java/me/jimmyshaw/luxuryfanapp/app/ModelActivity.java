package me.jimmyshaw.luxuryfanapp.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import me.jimmyshaw.luxuryfanapp.R;
import me.jimmyshaw.luxuryfanapp.adapters.ModelAdapter;
import me.jimmyshaw.luxuryfanapp.fragments.ModelFragment;

public class ModelActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private SharedPreferences mSharedPreferences;

    private List<Fragment> mFragmentList;
    private List<String> mTabTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mSharedPreferences = ModelActivity.this.getPreferences(Context.MODE_PRIVATE);
        
        promptForZipcode();

        initialize();

        prepareDataResource();

        ModelAdapter adapter = new ModelAdapter(getSupportFragmentManager(), mFragmentList, mTabTitleList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void initialize() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("2016 Lexus - Models");

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mFragmentList = new ArrayList<>();
        mTabTitleList = new ArrayList<>();
    }

    private void promptForZipcode() {

        final AlertDialog.Builder inputDialogBuilder = new AlertDialog.Builder(this);
        inputDialogBuilder.setTitle("ZIP Code");
        inputDialogBuilder.setMessage("Please enter your ZIP code");

        final EditText userInput = new EditText(this);
        userInput.setHint(R.string.zip_code_default);
        inputDialogBuilder.setView(userInput);
        inputDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputValue = userInput.getText().toString();
                SharedPreferences.Editor editor = mSharedPreferences.edit();

                if (userInputValue.isEmpty() || userInputValue.length() != 5) {
                    editor.putString(getString(R.string.zip_code), getResources().getString(R.string.zip_code_default));
                }
                else {
                    editor.putString(getString(R.string.zip_code), userInputValue);
                }

                editor.apply();
            }
        });
        inputDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog inputDialog = inputDialogBuilder.create();
        inputDialog.show();
    }

    private void prepareDataResource() {
        addData(ModelFragment.newInstance(null), "ALL");
        addData(ModelFragment.newInstance("Sedan"), "SEDAN");
        addData(ModelFragment.newInstance("Coupe"), "COUPE");
        addData(ModelFragment.newInstance("4dr SUV"), "SUV");
        addData(ModelFragment.newInstance("4dr Hatchback"), "OTHER");
    }

    private void addData(Fragment fragment, String tabTitle) {
        mFragmentList.add(fragment);
        mTabTitleList.add(tabTitle);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_model, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }
        else if (id == R.id.nav_gallery) {

        }
        else if (id == R.id.nav_slideshow) {

        }
        else if (id == R.id.nav_manage) {

        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
