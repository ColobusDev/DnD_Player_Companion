package com.colobus.dndplayercompanion;

import android.os.Bundle;
import android.view.MenuItem;

import com.colobus.dndplayercompanion.ui.character.CharactersFragment;
import com.colobus.dndplayercompanion.ui.home.HomeFragment;
import com.colobus.dndplayercompanion.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment(), "HOME");
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String fragmentTag = "";

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                fragmentTag = "HOME";
                break;
            case R.id.navigation_character:
                fragment = new CharactersFragment();
                fragmentTag = "CHARACTERS";
                break;
            case R.id.navigation_dice:
                fragment = new NotificationsFragment();
                fragmentTag = "DICE";
                break;
        }

        return loadFragment(fragment, fragmentTag);
    }

    private boolean loadFragment(Fragment fragment, String fragmentTag) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment,fragment, fragmentTag)
                    .commit();
            return true;
        }
        return false;
    }

    public void navigateToFragment(Fragment fragment, String fragmentTag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();
    }
}
