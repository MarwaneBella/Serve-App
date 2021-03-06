package com.example.services;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditListFragment newInstance(String param1, String param2) {
        EditListFragment fragment = new EditListFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_list, container, false);


        /////////
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> userImages = new ArrayList<>();
        ArrayList<String> keyServices =new ArrayList<>();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference servicesRef = rootRef.child("Services").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        servicesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot :datasnapshot.getChildren()){


                    usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot s) {
                            User user = s.getValue(User.class);
                            usernames.add(user.getUsername());
                            userImages.add(user.getUimage());


                            keyServices.add(snapshot.getKey());
                            Service service = snapshot.getValue(Service.class);
                            titles.add(service.getTitleService());
                            prices.add(service.getPrice() +" DH");


                            ListView listView = rootView.findViewById(R.id.list_item);
                            ListBaseAdapter Adapter = new ListBaseAdapter(rootView.getContext(), usernames, userImages, titles, prices);
                            listView.setAdapter(Adapter);
                            listView.setOnItemClickListener((parent, view, position, id) -> {
                                Intent intent = new Intent(getContext(), EditMyService.class);
                                intent.putExtra("key", keyServices.get(position));
                                startActivity(intent);
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////////////


        return rootView;
    }
}