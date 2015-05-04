package com.aman.dotmic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aman.dotmic.POJO.StoreItem;
import com.aman.dotmic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * Adapter for grid showing all the items.
 */
public class GridAdapter extends ArrayAdapter<StoreItem> {

    List<StoreItem> items;

    public GridAdapter(Context context, List<StoreItem> items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.items = items;
    }

    private class ViewHolder {
        public TextView title, store, price;
        public ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) v.findViewById(R.id.textView_title);
            viewHolder.store = (TextView) v.findViewById(R.id.textView_store);
            viewHolder.price = (TextView) v.findViewById(R.id.textView_price);
            viewHolder.image = (ImageView) v.findViewById(R.id.view_image);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.title.setText(items.get(position).title);
        viewHolder.store.setText(items.get(position).store);
        viewHolder.price.setText("Rs "+String.valueOf(items.get(position).price));
        String url = items.get(position).image;
        Picasso picasso = Picasso.with(this.getContext());
        picasso.setIndicatorsEnabled(false);
        picasso.load(url).placeholder(R.drawable.placeholder)
                .priority(Picasso.Priority.LOW)
                .into(viewHolder.image);
        return v;
    }

    @Override
    public int getCount() {
        return (items == null) ? 0 : items.size();
    }
}
