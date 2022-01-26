package com.example.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.bumptech.glide.Glide;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    MeowBottomNavigation bottomNavigation;
    public Integer idFragment;
    CircleImageView imgProfile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigation = findViewById(R.id.button_navigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_listview));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_edit_list));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_add));



        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment=null;

                switch (item.getId()){
                    case 1:
                        fragment = new ListViewFragment();
                        break;
                    case 2:
                        fragment = new EditListFragment();
                        break;
                    case 3:
                        fragment = new AddServiceFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        try {
            Intent i = getIntent();
            idFragment =Integer.parseInt(i.getStringExtra("id"));

        } catch (Exception e){}

        if(idFragment==null){
            bottomNavigation.show(1,true);
        }
        else{
            bottomNavigation.show(idFragment,true);
        }



        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

                idFragment =item.getId();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });

        CircleImageView ProfileImg = findViewById(R.id.imgProfile);

        ProfileImg.setOnClickListener(v -> {
                PopupMenu popup = new PopupMenu(this, v);
                popup.setOnMenuItemClickListener(this);
                popup.inflate(R.menu.popup_menu_profile);
                popup.show();
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent1 = new Intent(MainActivity.this, Profile.class);
                intent1.putExtra("id",idFragment+"");
                MainActivity.this.startActivity(intent1);
                return true;
            case R.id.item2:
                FirebaseAuth.getInstance().signOut();
                MainActivity.this.startActivity(new Intent(MainActivity.this, Login.class));
                return true;
            default:
                return false;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        imgProfile = findViewById(R.id.imgProfile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference().child("Users");
        String UserID=user.getUid();
        dbreference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    if(snapshot.hasChild("uimage")) {
                        Glide.with(getApplicationContext()).load(snapshot.child("uimage").getValue().toString()).into(imgProfile);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}