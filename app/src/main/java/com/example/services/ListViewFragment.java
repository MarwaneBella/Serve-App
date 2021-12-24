package com.example.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListViewFragment newInstance(String param1, String param2) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private String usernames[]={"Marwane bella","Hamza Ezzaki","Robenson John","Bose Steven","Bradbury Mitchell","Bulter Boomer","Chesney Sarah"};
    private int  userImages[]= {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g};
    private String descriptions []= {"aaaaaaaaa","bbbbbbb","ccccccccccc","ddddddddd","eeeeeeeeee","ffffffffff","ggggggggg"};
    private float prices[]={100,200,300,400,500.75F,600,700};
    private ListView listView ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

        //select bar
        String[] items =  getResources().getStringArray(R.array.category2);

        SelectCategory select = new SelectCategory(rootView,items);
        select.setItems();

        //list view
        listView = rootView.findViewById(R.id.list_item);
        ListBaseAdapter Adapter = new ListBaseAdapter(getContext(),usernames,userImages,descriptions,prices);
        listView.setAdapter(Adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), ServiceInfos.class);
            startActivity(intent);
        });

        return rootView;
    }
}