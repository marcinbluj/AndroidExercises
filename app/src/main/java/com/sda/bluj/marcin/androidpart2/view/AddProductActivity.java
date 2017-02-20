package com.sda.bluj.marcin.androidpart2.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.sda.bluj.marcin.androidpart2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RENT on 2017-02-20.
 */

public class AddProductActivity extends AppCompatActivity {
    @BindView(R.id.credit_radio_button)
    RadioButton credit;

    @BindView(R.id.money_radio_button)
    RadioButton money;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
    }

    public void onAddProductClicked(View view) {

    }
}
