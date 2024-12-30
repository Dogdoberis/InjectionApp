package lt.jono.injectionapp.activities;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import lt.jono.injectionapp.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list); // XML failas, kuriame atvaizduojamas sąrašas

        ListView listView = findViewById(R.id.stakliuListView);

        Button backButton = findViewById(R.id.backButton);

        // Gauti duomenis iš duomenų bazės
        SQLiteDatabase db = openOrCreateDatabase("LiejiMoStakliuDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Stakles(pavadinimas TEXT, diametras TEXT, ilgis TEXT, spaudimas TEXT)");
        Cursor cursor = db.rawQuery("SELECT * FROM Stakles", null);

        // Sukurti adapterį, kad galėtume rodyti duomenis sąraše
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor,
                new String[]{"id"}, new int[]{android.R.id.text1}, 0);

        listView.setAdapter(adapter);

        // Nustatyti paspaudimo veiksmo mygtuką
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Perkelti į kitą veiklą su pasirinkta staklių ID
            Intent intent = new Intent(ListActivity.this, MachineDetailActivity.class);
            intent.putExtra("machine_id", id); // Perduodame pasirinktą staklės ID
            startActivity(intent);
        });

        // Grįžti atgal mygtukas
        backButton.setOnClickListener(v -> finish());
    }
}
