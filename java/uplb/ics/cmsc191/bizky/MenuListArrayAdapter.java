package uplb.ics.cmsc191.bizky;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

public class MenuListArrayAdapter extends ArrayAdapter<Product>{
    public MenuListArrayAdapter(Context context, ArrayList<Product> product) {
        super(context, 0, product);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, parent, false);
        }
        // Lookup view for data population
        TextView tvProduct = (TextView) convertView.findViewById(R.id.product);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.price);
        TextView tvQuantityText = (TextView) convertView.findViewById(R.id.quantityText);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.quantity);
        Button addButton = (Button) convertView.findViewById(R.id.addButton);
        Button subtractButton = (Button) convertView.findViewById(R.id.subtractButton);
        // Populate the data into the template view using the data object
        tvProduct.setText(product.getName());
        tvPrice.setText("₱"+String.valueOf(product.getPrice()));
        tvQuantityText.setText("Stock left: ");
        tvQuantity.setText(String.valueOf(product.getQuantity()));
        // Return the completed view to render on screen

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rl = (RelativeLayout)v.getParent();
                TextView tv = (TextView)rl.findViewById(R.id.quantity);
                TextView cqtv = (TextView)rl.findViewById(R.id.cartQuantity);
                int q = Integer.parseInt(tv.getText().toString());
                q--;
                if(!(q<0)) {
                    product.setQuantity(q);
                    product.setCartQuantity(q);
                    tv.setText(String.valueOf(product.getQuantity()));
                    cqtv.setText(String.valueOf(product.getCartQuantity()));
                    OrderActivity.updateCart(product.getPrice(),true, product.getName());
                }
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rl = (RelativeLayout)v.getParent();
                TextView tv = (TextView)rl.findViewById(R.id.quantity);
                TextView cqtv = (TextView)rl.findViewById(R.id.cartQuantity);
                int q = Integer.parseInt(tv.getText().toString());
                q++;
                int maxQ = product.getMaxQuantity();
                if(!(q>maxQ)){
                    product.setQuantity(q);
                    product.setCartQuantity(q);
                    tv.setText(String.valueOf(product.getQuantity()));
                    cqtv.setText(String.valueOf(product.getCartQuantity()));
                    OrderActivity.updateCart(product.getPrice(),false, product.getName());
                }
            }
        });

        return convertView;
    }

}
