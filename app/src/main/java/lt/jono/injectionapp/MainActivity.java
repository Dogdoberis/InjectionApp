package lt.jono.injectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import lt.jono.injectionapp.activities.ErrorsActivity;
import lt.jono.injectionapp.activities.MachineActivity;
import lt.jono.injectionapp.activities.PolimeraiActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing buttons
        Button paieskaButton = findViewById(R.id.paieskaButton);
        Button technologinisButton = findViewById(R.id.technologinisButton);
        Button formaButton = findViewById(R.id.formaButton);
        Button staklesButton = findViewById(R.id.staklesButton);
        Button polimeraiButton = findViewById(R.id.polimeraiButton);
        Button klaidosButton = findViewById(R.id.klaidosButton);

        // Setting click listeners (can be customized as needed)
        paieskaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for "Paieška" button
            }
        });

        technologinisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for "Technologinis" button
            }
        });

        formaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for "Forma" button
            }
        });

        staklesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for "Staklės" button
                Intent intent = new Intent(MainActivity.this, MachineActivity.class);
                startActivity(intent);
            }
        });

        polimeraiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PolimeraiActivity.class);
                startActivity(intent);
            }
        });

        klaidosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ErrorsActivity.class);
                startActivity(intent);
            }
        });
    }
}
