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

public class ErrorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errors);

        ScrollView scrollView = findViewById(R.id.errorsScrollView);
        TextView errorsTextView = findViewById(R.id.errorsTextView);
        Button backButton = findViewById(R.id.backButton);

        // Load data from SQLite database
        SQLiteDatabase db = openOrCreateDatabase("LiejiMoStakliuDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Errors(tipas TEXT, priezastis TEXT, sprendimas TEXT);");

        Cursor cursor = db.rawQuery("SELECT * FROM Errors", null);

        StringBuilder errorsContent = new StringBuilder();
        while (cursor.moveToNext()) {
            errorsContent.append("Klaida: ").append(cursor.getString(1)).append("\n")
                    .append("priežastis: ").append(cursor.getString(2)).append("\n")
                    .append("sprendimas: ").append(cursor.getString(3)).append("\n\n");
        }
        cursor.close();
        db.close();

        errorsTextView.setText(errorsContent.toString());

        // Back button action
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to the previous screen
            }
        });
    }
}