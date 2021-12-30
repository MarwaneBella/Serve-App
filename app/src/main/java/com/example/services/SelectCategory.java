package com.example.services;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;



public class SelectCategory {
    private String[] items ;
    private AutoCompleteTextView autoCompleteTxt;
    private ArrayAdapter<String> adapterItems;
    private View rootView;

    public SelectCategory(View rootView,String[] items) {
        this.rootView = rootView;
        this.items = items;
    }

    //for fragment
    public void  setItems(){

        autoCompleteTxt = rootView.findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(rootView.getContext(),R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(rootView.getContext(),"Item: "+item,Toast.LENGTH_SHORT).show();

            }
        });
    }


}
