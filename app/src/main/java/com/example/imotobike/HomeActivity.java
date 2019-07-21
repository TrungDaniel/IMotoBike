package com.example.imotobike;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.imotobike.Util.Appconfig;
import com.example.imotobike.adapter.HomePagerAdapter;
import com.example.imotobike.fragment.TatCaFragment;
import com.example.imotobike.fragment.ThuongXuyenFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tlHome;
    ViewPager vpHome;
    ArrayList<Fragment> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //--------
        init();
        setDataNavigation();
        configVP();
    }


    private void configVP() {
        TatCaFragment tatCaFragment = new TatCaFragment();
        ThuongXuyenFragment thuongXuyenFragment = new ThuongXuyenFragment();
        data.add(tatCaFragment);
        data.add(thuongXuyenFragment);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), this, data);
        vpHome.setAdapter(adapter);
        tlHome.setupWithViewPager(vpHome);


    }

    private void setDataNavigation() {
        // lấy id của view trong navigation
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView tenUser = (TextView) headerView.findViewById(R.id.tv_ten);
        TextView phoneUser = (TextView) headerView.findViewById(R.id.tv_so_dien_thoai);
        TextView biensoUser = (TextView) headerView.findViewById(R.id.tv_bien_so);
        //---
        String strPhoneUser = Appconfig.getPhoneNumber(HomeActivity.this);
        String strTenUser = Appconfig.getTenUser(HomeActivity.this);
        String strBiensoUser = Appconfig.getBiensoUser(HomeActivity.this);
        tenUser.setText(strTenUser);
        phoneUser.setText(strPhoneUser);
        biensoUser.setText(strBiensoUser);
        //------
        Toast.makeText(this, "Xin chào " + strTenUser, Toast.LENGTH_SHORT).show();


    }

    private void init() {
        tlHome = findViewById(R.id.tb_home);
        vpHome = findViewById(R.id.vp_home);


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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_phu_tung: {
                break;
            }
            case R.id.nav_dang_xuat: {
                Appconfig.logout(this);
                Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
