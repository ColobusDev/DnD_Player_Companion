package com.colobus.dndplayercompanion;

import android.os.Bundle;
import android.view.MenuItem;

import com.colobus.dndplayercompanion.ui.character.CharactersFragment;
import com.colobus.dndplayercompanion.ui.home.HomeFragment;
import com.colobus.dndplayercompanion.ui.dice.DiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
//        NavDestination current = NavHostFragment.findNavController(getSupportFragmentManager().getFragments().get(0)).getCurrentDestination();


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
                fragment = new DiceFragment();
                fragmentTag = "DICE";
                break;
        }

        return loadFragment(fragment, fragmentTag);
    }

    private boolean loadFragment(Fragment fragment, String fragmentTag) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment, fragmentTag)
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
