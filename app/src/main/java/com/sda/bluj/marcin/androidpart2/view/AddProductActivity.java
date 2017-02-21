package com.sda.bluj.marcin.androidpart2.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductCardView;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductDetailsActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-02-20.
 */

public class AddProductActivity extends AppCompatActivity implements ProductCardView.ProductCardViewInterface{
//    @BindView(R.id.credit_radio_button)
//    RadioButton credit;
//
//    @BindView(R.id.money_radio_button)
//    RadioButton money;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
    }

//    @OnClick(R.id.add_product_button)
//    public void onAddProductClicked(View view) {
//        ProductCardView productCardView = new ProductCardView(getApplicationContext());
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
//                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        productCardView.setLayoutParams(params);
//
//        Product product = new Product(11, "Aaa", 5, R.drawable.lobelia);
//        product.setmDescription("aaa");
//        productCardView.bindTo(product, this);
//
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.line1);
//        linearLayout.addView(productCardView);
//    }

    @Override
    public void onProductClicked(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.INTENT_PRODUCT_ID, product.getmId());
        startActivity(intent);
    }
}
