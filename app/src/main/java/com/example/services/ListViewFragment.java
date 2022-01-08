package com.example.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

        AutoCompleteTextView autoCompleteTxt;
        ArrayAdapter<String> adapterItems;

        String[] items =  getResources().getStringArray(R.array.category2);
        autoCompleteTxt = rootView.findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(rootView.getContext(),R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        /* <item>All</item>
        <item>Delivery</item>
        <item>Bill payment</item>
        <item>Grocery shopping</item>
        <item>Other</item>*/
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> userImages = new ArrayList<>();
        ArrayList<String> keyServices =new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference servicesRef = rootRef.child("Services");

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(item.equals("All")){
                    servicesRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                            for(DataSnapshot childSnapshot :datasnapshot.getChildren()){
                                for(DataSnapshot snapshot :childSnapshot.getChildren()){
                                    keyServices.add(snapshot.getKey());
                                    Service service = snapshot.getValue(Service.class);
                                    usernames.add(service.getUsername());
                                    userImages.add(service.getUimage());
                                    titles.add(service.getTitleService());
                                    prices.add(service.getPrice());
                                }
                            }

                            ListView listView = rootView.findViewById(R.id.list_item);
                            ListBaseAdapter Adapter = new ListBaseAdapter(getContext(),usernames,userImages,titles,prices);
                            listView.setAdapter(Adapter);

                            listView.setOnItemClickListener((parent, view, position, id) -> {
                                Intent intent = new Intent(getContext(), ServiceInfos.class);
                                intent.putExtra("key",keyServices.get(position));
                                startActivity(intent);
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

                else{
                    servicesRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                            for(DataSnapshot childSnapshot :datasnapshot.getChildren()){
                                for(DataSnapshot snapshot :childSnapshot.getChildren()){
                                    Service service = snapshot.getValue(Service.class);
                                    if(service.getCategory().equals(item)){
                                        keyServices.add(snapshot.getKey());
                                        usernames.add(service.getUsername());
                                        userImages.add(service.getUimage());
                                        titles.add(service.getTitleService());
                                        prices.add(service.getPrice());
                                    }

                                }
                            }

                            ListView listView = rootView.findViewById(R.id.list_item);
                            ListBaseAdapter Adapter = new ListBaseAdapter(getContext(),usernames,userImages,titles,prices);
                            listView.setAdapter(Adapter);

                            listView.setOnItemClickListener((parent, view, position, id) -> {
                                Intent intent = new Intent(getContext(), ServiceInfos.class);
                                intent.putExtra("key",keyServices.get(position));
                                startActivity(intent);
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                usernames.clear();
                userImages.clear();
                titles.clear();
                prices.clear();
                keyServices.clear();
            }
        });

        return rootView;
    }


}