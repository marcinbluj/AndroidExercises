package com.sda.bluj.marcin.androidpart2.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-02-20.
 */

public class AddProductActivity extends AppCompatActivity {
    @BindView(R.id.product_name)
    EditText mProductName;

    @BindView(R.id.product_price)
    TextInputEditText mProductPrice;

    @BindView(R.id.annual)
    RadioButton mJednoroczna;

    @BindView(R.id.biennial)
    RadioButton mDwuletnia;

    @BindView(R.id.perennial)
    RadioButton mWieloletnia;

    @BindView(R.id.garden_plant)
    Switch mOgrodowa;

    boolean flag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.add_product_button)
    public void onAddProductClicked(View view) {
        String name = mProductName.getText().toString();
        if (name.isEmpty()) {
            mProductName.setError("Wypełnij pole");
            return;
        }
        int price = getPrice();
        if (price < 0) {
            mProductPrice.setError("Wypełnij pole");
            return;
        }
        String description = createDescription();
        ProductRepository.getInstance().addProduct(name, price, description);
        finish();
    }

    private int getPrice() {
        int price = -1;
        if (!mProductPrice.getText().toString().isEmpty()) {
            price = Integer.parseInt(mProductPrice.getText().toString());
        }
        return price;
    }

    public String createDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("Typ rośliny: ");

        if (mJednoroczna.isChecked()) {
            builder.append("jednoroczna.");
        } else if (mDwuletnia.isChecked()) {
            builder.append("dwuletnia.");
        } else if (mWieloletnia.isChecked()) {
            builder.append("wieloletnia.");
        } else {
            builder.append("nie podano informacji.");
        }

        builder.append("\nOgrodowa: ");
        if (mOgrodowa.isChecked()) {
            builder.append("tak.");
        } else {
            builder.append("nie.");
        }

        return builder.toString();
    }
}
