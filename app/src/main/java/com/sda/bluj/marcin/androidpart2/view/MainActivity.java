package com.sda.bluj.marcin.androidpart2.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductDetailsActivity;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductDetailsFragment;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ProductListFragment.OnProductSelected {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    private ProductListFragment mProductListFragment;
    private ProductDetailsFragment mProductDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupNavigationView();
        setupActionBarDrawerToggle();
        setupBottomNavigationView();

        mProductListFragment = (ProductListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.product_list_fragment);
        mProductDetailsFragment = (ProductDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.product_details_fragment);
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
                                Toast.makeText(MainActivity.this, "Action1", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, TestStorageActivity.class));
                                break;
                            case R.id.application_drawer:
                                Toast.makeText(MainActivity.this, "Action2", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, FragmentTestActivity.class));
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

    @Override
    public void onProductSelected(Product product) {
        if (mProductDetailsFragment != null && mProductDetailsFragment.getActivity() != null) { //sprawdzanie czy istnieje fragment
            Log.i("TEST_FRAGMENT", mProductDetailsFragment.getActivity().toString());
            mProductDetailsFragment.updateProduct(product);
        } else {
            Intent intent = new Intent(this, ProductDetailsActivity.class);
            intent.putExtra(ProductDetailsActivity.INTENT_PRODUCT_ID, product.getId());
            startActivity(intent);
        }
    }
}
