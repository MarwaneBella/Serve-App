package com.example.services;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> usernames;
    ArrayList<String> userImages;
    ArrayList<String> titles;
    ArrayList<String> prices;
    LayoutInflater inflater;

    public ListBaseAdapter(Context ctx,ArrayList<String> usernames, ArrayList<String> userImages, ArrayList<String> titles, ArrayList<String> prices){
        this.context = ctx;
        this.usernames =usernames;
        this.userImages = userImages;
        this.titles = titles;
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
        TextView title = (TextView) convertView.findViewById(R.id.description);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        username.setText(usernames.get(position));
        Glide.with(convertView.getContext()).load(userImages.get(position)).into(avatar);
        title.setText(titles.get(position));
        price.setText(prices.get(position));
        return convertView;
    }
}
