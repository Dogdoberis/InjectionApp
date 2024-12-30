package lt.jono.injectionapp.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.core.content.ContextCompat;

import lt.jono.injectionapp.R;
import lt.jono.injectionapp.database.ProductDatabaseHelper;


public class NewProductActivity extends AppCompatActivity {

    private Spinner materialSpinner;
    private EditText glassFiberEditText, weightEditText, quantityEditText, sprueWeightEditText;
    private EditText formNumberEditText, machineNumberEditText, dryingTemperatureEditText, dryingTimeEditText, injectionWeightEditText, injectionSpeedEditText, holdingPressureEditText, holdingSpeedEditText, holdingTimeEditText, cylinderTemperatureZone1EditText, cylinderTemperatureZone2EditText, cylinderTemperatureZone3EditText, cylinderTemperatureZone4EditText, nozzleTemperatureEditText, moldTemperatureEditText, lockPressureEditText;
    private TextView requiredParamsTextView;
    private Button showParametersButton, saveButton;
    private LinearLayout resultsLayout;
    private ProductDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        initializeViews();
        setupMaterialSpinner();
        setupInputWatchers();
        setupButtons();
    }

    private void initializeViews() {
        materialSpinner = findViewById(R.id.materialSpinner);
        glassFiberEditText = findViewById(R.id.glassFiberEditText);
        weightEditText = findViewById(R.id.weightEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        sprueWeightEditText = findViewById(R.id.sprueWeightEditText);
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
        requiredParamsTextView = findViewById(R.id.requiredParamsTextView);
        showParametersButton = findViewById(R.id.showParametersButton);
        saveButton = findViewById(R.id.saveButton);
        resultsLayout = findViewById(R.id.resultsLayout);
        dbHelper = new ProductDatabaseHelper(this);
    }

    private void setupMaterialSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.materials_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materialSpinner.setAdapter(adapter);
        materialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position != 0) {
                    glassFiberEditText.setVisibility(View.VISIBLE);
                } else {
                    glassFiberEditText.setVisibility(View.GONE);
                }
                checkInputs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void setupInputWatchers() {
        TextWatcher inputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        weightEditText.addTextChangedListener(inputWatcher);
        quantityEditText.addTextChangedListener(inputWatcher);
        sprueWeightEditText.addTextChangedListener(inputWatcher);
    }

    private void setupButtons() {
        showParametersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameters();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });
    }

    private void checkInputs() {
        String material = materialSpinner.getSelectedItem().toString();
        String weight = weightEditText.getText().toString().trim();
        String quantity = quantityEditText.getText().toString().trim();
        String sprueWeight = sprueWeightEditText.getText().toString().trim();

        boolean allFieldsFilled = !material.equals("Plastiko rūšis") && !weight.isEmpty() && !quantity.isEmpty() && !sprueWeight.isEmpty();

        if (allFieldsFilled) {
            requiredParamsTextView.setVisibility(View.GONE);
            showParametersButton.setEnabled(true);
            showParametersButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_blue_light));
        } else {
            requiredParamsTextView.setVisibility(View.VISIBLE);
            showParametersButton.setEnabled(false);
            showParametersButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray));
        }
    }

    private void showParameters() {
        resultsLayout.setVisibility(View.VISIBLE);

        // Apskaičiuoti rezultatai (pavyzdinės reikšmės)
        dryingTemperatureEditText.setText("80");
        dryingTimeEditText.setText("30");
        injectionWeightEditText.setText("50");
        injectionSpeedEditText.setText("60");
        holdingPressureEditText.setText("70");
        holdingSpeedEditText.setText("80");
        holdingTimeEditText.setText("90");
        cylinderTemperatureZone1EditText.setText("100");
        cylinderTemperatureZone2EditText.setText("110");
        cylinderTemperatureZone3EditText.setText("120");
        cylinderTemperatureZone4EditText.setText("130");
        nozzleTemperatureEditText.setText("140");
        moldTemperatureEditText.setText("150");
        lockPressureEditText.setText("160");

        formNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputsForSave();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        machineNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputsForSave();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        checkInputsForSave();
    }

    private void checkInputsForSave() {
        String formNumber = formNumberEditText.getText().toString().trim();
        String machineNumber = machineNumberEditText.getText().toString().trim();

        boolean allFieldsFilled = !formNumber.isEmpty() && !machineNumber.isEmpty();

        if (allFieldsFilled) {
            saveButton.setEnabled(true);
            saveButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_blue_light));
        } else {
            saveButton.setEnabled(false);
            saveButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray));
        }
    }

    private void saveProduct() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String material = materialSpinner.getSelectedItem().toString();
        int glassFiber = Integer.parseInt(glassFiberEditText.getText().toString().trim());
        int weight = Integer.parseInt(weightEditText.getText().toString().trim());
        int quantity = Integer.parseInt(quantityEditText.getText().toString().trim());
        int sprueWeight = Integer.parseInt(sprueWeightEditText.getText().toString().trim());
        String formNumber = formNumberEditText.getText().toString().trim();
        String machineNumber = machineNumberEditText.getText().toString().trim();
        int dryingTemperature = Integer.parseInt(dryingTemperatureEditText.getText().toString().trim());
        int dryingTime = Integer.parseInt(dryingTimeEditText.getText().toString().trim());
        int injectionWeight = Integer.parseInt(injectionWeightEditText.getText().toString().trim());
        int injectionSpeed = Integer.parseInt(injectionSpeedEditText.getText().toString().trim());
        int holdingPressure = Integer.parseInt(holdingPressureEditText.getText().toString().trim());
        int holdingSpeed = Integer.parseInt(holdingSpeedEditText.getText().toString().trim());
        int holdingTime = Integer.parseInt(holdingTimeEditText.getText().toString().trim());
        int cylinderTemperatureZone1 = Integer.parseInt(cylinderTemperatureZone1EditText.getText().toString().trim());
        int cylinderTemperatureZone2 = Integer.parseInt(cylinderTemperatureZone2EditText.getText().toString().trim());
        int cylinderTemperatureZone3 = Integer.parseInt(cylinderTemperatureZone3EditText.getText().toString().trim());
        int cylinderTemperatureZone4 = Integer.parseInt(cylinderTemperatureZone4EditText.getText().toString().trim());
        int nozzleTemperature = Integer.parseInt(nozzleTemperatureEditText.getText().toString().trim());
        int moldTemperature = Integer.parseInt(moldTemperatureEditText.getText().toString().trim());
        int lockPressure = Integer.parseInt(lockPressureEditText.getText().toString().trim());

        ContentValues values = new ContentValues();
        values.put("material", material);
        values.put("glassFiber", glassFiber);
        values.put("weight", weight);
        values.put("quantity", quantity);
        values.put("sprueWeight", sprueWeight);
        values.put("formNumber", formNumber);
        values.put("machineNumber", machineNumber);
        values.put("dryingTemperature", dryingTemperature);
        values.put("dryingTime", dryingTime);
        values.put("injectionWeight", injectionWeight);
        values.put("injectionSpeed", injectionSpeed);
        values.put("holdingPressure", holdingPressure);
        values.put("holdingSpeed", holdingSpeed);
        values.put("holdingTime", holdingTime);
        values.put("cylinderTemperatureZone1", cylinderTemperatureZone1);
        values.put("cylinderTemperatureZone2", cylinderTemperatureZone2);
        values.put("cylinderTemperatureZone3", cylinderTemperatureZone3);
        values.put("cylinderTemperatureZone4", cylinderTemperatureZone4);
        values.put("nozzleTemperature", nozzleTemperature);
        values.put("moldTemperature", moldTemperature);
        values.put("lockPressure", lockPressure);

        long newRowId = db.insert("products", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Produktas sėkmingai išsaugotas", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nepavyko išsaugoti produkto", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}


