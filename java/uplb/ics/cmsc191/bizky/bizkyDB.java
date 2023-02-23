package uplb.ics.cmsc191.bizky;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bizkyDB extends SQLiteOpenHelper {
    private static final String DB_Name = "bizky_inventory";
    private static final int DB_Version = 1;

    bizkyDB(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE INVENTORY ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PRICE REAL, "
                + "QUANTITY INTEGER);");
        db.execSQL("CREATE TABLE INVOICE ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PRICE REAL, "
                + "DATE DATETIME);");

        insertProduct(db, "Nestea",(float) 15.5, 2);
        insertProduct(db, "Bread",(float) 1.0, 5);
        insertProduct(db, "Coke",(float) 7.5, 3);
        insertProduct(db, "Spaghetti",(float) 100.0, 5);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        //insertProduct(db, "Coke",(float) 7.5, 3);
        //insertProduct(db, "Spaghetti",(float) 100.0, 5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertProduct(SQLiteDatabase db, String name, float price, int quantity) {
        ContentValues productValues = new ContentValues();
        productValues.put("NAME", name);
        productValues.put("PRICE", price);
        productValues.put("QUANTITY", quantity);

        db.insert("INVENTORY", null, productValues);
    }
}

