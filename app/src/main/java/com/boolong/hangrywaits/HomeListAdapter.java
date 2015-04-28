package com.boolong.hangrywaits;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.boolong.hangrywaits.dataprovider.DataProvider;

import java.util.List;

/**
 * Created by dennizhu on 3/30/15.
 */
public class HomeListAdapter extends ArrayAdapter<Business> {
    private final Context context;
    private final List<Business> itemsArrayList;

    public HomeListAdapter(Context context, int resource, List<Business> objects) {
        super(context, resource, objects);
        this.context = context;
        this.itemsArrayList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.home_list_item, parent, false);

        final TextView restaurantName = (TextView) rowView.findViewById(R.id.restaurant_name);
        final TextView waitTime = (TextView) rowView.findViewById(R.id.wait_time);
        final TextView address = (TextView) rowView.findViewById(R.id.address);
        final TextView phoneNumber = (TextView) rowView.findViewById(R.id.phone_number);
        final ToggleButton isFavorite = (ToggleButton) rowView.findViewById(R.id.isFavorite);

        restaurantName.setText(itemsArrayList.get(position).getRestaurantName());
        phoneNumber.setText("Call: " + itemsArrayList.get(position).getPhone());
        waitTime.setText("" + itemsArrayList.get(position).getWaitTime());
        address.setText(itemsArrayList.get(position).getAddress());
        isFavorite.setChecked(itemsArrayList.get(position).isFavorite());

        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
                context.startActivity(callIntent);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open google map
            }
        });

        isFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ToggleButton)v).isChecked()){
                    DataProvider provider = DataProvider.getProvider(context);
                    provider.addFavorite(itemsArrayList.get(position));
                }
            }
        });

        return rowView;
    }
}
