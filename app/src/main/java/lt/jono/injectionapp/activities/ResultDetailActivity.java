package lt.jono.injectionapp.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import lt.jono.injectionapp.database.ProductDatabaseHelper;
import lt.jono.injectionapp.R;

public class ResultDetailActivity extends AppCompatActivity {

    private TextView resultTextView;
    private ProductDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);

        resultTextView = findViewById(R.id.resultTextView);
        dbHelper = new ProductDatabaseHelper(this);

        loadResultDetails();
    }

    private void loadResultDetails() {
        String machineNumber = getIntent().getStringExtra("MACHINE_NUMBER");
        String formNumber = getIntent().getStringExtra("FORM_NUMBER");
        String date = getIntent().getStringExtra("DATE");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "machineNumber=? AND formNumber=? AND date=?";
        String[] selectionArgs = {machineNumber, formNumber, date};

        Cursor cursor = db.query("forms", null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            String result = "Staklės nr: " + cursor.getString(cursor.getColumnIndexOrThrow("machineNumber")) +
                    "\nFormos nr: " + cursor.getString(cursor.getColumnIndexOrThrow("formNumber")) +
                    "\nData: " + cursor.getString(cursor.getColumnIndexOrThrow("date")) +
                    "\nMedžiaga: " + cursor.getString(cursor.getColumnIndexOrThrow("material")) +
                    "\nDžiovinimo temperatūra: " + cursor.getInt(cursor.getColumnIndexOrThrow("dryingTemperature")) + "°C" +
                    "\nDžiovinimo laikas: " + cursor.getInt(cursor.getColumnIndexOrThrow("dryingTime")) + " min" +
                    "\nĮpurškimo svoris: " + cursor.getInt(cursor.getColumnIndexOrThrow("injectionWeight")) + " g" +
                    "\nĮpurškimo greitis: " + cursor.getInt(cursor.getColumnIndexOrThrow("injectionSpeed")) + " mm/s" +
                    "\nLaikymo spaudimas: " + cursor.getInt(cursor.getColumnIndexOrThrow("holdingPressure")) + " bar" +
                    "\nLaikymo greitis: " + cursor.getInt(cursor.getColumnIndexOrThrow("holdingSpeed")) + " mm/s" +
                    "\nLaikymo laikas: " + cursor.getInt(cursor.getColumnIndexOrThrow("holdingTime")) + " s" +
                    "\n1 zonos temperatūra: " + cursor.getInt(cursor.getColumnIndexOrThrow("cylinderTemperatureZone1")) + "°C" +
                    "\n2 zonos temperatūra: " + cursor.getInt(cursor.getColumnIndexOrThrow("cylinderTemperatureZone2")) + "°C" +
                    "\n3 zonos temperatūra: " + cursor.getInt(cursor.getColumnIndexOrThrow("cylinderTemperatureZone3")) + "°C" +
                    "\n4 zonos temperatūra: " + cursor.getInt(cursor.getColumnIndexOrThrow("cylinderTemperatureZone4")) + "°C" +
                    "\nAntgalio temperatūra: " + cursor.getInt(cursor.getColumnIndexOrThrow("nozzleTemperature")) + "°C" +
                    "\nPlokštės temperatūra: " + cursor.getInt(cursor.getColumnIndexOrThrow("moldTemperature")) + "°C" +
                    "\nRakinimo slėgis: " + cursor.getInt(cursor.getColumnIndexOrThrow("lockPressure")) + " kN";

            resultTextView.setText(result);
        }
        cursor.close();
    }
}
