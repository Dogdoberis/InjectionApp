package lt.jono.injectionapp.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import lt.jono.injectionapp.R;

public class PolimeraiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polimerai);

        ScrollView scrollView = findViewById(R.id.polimeraiScrollView);
        TextView polimeraiTextView = findViewById(R.id.polimeraiTextView);
        Button backButton = findViewById(R.id.backButton);

        // Load data from SQLite database
        SQLiteDatabase db = openOrCreateDatabase("LiejiMoStakliuDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Polimerai(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pavadinimas VARCHAR(255), " +
                "modifikacijos VARCHAR(255), " +
                "lydymosi_temp VARCHAR(50), " +
                "kritine_temp VARCHAR(50), " +
                "liejimo_temp VARCHAR(50), " +
                "dziovinimo_temp VARCHAR(50));");

        // Fetching data from the Polimerai table
        Cursor cursor = db.rawQuery("SELECT * FROM Polimerai", null);

        StringBuilder polimeraiContent = new StringBuilder();
        while (cursor.moveToNext()) {
            polimeraiContent.append("Pavadinimas: ").append(cursor.getString(1)).append("\n")
                    .append("Modifikacijos: ").append(cursor.getString(2)).append("\n")
                    //.append("Lydymosi Temp: ").append(cursor.getString(3)).append("\n")
                    //.append("Kritinė Temp: ").append(cursor.getString(4)).append("\n")
                    .append("Liejimo Temp: ").append(cursor.getString(5)).append("\n")
                    .append("Džiovinimo Temp: ").append(cursor.getString(6)).append("\n\n");
        }
        cursor.close();
        db.close();

        polimeraiTextView.setText(polimeraiContent.toString());

        // Back button action
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to the previous screen
            }
        });
    }
}

