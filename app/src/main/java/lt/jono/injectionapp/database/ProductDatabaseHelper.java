package lt.jono.injectionapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 3; // Padidinkite duomenų bazės versiją

    public ProductDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE machines (id INTEGER PRIMARY KEY AUTOINCREMENT, machineNumber TEXT, date TEXT)");
        db.execSQL("CREATE TABLE forms (id INTEGER PRIMARY KEY AUTOINCREMENT, formNumber TEXT, machineId INTEGER, date TEXT, FOREIGN KEY(machineId) REFERENCES machines(id))");
        db.execSQL("CREATE TABLE parameters (id INTEGER PRIMARY KEY AUTOINCREMENT, material TEXT, glassFiber INTEGER, weight INTEGER, quantity INTEGER, sprueWeight INTEGER, dryingTemperature INTEGER, dryingTime INTEGER, injectionWeight INTEGER, injectionSpeed INTEGER, holdingPressure INTEGER, holdingSpeed INTEGER, holdingTime INTEGER, cylinderTemperatureZone1 INTEGER, cylinderTemperatureZone2 INTEGER, cylinderTemperatureZone3 INTEGER, cylinderTemperatureZone4 INTEGER, nozzleTemperature INTEGER, moldTemperature INTEGER, lockPressure INTEGER, formId INTEGER, date TEXT, FOREIGN KEY(formId) REFERENCES forms(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3) {
            db.execSQL("CREATE TABLE machines (id INTEGER PRIMARY KEY AUTOINCREMENT, machineNumber TEXT, date TEXT)");
            db.execSQL("CREATE TABLE forms (id INTEGER PRIMARY KEY AUTOINCREMENT, formNumber TEXT, machineId INTEGER, date TEXT, FOREIGN KEY(machineId) REFERENCES machines(id))");
            db.execSQL("CREATE TABLE parameters (id INTEGER PRIMARY KEY AUTOINCREMENT, material TEXT, glassFiber INTEGER, weight INTEGER, quantity INTEGER, sprueWeight INTEGER, dryingTemperature INTEGER, dryingTime INTEGER, injectionWeight INTEGER, injectionSpeed INTEGER, holdingPressure INTEGER, holdingSpeed INTEGER, holdingTime INTEGER, cylinderTemperatureZone1 INTEGER, cylinderTemperatureZone2 INTEGER, cylinderTemperatureZone3 INTEGER, cylinderTemperatureZone4 INTEGER, nozzleTemperature INTEGER, moldTemperature INTEGER, lockPressure INTEGER, formId INTEGER, date TEXT, FOREIGN KEY(formId) REFERENCES forms(id))");
        }
    }
}
