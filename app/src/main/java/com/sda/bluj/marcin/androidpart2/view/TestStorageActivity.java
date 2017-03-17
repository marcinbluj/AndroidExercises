package com.sda.bluj.marcin.androidpart2.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestStorageActivity extends AppCompatActivity {

    @BindView(R.id.internal_dir)
    TextView mInternalDir;
    @BindView(R.id.cache_dir)
    TextView mCacheDir;
    @BindView(R.id.hello_world)
    TextView mText;
    @BindView(R.id.file_image)
    ImageView mFileImage;
    @BindView(R.id.product_info)
    TextView mProductInfo;
    @BindView(R.id.text_to_save)
    EditText mTextToSave;

    private final SharedPreferences.OnSharedPreferenceChangeListener mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Toast.makeText(TestStorageActivity.this, sharedPreferences.getString(key, null), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_storage);
        ButterKnife.bind(this);

        setup();
        displaySimpleText();
        displayImageFile();
        displayProduct();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        preferences.registerOnSharedPreferenceChangeListener(mListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        preferences.unregisterOnSharedPreferenceChangeListener(mListener);
    }

    private void setup() {
        mInternalDir.setText(mInternalDir.getContext().getFilesDir().toString());
        mCacheDir.setText(mCacheDir.getContext().getCacheDir().toString());
    }

    private void displaySimpleText() {
        String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream inputStream;
        String fileAsString = null;
        try {
            inputStream = openFileInput(filename);
            BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            fileAsString = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mText.setText(String.format("File content: %s", fileAsString));
    }

    private void displayImageFile() {
        String filename = "roslina.png";

        FileOutputStream outputStream = null;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = getBitmap(filename, outputStream);

        mFileImage.setImageBitmap(bitmap);

    }

    private Bitmap getBitmap(String filename, FileOutputStream outputStream) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.roslina);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        try {
            bitmap = BitmapFactory.decodeStream(openFileInput(filename), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void displayProduct() {
        String filename = "object";
        Product product = ProductRepository.getInstance().getProduct(1);

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    openFileOutput(filename, Context.MODE_PRIVATE));
            objectOutputStream.writeObject(product);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        product = getProduct(filename, product);

        mProductInfo.setText(product.toString());

    }

    private Product getProduct(String filename, Product product) {
        FileInputStream fis = null;
        ObjectInputStream is = null;
        try {
            fis = openFileInput(filename);
            is = new ObjectInputStream(fis);
            product = (Product) is.readObject();
            is.close();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    private void saveToSharedPreferences() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.saved_text), mTextToSave.getText().toString().trim());
        editor.apply();

        Log.i("TEST", String.valueOf(sharedPreferences.contains(getString(R.string.saved_text))));
    }

    @OnClick(R.id.save_text)
    public void saveText() {
        if (!TextUtils.isEmpty(mTextToSave.getText().toString().trim()))
            saveToSharedPreferences();
    }

    @OnClick(R.id.remove_text)
    public void removeText() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (sharedPreferences.contains(getString(R.string.saved_text))) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(getString(R.string.saved_text));
            editor.apply();
        }
    }

    @OnClick(R.id.show_text)
    public void showText() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (sharedPreferences.contains(getString(R.string.saved_text))) {
            String string = sharedPreferences.getString(getString(R.string.saved_text), "");
            Toast.makeText(this, string, Toast.LENGTH_LONG).show();
            Log.i("TEST", string);
        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
        }
    }


}
