package com.sda.bluj.marcin.androidpart2.view;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Build;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepositoryInterface;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductCardView;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements
        ProductCardView.ProductCardViewInterface {

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

    private ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        displayData();
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
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    private void displayData() {
        List<Product> products = mProductRepository.getProducts();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            ProductCardView cardView = new ProductCardView(this);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cardView.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this, R.animator.card_view_translation_z));
            }

            int height = getPixelsFromDp(80);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

            int margin = getPixelsFromDp(4);
            params.setMargins(margin, margin, margin, margin);
            cardView.setLayoutParams(params);
            cardView.bindTo(product, this);

            linearLayout.addView(cardView);
        }
    }

    private int getPixelsFromDp(int dp) { //TODO utils
        DisplayMetrics displaymetrics = getBaseContext().getResources().getDisplayMetrics();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displaymetrics));
    }

    @Override
    public void onProductClicked(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.INTENT_PRODUCT_ID, product.getId());
        startActivity(intent);

        Log.d("Shop", "Product clicked: " + product.getName());
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
