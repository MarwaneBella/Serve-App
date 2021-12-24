package com.example.services;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListBaseAdapter extends BaseAdapter {
    Context context;
    String[] usernames;
    int[] userImages;
    String[] descriptions;
    float[] prices;
    LayoutInflater inflater;

    public ListBaseAdapter(Context ctx,String[] usernames, int[] userImages, String[] descriptions, float[] prices){
        this.context = ctx;
        this.usernames =usernames;
        this.userImages = userImages;
        this.descriptions = descriptions;
        this.prices = prices;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return usernames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_view,null);
        TextView username = (TextView) convertView.findViewById(R.id.username);
        CircleImageView avatar = (CircleImageView) convertView.findViewById(R.id.avatar);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        username.setText(usernames[position]);
        avatar.setImageResource(userImages[position]);
        description.setText(descriptions[position]);
        price.setText(Float.toString(prices[position]));
        return convertView;
    }
}
