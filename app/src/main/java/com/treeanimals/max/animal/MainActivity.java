package com.treeanimals.max.animal;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends BaseActivity {
    private BottomNavigationBar bottomNavigation;
    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private UserFragment userFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = (BottomNavigationBar)findViewById(R.id.bottomNavgation);
        bottomNavigation.setMode(BottomNavigationBar.MODE_SHIFTING)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.black);
        bottomNavigation
                .addItem(new BottomNavigationItem(R.drawable.magnifying,"首页"))
                .addItem(new BottomNavigationItem(R.drawable.message,"信息"))
                .addItem(new BottomNavigationItem(R.drawable.user,"我"))
                .setFirstSelectedPosition(0)
                .initialise();
        messageFragment = new MessageFragment();
        userFragment = new UserFragment();
        homeFragment = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        bottomNavigation.setTabSelectedListener(new NavigationListener());
        setDefaultFragment();
    }
    private void setDefaultFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if( homeFragment == null)
            homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.contentPage,homeFragment);
        fragmentTransaction.commit();
    }
    class NavigationListener implements BottomNavigationBar.OnTabSelectedListener{

        @Override
        public void onTabSelected(int position) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch(position){
                case 0:
                    if( homeFragment == null)
                        homeFragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.contentPage,homeFragment);
                    Log.e("nav","0");
                    break;
                case 1:
                    if(messageFragment == null)
                        messageFragment = new MessageFragment();
                    fragmentTransaction.replace(R.id.contentPage,messageFragment);
                    Log.e("nav","1");
                    break;
                case 2:
                    if (userFragment == null)
                        userFragment = new UserFragment();
                    fragmentTransaction.replace(R.id.contentPage,userFragment);
                    Log.e("nav","2");
                    break;
                default:
                    break;
            }
            fragmentTransaction.commit();
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    }
}
