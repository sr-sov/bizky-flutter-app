package uplb.ics.cmsc191.bizky;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceActivity extends AppCompatActivity {
    private ListView invoiceListing;
    private InvoiceArrayAdapter listAdapter;
    private ArrayList<Invoice> arrayOfInvoices;

    private SQLiteDatabase db;
    private Cursor cursor;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        // Construct the data source
        arrayOfInvoices = new ArrayList<Invoice>();
        getInvoice();
        // Create the adapter to convert the array to views
        listAdapter = new InvoiceArrayAdapter(this, arrayOfInvoices);
        // Attach the adapter to a ListView
        invoiceListing = (ListView) findViewById(R.id.invoiceListing);
        invoiceListing.setAdapter(listAdapter);
    }

    private void getInvoice(){
        //from database
        //iterate foreach arrayOfInvoices.add
        bizkyDB DB = new bizkyDB(this);
        SQLiteDatabase db = DB.getWritableDatabase();
        cursor = db.query("INVOICE",
                new String[] {"_id", "NAME", "PRICE", "DATE"},
                null, null, null, null, null);
// Execute queries...
        if (cursor != null) {
            int idIndex = cursor.getColumnIndex("_id");
            int invoiceIndex = cursor.getColumnIndex("NAME");
            int priceIndex = cursor.getColumnIndex("PRICE");
            int dateIndex = cursor.getColumnIndex("DATE");

            cursor.moveToFirst();

            try{
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(idIndex);
                    String invoice = cursor.getString(invoiceIndex);
                    float price = cursor.getFloat(priceIndex);
                    String s = cursor.getString(dateIndex);

                    Invoice tempInvoice = new Invoice(id, invoice, price, s);
                    arrayOfInvoices.add(tempInvoice);


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
