package com.sda.bluj.marcin.androidpart2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

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

public class MainActivity extends AppCompatActivity implements ProductCardView.ProductCardViewInterface {
    @BindViews({R.id.product_1, R.id.product_2, R.id.product_3, R.id.product_4, R.id.product_5, R.id.product_6, R.id.product_7, R.id.product_8})
    List<ProductCardView> mProductCardView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        displayData();
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

    private void displayData() {
        List<Product> products = mProductRepository.getProducts();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            mProductCardView.get(i).bindTo(product, this);
        }
    }

    @Override
    public void onProductClicked(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.INTENT_PRODUCT_ID, product.getmId());
        startActivity(intent);

        Log.d("Shop", "Product clicked: " + product.getmName());
    }
}
