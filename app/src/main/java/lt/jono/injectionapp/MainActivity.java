package lt.jono.injectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import lt.jono.injectionapp.activities.NewProductActivity;
import lt.jono.injectionapp.activities.SearchProductActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openNewProductActivity(View view) {
        Intent intent = new Intent(this, NewProductActivity.class);
        startActivity(intent);
    }

    public void openSearchProductActivity(View view) {
        Intent intent = new Intent(this, SearchProductActivity.class);
        startActivity(intent);
    }
}
