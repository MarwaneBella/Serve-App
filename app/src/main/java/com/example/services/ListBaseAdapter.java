package com.example.services;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> usernames;
    ArrayList<Integer> userImages;
    ArrayList<String> descriptions;
    ArrayList<String> prices;
    LayoutInflater inflater;

    public ListBaseAdapter(Context ctx,ArrayList<String> usernames, ArrayList<Integer> userImages, ArrayList<String> descriptions, ArrayList<String> prices){
        this.context = ctx;
        this.usernames =usernames;
        this.userImages = userImages;
        this.descriptions = descriptions;
        this.prices = prices;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return usernames.size();
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
        username.setText(usernames.get(position));
        avatar.setImageResource(userImages.get(position));
        description.setText(descriptions.get(position));
        price.setText(prices.get(position));
        return convertView;
    }
}
