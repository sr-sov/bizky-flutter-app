package uplb.ics.cmsc191.bizky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingCartArrayAdapter extends ArrayAdapter<Product> {
    public ShoppingCartArrayAdapter(Context context, ArrayList<Product> product) {
        super(context, 0, product);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cart, parent, false);
        }
        // Lookup view for data population
        TextView tvProduct = (TextView) convertView.findViewById(R.id.product);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.quantity);
        TextView tvItemTotal = (TextView) convertView.findViewById(R.id.itemTotal);
        // Populate the data into the template view using the data object
        tvProduct.setText(product.getName());
        tvQuantity.setText("# in cart: " + String.valueOf(product.getCartQuantity()));
        tvItemTotal.setText("₱"+String.valueOf(product.getPrice()));
        // Return the completed view to render on screen
        return convertView;
    }
}