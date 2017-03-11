package com.sda.bluj.marcin.androidpart2.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import com.sda.bluj.marcin.androidpart2.AndroidApplication;
import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.database.Database;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-02-20.
 */

public class AddProductActivity extends AppCompatActivity { //TODO dodanie zdjecia
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

    @BindView(R.id.edit_product_edit_text)
    EditText mIdEditText;

    boolean mEditState;

    private Database mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        mIdEditText.setEnabled(false);


        if (!TextUtils.isEmpty(getIntent().getAction()) && getIntent().getAction().equals(ProductDetailsActivity.EDIT_ACTION)) {
            mEditState = true;
            insertBasicData();
        } else {
            mIdEditText.setText(R.string.autoincrement);
            mEditState = false;
        }

        mDatabase = AndroidApplication.getDatabase();

    }

    private void insertBasicData() {
        int id = getIntent().getIntExtra(ProductDetailsActivity.EDIT_PRODUCT_ID, -1);
        mIdEditText.setText(String.valueOf(id));

        Product product = ProductRepository.getInstance().getProduct(id);
        mProductName.setText(product.getName());
        mProductPrice.setText(String.valueOf(product.getPrice()));
    }

    @OnClick(R.id.add_product_button)
    public void onAddProductClicked(View view) {
        String name = mProductName.getText().toString().trim();
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

        if (!mEditState) {
            mDatabase.saveProduct(name, price, description);
        } else {
            int id = Integer.parseInt(mIdEditText.getText().toString().trim());
            Product product = ProductRepository.getInstance().getProduct(id);
            mDatabase.updateProduct(product, name, price, description);
        }
        finish();
//        onBackPressed();
    }

    private int getPrice() {
        int price = -1;
        if (!mProductPrice.getText().toString().isEmpty()) {
            price = Integer.parseInt(mProductPrice.getText().toString().trim());
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

    //    @OnClick(R.id.product_available)
//    @OnClick(R.id.add_product_button)
//    public void onDataPickerClick(View view) {
//        Calendar calendar = Calendar.getInstance();
//        DatePickerDialog datePickerDialog =
//                new DatePickerDialog(this, dataPickerLstener,
//                        calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.show(); //TODO date picker
//    }

//    private final DatePickerDialog.OnDateSetListener dataPickerLstener =
//            new DatePickerDialog.OnDateSetListener() {
//
//                @Override
//                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                }
//            };
}
