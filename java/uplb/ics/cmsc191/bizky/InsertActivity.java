package uplb.ics.cmsc191.bizky;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    TextView nameTV;
    TextView priceTV;
    TextView quantityTV;
    private String name;
    private float price;
    private int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        final bizkyDB DB = new bizkyDB(this);
        nameTV = (TextView) findViewById(R.id.name);
        priceTV = (TextView) findViewById(R.id.price);
        quantityTV = (TextView) findViewById(R.id.quantity);
        Button insert = (Button) findViewById(R.id.insert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameTV.getText().toString();
                price = Float.valueOf(priceTV.getText().toString());
                quantity = Integer.valueOf(quantityTV.getText().toString());
                SQLiteDatabase db = DB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("NAME", name);
                values.put("PRICE", price);
                values.put("QUANTITY", quantity);
                db.insert("INVENTORY", null, values);
                toast();
            }
        });

    }
    private void toast(){
        Toast.makeText(getApplicationContext(),"Successful Order!",Toast.LENGTH_SHORT);
    }
}
