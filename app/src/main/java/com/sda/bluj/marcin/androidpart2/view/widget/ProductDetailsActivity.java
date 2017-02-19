package com.sda.bluj.marcin.androidpart2.view.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RENT on 2017-02-18.
 */

public class ProductDetailsActivity extends AppCompatActivity {

    public static final String INTENT_PRODUCT_ID
            = ProductDetailsActivity.class.getSimpleName() + "productId";

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.primary_tv)
    TextView mProductName;

    @BindView(R.id.secondary_tv)
    TextView mProductPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        int productId = bundle.getInt(INTENT_PRODUCT_ID);
        Log.d("Shop", "Product id: " + productId);

        displayContent(productId);

        setupToolbar();

    }

    private void displayContent(int productId) {
        Product product = ProductRepository.getInstance().getProducts().get(productId-1);
        mProductImage.setImageResource(product.getmImageResId());
        mProductName.setText(product.getmName());
        mProductPrice.setText(String.valueOf(product.getmPrice()));
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
