package lt.jono.injectionapp.activities;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lt.jono.injectionapp.R;

public class MachineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine); // Nurodykite teisingą XML failą

        // Sukurkite ir nustatykite užrašą viršuje
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText("STAKLĖS");

        // Mygtukai
        Button listButton = findViewById(R.id.listButton);
        Button newButton = findViewById(R.id.newButton);
        Button backButton = findViewById(R.id.backButton);

        // Mygtuko "Sąrašas" paspaudimo veiksmas
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              try {
                  Log.d("MachineActivity", "Sąrašas mygtukas paspaustas");
                  // Perkelkite į "Sąrašas" veiksmą
                  Intent intent = new Intent(MachineActivity.this, ListActivity.class);
                  startActivity(intent);
              }catch (Exception e) {
                  Log.e("MachineActivity", "Sąrašas mygtukas paspaustas",e);
              }
            }
        });

        // Mygtuko "Naujos" paspaudimo veiksmas
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perkelkite į "Naujos" veiksmą
                Intent intent = new Intent(MachineActivity.this, NewMachineActivity.class);
                startActivity(intent);
            }
        });

        // Mygtuko "Grįžti" paspaudimo veiksmas
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Grįžti atgal į ankstesnį ekraną
                finish();
            }
        });
    }
}

