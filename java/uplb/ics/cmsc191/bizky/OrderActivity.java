package uplb.ics.cmsc191.bizky;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

public class OrderActivity extends AppCompatActivity {
    private ListView productListing;
    private MenuListArrayAdapter listAdapter;
    private ArrayList<Product> arrayOfProducts;

    private SQLiteDatabase db;
    private Cursor cursor;
    TextView tv;
    static TextView c;

    static float cartTotal;
    static Hashtable<String, Integer>productCart = new Hashtable<String, Integer>();
    static Hashtable<String, Float>productCartPrice = new Hashtable<String, Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        // Construct the data source
        c = (TextView) findViewById(R.id.cartButton);
        arrayOfProducts = new ArrayList<Product>();
        getInventory();
        // Create the adapter to convert the array to views
        listAdapter = new MenuListArrayAdapter(this, arrayOfProducts);
        // Attach the adapter to a ListView
        productListing = (ListView) findViewById(R.id.productListing);
        productListing.setAdapter(listAdapter);
        /*productListing.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int position, long id){
                Product editProduct = arrayOfProducts.get(position);
            }
        });
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

    public void viewCart(View view){
        Intent i = new Intent(view.getContext(), CartActivity.class);
        i.putExtra("arrayOfProducts",arrayOfProducts);

        startActivity(i);
    }

    static public void updateCart(float price, boolean isAdd, String product) {
        String cartString;
        if(isAdd==true){
            cartTotal += price;
        }
        else{
            cartTotal -= price;
        }

        if(productCart.containsKey(product)){
            productCart.put(product,productCart.get(product)+1);
        }
        else{
            productCart.put(product,1);
        }

        if(!(productCart.containsKey(product))){
            productCartPrice.put(product,price);
        }
    }
}
