package lt.jono.injectionapp.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lt.jono.injectionapp.database.ProductDatabaseHelper;
import lt.jono.injectionapp.R;

public class ResultActivity extends AppCompatActivity {

    private EditText formNumberEditText, machineNumberEditText, dryingTemperatureEditText, dryingTimeEditText, injectionWeightEditText, injectionSpeedEditText, holdingPressureEditText, holdingSpeedEditText, holdingTimeEditText, cylinderTemperatureZone1EditText, cylinderTemperatureZone2EditText, cylinderTemperatureZone3EditText, cylinderTemperatureZone4EditText, nozzleTemperatureEditText, moldTemperatureEditText, lockPressureEditText;
    private Button saveButton;
    private ProductDatabaseHelper dbHelper;
    private String material;
    private int glassFiber, weight, quantity, sprueWeight;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        formNumberEditText = findViewById(R.id.formNumberEditText);
        machineNumberEditText = findViewById(R.id.machineNumberEditText);
        dryingTemperatureEditText = findViewById(R.id.dryingTemperatureEditText);
        dryingTimeEditText = findViewById(R.id.dryingTimeEditText);
        injectionWeightEditText = findViewById(R.id.injectionWeightEditText);
        injectionSpeedEditText = findViewById(R.id.injectionSpeedEditText);
        holdingPressureEditText = findViewById(R.id.holdingPressureEditText);
        holdingSpeedEditText = findViewById(R.id.holdingSpeedEditText);
        holdingTimeEditText = findViewById(R.id.holdingTimeEditText);
        cylinderTemperatureZone1EditText = findViewById(R.id.cylinderTemperatureZone1EditText);
        cylinderTemperatureZone2EditText = findViewById(R.id.cylinderTemperatureZone2EditText);
        cylinderTemperatureZone3EditText = findViewById(R.id.cylinderTemperatureZone3EditText);
        cylinderTemperatureZone4EditText = findViewById(R.id.cylinderTemperatureZone4EditText);
        nozzleTemperatureEditText = findViewById(R.id.nozzleTemperatureEditText);
        moldTemperatureEditText = findViewById(R.id.moldTemperatureEditText);
        lockPressureEditText = findViewById(R.id.lockPressureEditText);
        saveButton = findViewById(R.id.saveButton);
        dbHelper = new ProductDatabaseHelper(this);
        material = getIntent().getStringExtra("MATERIAL");
        glassFiber = getIntent().getIntExtra("GLASS_FIBER", 0);
        weight = getIntent().getIntExtra("WEIGHT", 0);
        quantity = getIntent().getIntExtra("QUANTITY", 0);
        sprueWeight = getIntent().getIntExtra("SPRUE_WEIGHT", 0 );
        calculateAndDisplayResults();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveResults();
            }
        });
    }

    private void calculateAndDisplayResults() {
        int dryingTemperature = calculateDryingTemperature(material, glassFiber);
        int dryingTime = calculateDryingTime(material, glassFiber);
        int injectionWeight = calculateInjectionWeight(weight, quantity);
        int injectionSpeed = calculateInjectionSpeed(material);
        int holdingPressure = calculateHoldingPressure(material);
        int holdingSpeed = calculateHoldingSpeed(material);
        int holdingTime = calculateHoldingTime(material);
        int[] cylinderTemperatures = calculateCylinderTemperatures(material);
        int nozzleTemperature = calculateNozzleTemperature(material);
        int moldTemperature = calculateMoldTemperature(material);
        int lockPressure = calculateLockPressure(material);

        dryingTemperatureEditText.setText(String.valueOf(dryingTemperature));
        dryingTimeEditText.setText(String.valueOf(dryingTime));
        injectionWeightEditText.setText(String.valueOf(injectionWeight));
        injectionSpeedEditText.setText(String.valueOf(injectionSpeed));
        holdingPressureEditText.setText(String.valueOf(holdingPressure));
        holdingSpeedEditText.setText(String.valueOf(holdingSpeed));
        holdingTimeEditText.setText(String.valueOf(holdingTime));
        cylinderTemperatureZone1EditText.setText(String.valueOf(cylinderTemperatures[0]));
        cylinderTemperatureZone2EditText.setText(String.valueOf(cylinderTemperatures[1]));
        cylinderTemperatureZone3EditText.setText(String.valueOf(cylinderTemperatures[2]));
        cylinderTemperatureZone4EditText.setText(String.valueOf(cylinderTemperatures[3]));
        nozzleTemperatureEditText.setText(String.valueOf(nozzleTemperature));
        moldTemperatureEditText.setText(String.valueOf(moldTemperature));
        lockPressureEditText.setText(String.valueOf(lockPressure));
    }

    private int calculateDryingTemperature(String material, int glassFiber) {
        return 80 + (glassFiber / 10);
    }

    private int calculateDryingTime(String material, int glassFiber) {
        return 30 + (glassFiber / 5);
    }

    private int calculateInjectionWeight(int weight, int quantity) {
        return weight + quantity;
    }

    private int calculateInjectionSpeed(String material) {
        return 50; // Pavyzdinis skaičiavimas
    }

    private int calculateHoldingPressure(String material) {
        return 100; // Pavyzdinis skaičiavimas
    }

    private int calculateHoldingSpeed(String material) {
        return 40; // Pavyzdinis skaičiavimas
    }

    private int calculateHoldingTime(String material) {
        return 10; // Pavyzdinis skaičiavimas
    }

    private int[] calculateCylinderTemperatures(String material) {
        return new int[]{180, 190, 200, 210}; // Pavyzdinis skaičiavimas
    }

    private int calculateNozzleTemperature(String material) {
        return 220; // Pavyzdinis skaičiavimas
    }

    private int calculateMoldTemperature(String material) {
        return 60; // Pavyzdinis skaičiavimas
    }

    private int calculateLockPressure(String material) {
        int pressureInBar = 80; // Pavyzdinis slėgis bar
        double pressureInKN = pressureInBar * 0.1; // Pavyzdinė konversija į kiloniutonus (kN)
        return (int) pressureInKN;
    }

    private void saveResults() {
        String formNumber = formNumberEditText.getText().toString();
        String machineNumber = machineNumberEditText.getText().toString();
        String date = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(new Date());

        int dryingTemperature = Integer.parseInt(dryingTemperatureEditText.getText().toString());
        int dryingTime = Integer.parseInt(dryingTimeEditText.getText().toString());
        int injectionWeight = Integer.parseInt(injectionWeightEditText.getText().toString());
        int injectionSpeed = Integer.parseInt(injectionSpeedEditText.getText().toString());
        int holdingPressure = Integer.parseInt(holdingPressureEditText.getText().toString());
        int holdingSpeed = Integer.parseInt(holdingSpeedEditText.getText().toString());
        int holdingTime = Integer.parseInt(holdingTimeEditText.getText().toString());
        int cylinderTemperatureZone1 = Integer.parseInt(cylinderTemperatureZone1EditText.getText().toString());
        int cylinderTemperatureZone2 = Integer.parseInt(cylinderTemperatureZone2EditText.getText().toString());
        int cylinderTemperatureZone3 = Integer.parseInt(cylinderTemperatureZone3EditText.getText().toString());
        int cylinderTemperatureZone4 = Integer.parseInt(cylinderTemperatureZone4EditText.getText().toString());
        int nozzleTemperature = Integer.parseInt(nozzleTemperatureEditText.getText().toString());
        int moldTemperature = Integer.parseInt(moldTemperatureEditText.getText().toString());
        int lockPressure = Integer.parseInt(lockPressureEditText.getText().toString());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues machineValues = new ContentValues();
            machineValues.put("machineNumber", machineNumber);
            machineValues.put("date", date);
            long machineId = db.insert("machines", null, machineValues);

            ContentValues formValues = new ContentValues();
            formValues.put("formNumber", formNumber);
            formValues.put("machineId", machineId);
            formValues.put("date", date);
            long formId = db.insert("forms", null, formValues);

            ContentValues paramValues = new ContentValues();
            paramValues.put("material", material);
            paramValues.put("glassFiber", glassFiber);
            paramValues.put("weight", weight);
            paramValues.put("quantity", quantity);
            paramValues.put("sprueWeight", sprueWeight);
            paramValues.put("dryingTemperature", dryingTemperature);
            paramValues.put("dryingTime", dryingTime);
            paramValues.put("injectionWeight", injectionWeight);
            paramValues.put("injectionSpeed", injectionSpeed);
            paramValues.put("holdingPressure", holdingPressure);
            paramValues.put("holdingSpeed", holdingSpeed);
            paramValues.put("holdingTime", holdingTime);
            paramValues.put("cylinderTemperatureZone1", cylinderTemperatureZone1);
            paramValues.put("nozzleTemperature", nozzleTemperature);
            paramValues.put("moldTemperature", moldTemperature);
            paramValues.put("lockPressure", lockPressure);
            paramValues.put("formId", formId);
            paramValues.put("date", date);
            db.insert("parameters", null, paramValues);

            db.setTransactionSuccessful();
            Toast.makeText(this, "Rezultatai išsaugoti", Toast.LENGTH_SHORT).show();
            finish();
        } finally {
            db.endTransaction();
        }
    }
}

