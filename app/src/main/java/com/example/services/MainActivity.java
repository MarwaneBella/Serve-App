package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    MeowBottomNavigation bottomNavigation;
    public Integer idFragment;



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
                Intent intentt = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(intentt);
                return true;
            default:
                return false;
        }
    }
}