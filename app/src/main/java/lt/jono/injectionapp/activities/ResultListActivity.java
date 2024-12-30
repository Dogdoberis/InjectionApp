package lt.jono.injectionapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lt.jono.injectionapp.database.ProductDatabaseHelper;
import lt.jono.injectionapp.R;

public class ResultListActivity extends AppCompatActivity {

    private ListView resultListView;
    private ProductDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        resultListView = findViewById(R.id.resultListView);
        dbHelper = new ProductDatabaseHelper(this);

        loadResults();
    }

    private void loadResults() {
        Intent intent = getIntent();
        String machineNumber = intent.getStringExtra("MACHINE_NUMBER");
        String formNumber = intent.getStringExtra("FORM_NUMBER");
        String date = intent.getStringExtra("DATE");

        List<String> results = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "";
        List<String> selectionArgs = new ArrayList<>();

        if (!machineNumber.isEmpty()) {
            selection += "machineNumber=?";
            selectionArgs.add(machineNumber);
        }
        if (!formNumber.isEmpty()) {
            if (!selection.isEmpty()) selection += " AND ";
            selection += "formNumber=?";
            selectionArgs.add(formNumber);
        }
        if (!date.isEmpty()) {
            if (!selection.isEmpty()) selection += " AND ";
            selection += "date=?";
            selectionArgs.add(date);
        }

        Cursor cursor = db.query("forms", null, selection, selectionArgs.toArray(new String[0]), null, null, "date ASC");
        if (cursor.moveToFirst()) {
            do {
                String result = "Staklės nr: " + cursor.getString(cursor.getColumnIndexOrThrow("machineNumber")) +
                        ", Formos nr: " + cursor.getString(cursor.getColumnIndexOrThrow("formNumber")) +
                        ", Data: " + cursor.getString(cursor.getColumnIndexOrThrow("date"));
                results.add(result);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
        resultListView.setAdapter(adapter);

        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] parts = selectedItem.split(", ");
                String selectedMachineNumber = parts[0].split(": ")[1];
                String selectedFormNumber = parts[1].split(": ")[1];
                String selectedDate = parts[2].split(": ")[1];

                Intent intent = new Intent(ResultListActivity.this, ResultDetailActivity.class);
                intent.putExtra("MACHINE_NUMBER", selectedMachineNumber);
                intent.putExtra("FORM_NUMBER", selectedFormNumber);
                intent.putExtra("DATE", selectedDate);
                startActivity(intent);
            }
        });
    }
}
