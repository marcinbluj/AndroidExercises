package com.sda.bluj.marcin.androidpart2.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.view.widget.FragmentCommunicationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activity_main)
    CoordinatorLayout mRootLayout;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.line1)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupNavigationView();
        setupActionBarDrawerToggle();
        setupBottomNavigationView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupActionBarDrawerToggle() {

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        mDrawerLayout.setScrimColor(ContextCompat.getColor(this, R.color.colorTransparentWhite));
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    private void setupNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        switch (item.getItemId()) {
                            case R.id.profile_drawer:
                                Toast.makeText(MainActivity.this, "Action1", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.application_drawer:
                                Toast.makeText(MainActivity.this, "Action2", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.rules_drawer:
                                Toast.makeText(MainActivity.this, "Action3", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                }
        );
    }

    private void setupBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.profile_drawer:
                                startActivity(new Intent(MainActivity.this, TestStorageActivity.class));
                                break;
                            case R.id.application_drawer:
                                startActivity(new Intent(MainActivity.this, FragmentTestActivity.class));
                                break;
                            case R.id.rules_drawer:
                                startActivity(new Intent(MainActivity.this, FragmentCommunicationActivity.class));
                                break;
                        }
                        return false;
                    }
                }
        );
    }

    @OnClick(R.id.add_new_product)
    public void onAddProductClicked(View view) {
        Log.d("Shop", "New product click");

        Snackbar snackbar = Snackbar.make(mRootLayout, "New product click", Snackbar.LENGTH_LONG)
                .setAction("Dodaj nowy produkt", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                        startActivity(intent);
                    }
                })
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorTextColor));

        snackbar.show();
    }
}
