package lt.jono.injectionapp.activities;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import lt.jono.injectionapp.R;

public class MachineDetailActivity extends AppCompatActivity {

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_detail); // XML failas, kuriame rodomi staklių parametrai

        // Gauti perduotą staklės ID
        long machineId = getIntent().getLongExtra("machine_id", -1);

        // Surasti staklės parametrus iš duomenų bazės pagal ID
        SQLiteDatabase db = openOrCreateDatabase("LiejiMoStakliuDB", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Stakles WHERE id = ?", new String[]{String.valueOf(machineId)});

        if (cursor.moveToFirst()) {
            // Atvaizduoti staklės parametrus
            TextView nameTextView = findViewById(R.id.nameTextView);
            TextView diameterTextView = findViewById(R.id.diameterTextView);
            TextView lengthTextView = findViewById(R.id.lengthTextView);
            TextView pressureTextView = findViewById(R.id.pressureTextView);

            // Nustatyti parametrų reikšmes pagal duomenis iš duomenų bazės
            nameTextView.setText(cursor.getString(cursor.getColumnIndex("pavadinimas")));
            diameterTextView.setText(cursor.getString(cursor.getColumnIndex("diametras")));
            lengthTextView.setText(cursor.getString(cursor.getColumnIndex("ilgis")));
            pressureTextView.setText(cursor.getString(cursor.getColumnIndex("spaudimas")));
        }

        cursor.close();
    }
}
