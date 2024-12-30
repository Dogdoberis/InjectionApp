package lt.jono.injectionapp.activities;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import lt.jono.injectionapp.R;

public class FindProductActivity extends AppCompatActivity {

    private ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_product);

        productList = findViewById(R.id.productList);

        // Užpildykite sąrašą su duomenimis iš SQLite duomenų bazės
        loadProductList();
    }

    private void loadProductList() {
        // Čia įrašykite duomenų užkrovimo iš SQLite kodą
    }
}
