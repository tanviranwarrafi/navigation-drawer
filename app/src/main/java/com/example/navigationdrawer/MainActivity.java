package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;

    private int currentFragment = -1;
    private static final int HOME_FRAGMENT = 0;
    private static final int CATEGORY_FRAGMENT = 1;
    private static final int WISHLIST_FRAGMENT = 2;
    private static final int SETTINGS_FRAGMENT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = findViewById(R.id.register_frameLayout);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        setFragment(new HomeFragment(), HOME_FRAGMENT);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_home) {
            invalidateOptionsMenu();
            gotoFragment("Home",new HomeFragment(), HOME_FRAGMENT);
        } else if(id == R.id.nav_category){
            gotoFragment("Category",new CategoryFragment(), CATEGORY_FRAGMENT);
        } else if(id == R.id.nav_wishlist){
            gotoFragment("WishList",new WishlistFragment(), WISHLIST_FRAGMENT);
        } else if(id == R.id.nav_settings){
            gotoFragment("Settings",new SettingsFragment(), SETTINGS_FRAGMENT);
        } else if(id == R.id.nav_signOut){
            Toast.makeText(this, "This feature is not available", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);

    }

    private void setFragment(Fragment fragment, int fragmentNo) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();
        }
    }

