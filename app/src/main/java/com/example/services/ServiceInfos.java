package com.example.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServiceInfos extends AppCompatActivity {

    CircleImageView avatar;
    TextView tvUsername, tvCategory,tvTitle,tvLocation,tvPrice,tvDescription,tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_infos);
        ///back button
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceInfos.this, MainActivity.class);
            intent.putExtra("id",1+"");
            this.startActivity(intent);

        });

        //load service info

        Intent i = getIntent();
        String key = i.getStringExtra("key");

        avatar = findViewById(R.id.avatar);
        tvUsername = findViewById(R.id.tvUsername);
        tvCategory = findViewById(R.id.tvCategory);
        tvTitle = findViewById(R.id.tvTitle);
        tvLocation = findViewById(R.id.tvLocation);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        tvPhone = findViewById(R.id.tvPhone);

        DatabaseReference keyServiceRef = FirebaseDatabase.getInstance().getReference().child("Services");
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        keyServiceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot childSnapshot :datasnapshot.getChildren()){
                    for(DataSnapshot snapshot :childSnapshot.getChildren()){
                        if(snapshot.getKey().equals(key)){
                            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    User user = snapshot.getValue(User.class);
                                    Glide.with(getApplicationContext()).load(user.getUimage()).into(avatar);
                                    tvUsername.setText(user.getUsername());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            Service service = snapshot.getValue(Service.class);
                            tvCategory.setText(service.getCategory());
                            tvTitle.setText(service.getTitleService());
                            tvLocation.setText(service.getLocation());
                            tvPrice.setText(service.getPrice());
                            tvDescription.setText(service.getDescription());
                            tvPhone.setText("+212"+service.getPhone());
                            return;
                        }

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}