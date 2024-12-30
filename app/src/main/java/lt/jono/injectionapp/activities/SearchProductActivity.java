package lt.jono.injectionapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lt.jono.injectionapp.database.ProductDatabaseHelper;
import lt.jono.injectionapp.R;

public class SearchProductActivity extends AppCompatActivity {

    private AutoCompleteTextView machineNumberAutoComplete, formNumberAutoComplete, dateAutoComplete;
    private Button searchButton;
    private ProductDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        machineNumberAutoComplete = findViewById(R.id.machineNumberAutoComplete);
        formNumberAutoComplete = findViewById(R.id.formNumberAutoComplete);
        dateAutoComplete = findViewById(R.id.dateAutoComplete);
        searchButton = findViewById(R.id.searchButton);
        dbHelper = new ProductDatabaseHelper(this);

        loadMachineNumbers();
        loadFormNumbers();
        loadDates();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProducts();
            }
        });
    }

    private void loadMachineNumbers() {
        List<String> machineNumbers = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(true, "machines", new String[]{"machineNumber"}, null, null, null, null, "machineNumber ASC", null);
        if (cursor.moveToFirst()) {
            do {
                machineNumbers.add(cursor.getString(cursor.getColumnIndexOrThrow("machineNumber")));
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, machineNumbers);
        machineNumberAutoComplete.setAdapter(adapter);
    }

    private void loadFormNumbers() {
        List<String> formNumbers = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(true, "forms", new String[]{"formNumber"}, null, null, null, null, "formNumber ASC", null);
        if (cursor.moveToFirst()) {
            do {
                formNumbers.add(cursor.getString(cursor.getColumnIndexOrThrow("formNumber")));
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, formNumbers);
        formNumberAutoComplete.setAdapter(adapter);
    }

    private void loadDates() {
        List<String> dates = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(true, "forms", new String[]{"date"}, null, null, null, null, "date ASC", null);
        if (cursor.moveToFirst()) {
            do {
                dates.add(cursor.getString(cursor.getColumnIndexOrThrow("date")));
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, dates);
        dateAutoComplete.setAdapter(adapter);
    }

    private void searchProducts() {
        String machineNumber = machineNumberAutoComplete.getText().toString();
        String formNumber = formNumberAutoComplete.getText().toString();
        String date = dateAutoComplete.getText().toString();

        Intent intent = new Intent(SearchProductActivity.this, ResultListActivity.class);
        intent.putExtra("MACHINE_NUMBER", machineNumber);
        intent.putExtra("FORM_NUMBER", formNumber);
        intent.putExtra("DATE", date);
        startActivity(intent);
    }
}
