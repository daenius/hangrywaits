package com.boolong.hangrywaits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dennizhu on 3/30/15.
 */
public class HomeListAdapter extends ArrayAdapter<HomeListItem> {
    private final Context context;
    private final List<HomeListItem> itemsArrayList;

    public HomeListAdapter(Context context, int resource, List<HomeListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.itemsArrayList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.home_list_item, parent, false);

        TextView restaurantName = (TextView) rowView.findViewById(R.id.restaurant_name);
        TextView waitTime = (TextView) rowView.findViewById(R.id.wait_time);

        restaurantName.setText(itemsArrayList.get(position).getRestaurantName());
        waitTime.setText("" + itemsArrayList.get(position).getWaitTime());

        return rowView;
    }
}
