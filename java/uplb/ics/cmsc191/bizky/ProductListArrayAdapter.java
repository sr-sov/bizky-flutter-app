package uplb.ics.cmsc191.bizky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductListArrayAdapter extends ArrayAdapter<Product> {
    public ProductListArrayAdapter(Context context, ArrayList<Product> product) {
        super(context, 0, product);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }
        // Lookup view for data population
        TextView tvProduct = (TextView) convertView.findViewById(R.id.product);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.price);
        TextView tvQuantityText = (TextView) convertView.findViewById(R.id.quantityText);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.quantity);
        // Populate the data into the template view using the data object
        tvProduct.setText(product.getName());
        tvPrice.setText("₱"+String.valueOf(product.getPrice()));
        tvQuantityText.setText("Stock left: ");
        tvQuantity.setText(String.valueOf(product.getQuantity()));
        // Return the completed view to render on screen
        return convertView;
    }
}