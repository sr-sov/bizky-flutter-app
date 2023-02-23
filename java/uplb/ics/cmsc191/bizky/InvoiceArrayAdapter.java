package uplb.ics.cmsc191.bizky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InvoiceArrayAdapter extends ArrayAdapter<Invoice>{
    public InvoiceArrayAdapter(Context context, ArrayList<Invoice> invoice) {
        super(context, 0, invoice);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Invoice invoice = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_invoice, parent, false);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Lookup view for data population
        TextView tvInvoice = (TextView) convertView.findViewById(R.id.invoice);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.price);
        TextView tvDate = (TextView) convertView.findViewById(R.id.date);
        // Populate the data into the template view using the data object
        tvInvoice.setText(invoice.getName());
        tvPrice.setText("₱"+String.valueOf(invoice.getPrice()));
        tvDate.setText(invoice.getDate());
        // Return the completed view to render on screen
        return convertView;
    }
}
