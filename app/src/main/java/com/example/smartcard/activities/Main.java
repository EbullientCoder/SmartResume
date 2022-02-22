package com.example.smartcard.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartcard.MenuUtil;
import com.example.smartcard.R;
import com.example.smartcard.adapters.MenuAdapter;
import com.example.smartcard.fragments.CVFragment;
import com.example.smartcard.fragments.HomeFragment;
import com.example.smartcard.fragments.PortfolioFragment;
import com.example.smartcard.fragments.TeamFragment;
import com.example.smartcard.interfaces.MenuCallback;
import com.example.smartcard.model.MenuModel;
import com.example.smartcard.model.UserModel;

import java.util.List;

public class Main extends AppCompatActivity implements MenuCallback {
    private ImageView profilePhoto;
    private RecyclerView menuRv;
    private List<MenuModel> menuItems;
    private MenuAdapter adapter;
    private int selectedMenuPos = 0 ;

    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get User
        user = (UserModel) getIntent().getExtras().getSerializable("User");

        Bundle bundle = new Bundle();
        bundle.putSerializable("User", user);

        //Setup the Side Menu
        setupSideMenu();

        //Set Home by Default
        setHomeFragment();
    }


    private void setupSideMenu() {
        //Profile Photo Click
        profilePhoto = findViewById(R.id.img_profile);
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });

        //Find Menu Recycler View
        menuRv = findViewById(R.id.rv_side_menu);

        //Get Menu Items
        menuItems = MenuUtil.getMenuList();
        adapter = new MenuAdapter(menuItems,this);
        menuRv.setLayoutManager(new LinearLayoutManager(this));
        menuRv.setAdapter(adapter);
    }

    void setPortfolioFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new PortfolioFragment(user)).commit();
    }

    void setTeamFragment () {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new TeamFragment(user)).commit();
    }

    void setCVFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CVFragment(user)).commit();
    }

    void setHomeFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment(user)).commit();
    }

    void openProfileActivity(){
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }


    @Override
    public void onSideMenuItemClick(int i) {

        //Menu Item Click
        switch (menuItems.get(i).getCode()) {

            case MenuUtil.HOME_FRAGMENT_CODE : setHomeFragment();
                break;
            case MenuUtil.CV_FRAGMENT_CODE : setCVFragment();
                break;
            case MenuUtil.TEAM_FRAGMENT_CODE: setTeamFragment();
                break;
            case MenuUtil.PORTFOLIO_FRAGMENT_CODE:
                setPortfolioFragment();
                break;
            default: setHomeFragment();
        }

        //HighLight the Selected Menu Item
        menuItems.get(selectedMenuPos).setSelected(false);
        menuItems.get(i).setSelected(true);
        selectedMenuPos = i ;
        adapter.notifyDataSetChanged();
    }
}