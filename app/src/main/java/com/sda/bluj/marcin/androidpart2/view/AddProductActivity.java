package com.sda.bluj.marcin.androidpart2.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductCardView;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-02-20.
 */

public class AddProductActivity extends AppCompatActivity implements ProductCardView.ProductCardViewInterface{
    public static final String NAME = "name";
    public static final String PRICE = "price";
    @BindView(R.id.product_name)
    EditText productName;

    @BindView(R.id.product_price)
    TextInputEditText productPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_product_button)
    public void onAddProductClicked(View view) {
        Intent intent = getIntent();
        String name = productName.getText().toString();
        int price = Integer.parseInt(productPrice.getText().toString());
        intent.putExtra(NAME, name);
        intent.putExtra(PRICE, price);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onProductClicked(Product product) {
//        Intent intent = new Intent(this, ProductDetailsActivity.class);
//        intent.putExtra(ProductDetailsActivity.INTENT_PRODUCT_ID, product.getmId());
//        startActivity(intent);
    }
}
