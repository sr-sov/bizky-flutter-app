package uplb.ics.cmsc191.bizky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchToOrder(View v){
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
    public void switchToInventory(View v){
        Intent intent = new Intent(this, InventoryActivity.class);
        startActivity(intent);
    }
    public void switchToInvoice(View v){
        Intent intent = new Intent(this, InvoiceActivity.class);
        startActivity(intent);
    }
    public void switchToInsert(View v){
        Intent intent = new Intent(this, InsertActivity.class);
        startActivity(intent);
    }
}
