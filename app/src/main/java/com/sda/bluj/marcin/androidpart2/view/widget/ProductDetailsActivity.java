package com.sda.bluj.marcin.androidpart2.view.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepositoryInterface;
import com.sda.bluj.marcin.androidpart2.view.AddProductActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-02-18.
 */

public class ProductDetailsActivity extends AppCompatActivity {

    public static final String INTENT_PRODUCT_ID
            = ProductDetailsActivity.class.getSimpleName() + "productId";
    public static final String EDIT_PRODUCT_ID = "EDIT_PRODUCT_ID";
    public static final String EDIT_ACTION = "EDIT";

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.primary_tv)
    TextView mProductName;

    @BindView(R.id.secondary_tv)
    TextView mProductPrice;

    @BindView(R.id.product_description)
    TextView mProductDescription;

    Bundle bundle;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        int productId = bundle.getInt(INTENT_PRODUCT_ID);
        Log.d("Shop", "Product id: " + productId);

        Product product = mProductRepository.getProduct(productId);

        displayProductData(product);

        setupToolbar();

    }

    private void displayProductData(Product product) {
        int drawableResourceId = this.getResources()
                .getIdentifier(product.getImageName(), "drawable", getPackageName());
        mProductImage.setImageResource(drawableResourceId);
        mProductName.setText(product.getName());
        mProductPrice.setText(String.valueOf(product.getPrice()));
        mProductDescription.setText(product.getDescription());
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.edit_product)
    public void edit(View view) {
        int id = bundle.getInt(INTENT_PRODUCT_ID);

        Intent intent = new Intent(ProductDetailsActivity.this, AddProductActivity.class);
        intent.setAction(EDIT_ACTION);
        intent.putExtra(EDIT_PRODUCT_ID, id);
        startActivity(intent);

    }


}
