package lt.jono.injectionapp.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import lt.jono.injectionapp.database.ProductDatabaseHelper;
import lt.jono.injectionapp.R;

public class LoadProductActivity extends AppCompatActivity {

    private TextView resultTextView;
    private EditText formNumberEditText, machineNumberEditText;
    private Button saveButton;
    private ProductDatabaseHelper dbHelper;
    private long productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_product);

        resultTextView = findViewById(R.id.resultTextView);
        formNumberEditText = findViewById(R.id.formNumberEditText);
        machineNumberEditText = findViewById(R.id.machineNumberEditText);
        saveButton = findViewById(R.id.saveButton);
        dbHelper = new ProductDatabaseHelper(this);

        String product = getIntent().getStringExtra("PRODUCT");
        loadProduct(product);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveResults();
            }
        });
    }

    private void loadProduct(String product) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("results", null, "results=?", new String[]{product}, null, null, null);
        if (cursor.moveToFirst()) {
            String results = cursor.getString(cursor.getColumnIndexOrThrow("results"));
            String formNumber = cursor.getString(cursor.getColumnIndexOrThrow("formNumber"));
            String machineNumber = cursor.getString(cursor.getColumnIndexOrThrow("machineNumber"));

            productId = cursor.getLong(cursor.getColumnIndexOrThrow("id"));

            resultTextView.setText(results);
            formNumberEditText.setText(formNumber);
            machineNumberEditText.setText(machineNumber);
        }
        cursor.close();
    }

    private void saveResults() {
        String formNumber = formNumberEditText.getText().toString();
        String machineNumber = machineNumberEditText.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("formNumber", formNumber);
        values.put("machineNumber", machineNumber);

        int rowsUpdated = db.update("results", values, "id=?", new String[]{String.valueOf(productId)});

        if (rowsUpdated > 0) {
            Toast.makeText(this, "Rezultatai atnaujinti", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Nepavyko atnaujinti rezultatų", Toast.LENGTH_SHORT).show();
        }
    }
}
