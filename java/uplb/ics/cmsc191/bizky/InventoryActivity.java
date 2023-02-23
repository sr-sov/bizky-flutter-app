package uplb.ics.cmsc191.bizky;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {
    private ListView productListing;
    private ProductListArrayAdapter listAdapter;
    private ArrayList<Product> arrayOfProducts;

    private SQLiteDatabase db;
    private Cursor cursor;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        // Construct the data source
        arrayOfProducts = new ArrayList<Product>();
        tv = (TextView) findViewById(R.id.textView3);
        getInventory();
        // Create the adapter to convert the array to views
        listAdapter = new ProductListArrayAdapter(this, arrayOfProducts);
        // Attach the adapter to a ListView
        productListing = (ListView) findViewById(R.id.productListing);
        productListing.setAdapter(listAdapter);
        /*
        jobListing.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int position, long id){
                Intent i = new Intent(view.getContext(), JobActivity.class);
                i.putExtra("careerId",careerId.get(position));
                startActivity(i);
            }
        });
        THIS IS AN INSPIRATION FOR ADDING/SUBTRACTING MODULE
        */
    }

    private void getInventory(){
        //from database
        //iterate foreach arrayOfProducts.add
        bizkyDB DB = new bizkyDB(this);
        SQLiteDatabase db = DB.getWritableDatabase();
        cursor = db.query("INVENTORY",
                new String[] {"_id", "NAME", "PRICE", "QUANTITY"},
                null, null, null, null, null);
// Execute queries...
        if (cursor != null) {
            int idIndex = cursor.getColumnIndex("_id");
            int productIndex = cursor.getColumnIndex("NAME");
            int priceIndex = cursor.getColumnIndex("PRICE");
            int quantityIndex = cursor.getColumnIndex("QUANTITY");

            cursor.moveToFirst();

            try{
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(idIndex);
                    String product = cursor.getString(productIndex);
                    float price = cursor.getFloat(priceIndex);
                    int quantity = cursor.getInt(quantityIndex);

                    Product tempProduct = new Product(id, product, price, quantity);
                    arrayOfProducts.add(tempProduct);


                    // Dumps (2 rows):
                    // ID: 1 Title: Test Title Content: Test Content
                    // ID: 2 Title: Second Title Content: Second Content
                    //Log.d(TAG, "ID: " + id + " Title: " + title + " Content: " + content);
                }
            }
            finally {
                cursor.close();
            }
        }
        db.close();
    }
}
