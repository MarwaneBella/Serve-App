package com.example.services;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.SettingInjectorService;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddServiceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddServiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddServiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddServiceFragment newInstance(String param1, String param2) {
        AddServiceFragment fragment = new AddServiceFragment();
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
        View  rootView = inflater.inflate(R.layout.fragment_add_service, container, false);

        ///select category
        AutoCompleteTextView autoCompleteTxt;
        ArrayAdapter<String> adapterItems;

        String[] items =  getResources().getStringArray(R.array.category1);
        autoCompleteTxt = rootView.findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(rootView.getContext(),R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        /// get data
        EditText etTitle = rootView.findViewById(R.id.etTitle);
        EditText etLocation = rootView.findViewById(R.id.etLocation);
        EditText etPrice = rootView.findViewById(R.id.etPrice);
        EditText etDesc = rootView.findViewById(R.id.etDesc);
        EditText etPhone = rootView.findViewById(R.id.etPhone);
        Button post = rootView.findViewById(R.id.post);

        ///send data
        post.setOnClickListener(v->{


            String category = autoCompleteTxt.getText().toString().trim();
            String titleService = etTitle.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String price = etPrice.getText().toString().trim();
            String description = etDesc.getText().toString().trim();
            String phone = "+212"+etPhone.getText().toString().trim();


            if(titleService.isEmpty()){
                etTitle.setError("Title is required!");
                etTitle.requestFocus();
                return;
            }
            if(location.isEmpty()){
                etLocation.setError("Location is required!");
                etLocation.requestFocus();
                return;
            }
            if(price.isEmpty()){
                etPrice.setError("Price is required!");
                etPrice.requestFocus();
                return;
            }
            if(description.isEmpty()){
                etDesc.setError("Description is required!");
                etDesc.requestFocus();
                return;
            }
            if(phone.isEmpty()){
                etPhone.setError("Phone is required!");
                etPhone.requestFocus();
                return;
            }

            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);

                    String username = user.getUsername();
                    String uimage = user.getUimage();

                    Service service = new Service(username,uimage,category,titleService,location,price,description,phone);


                    DatabaseReference servicesRef = FirebaseDatabase.getInstance().getReference("Services");
                    servicesRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(servicesRef.push().getKey())
                            .setValue(service).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(rootView.getContext(), "Successfully!",Toast.LENGTH_LONG).show();

                                autoCompleteTxt.setText("Delivery");
                                etTitle.getText().clear();
                                etLocation.getText().clear();
                                etPrice.getText().clear();
                                etDesc.getText().clear();
                                etPhone.getText().clear();

                            }
                            else {
                                Toast.makeText(rootView.getContext(), "Failed!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        });



        return rootView;
    }




}