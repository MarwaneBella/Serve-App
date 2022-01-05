package com.example.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<Integer> userImages = new ArrayList<>();

        TextView test1 = findViewById(R.id.test1);
        TextView test2 = findViewById(R.id.test2);
        TextView test3 = findViewById(R.id.test3);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference servicesRef = rootRef.child("Services");


        servicesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot postSnapshot: datasnapshot.getChildren()) {
                        for (DataSnapshot data : postSnapshot.getChildren()) {
                            Service service = data.getValue(Service.class);
                            titles.add(service.getTitleService());


                        }
                }


                test1.setText(titles.get(0));
                test2.setText(titles.get(1));
                test3.setText(titles.get(2));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}