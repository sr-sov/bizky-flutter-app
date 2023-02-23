package uplb.ics.cmsc191.bizky;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class CartActivity extends AppCompatActivity {

    private Context context = this;
    private ListView shoppingCart;
    private ShoppingCartArrayAdapter listAdapter;
    private ArrayList<Product> arrayOfProducts;
    private ArrayList<Product> arrayOfProductsInCart;

    private float cartTotalCost;
    private SQLiteDatabase db;
    private Cursor cursor;
    TextView tv;
    static TextView c;
    Intent prevIntent;

    static float cartTotal;
    static Hashtable<String, Integer> productCart = new Hashtable<String, Integer>();
    static Hashtable<String, Float> productCartPrice = new Hashtable<String, Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        prevIntent = getIntent();
        arrayOfProducts = (ArrayList<Product>) prevIntent.getSerializableExtra("arrayOfProducts");
        arrayOfProductsInCart = new ArrayList<Product>();
        // Construct the data source
        c = (TextView) findViewById(R.id.cartTotal);
        getCart();
        // Create the adapter to convert the array to views
        listAdapter = new ShoppingCartArrayAdapter(this, arrayOfProductsInCart);
        // Attach the adapter to a ListView
        shoppingCart = (ListView) findViewById(R.id.shoppingCart);
        shoppingCart.setAdapter(listAdapter);

    }

    private void getCart() {
        int i = 0;
        while (i < arrayOfProducts.size()) {
            if(arrayOfProducts.get(i).getCartQuantity()>0) {
                arrayOfProductsInCart.add(arrayOfProducts.get(i));
                float temp = arrayOfProducts.get(i).getPrice();
                int mult = arrayOfProducts.get(i).getCartQuantity();
                temp = temp * mult;
                cartTotalCost += temp;
            }
            i++;
        }
        TextView cartTotalTV = (TextView) findViewById(R.id.cartTotal);
        cartTotalTV.setText(String.valueOf(cartTotalCost));
    }

    public void confirm(View v){
        float totalcost = 0;
        String items = new String();
        bizkyDB DB = new bizkyDB(this);
        SQLiteDatabase db = DB.getWritableDatabase();
        // New value for one column
        for(Product p: arrayOfProductsInCart) {
            ContentValues values = new ContentValues();
            values.put("NAME", p.getName());
            values.put("PRICE", p.getPrice());
            values.put("QUANTITY", p.getQuantity());
            items += p.getName()+", ";
            totalcost += p.getPrice();
            db.update("INVENTORY", values, "_id="+p.id(), null);
        }
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        values.put("NAME", items);
        values.put("PRICE", totalcost);
        values.put("DATE", dateFormat.format(date));
        db.insert("INVOICE", null, values);
        Toast.makeText(context,"Successful Order!",Toast.LENGTH_SHORT);
    }
}
