package com.colobus.dndplayercompanion.ui.character;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.colobus.dndplayercompanion.Alignment;
import com.colobus.dndplayercompanion.Background;
import com.colobus.dndplayercompanion.CharClass;
import com.colobus.dndplayercompanion.Character;
import com.colobus.dndplayercompanion.CharacterDao;
import com.colobus.dndplayercompanion.MainActivity;
import com.colobus.dndplayercompanion.Proficiencies;
import com.colobus.dndplayercompanion.R;
import com.colobus.dndplayercompanion.Race;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class AddCharacterFragment extends Fragment {
    private CharacterViewModel characterViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        characterViewModel = new ViewModelProvider(getActivity()).get(CharacterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_character, container, false);
        getActivity().setTitle("Add Character");
        final Spinner spinnerRace = root.findViewById(R.id.spinner_race);
        final Spinner spinnerClass = root.findViewById(R.id.spinner_class);
        final Spinner spinnerBackground = root.findViewById(R.id.spinner_background);
        final Spinner spinnerAlignment = root.findViewById(R.id.spinner_alignment);
        final EditText editCharacterName = root.findViewById(R.id.edit_char_name);
        final EditText editAc = root.findViewById(R.id.edit_ac);
        final EditText editSpeed = root.findViewById(R.id.edit_speed);
        final EditText editHp = root.findViewById(R.id.edit_hp);
        final EditText editXp = root.findViewById(R.id.edit_xp);
        final EditText editStr = root.findViewById(R.id.edit_str);
        final EditText editDex = root.findViewById(R.id.edit_dex);
        final EditText editCon = root.findViewById(R.id.edit_con);
        final EditText editInt = root.findViewById(R.id.edit_int);
        final EditText editWis = root.findViewById(R.id.edit_wis);
        final EditText editCha = root.findViewById(R.id.edit_cha);
        Button btnSave = root.findViewById(R.id.button_save);

        characterViewModel.getAllRaces().observe(getViewLifecycleOwner(), new Observer<List<Race>>() {
            @Override
            public void onChanged(List<Race> races) {
                ArrayAdapter<Race> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, races);
                spinnerRace.setAdapter(adapter);
            }
        });
        characterViewModel.getAllCharClasses().observe(getViewLifecycleOwner(), new Observer<List<CharClass>>() {
            @Override
            public void onChanged(List<CharClass> charClasses) {
                ArrayAdapter<CharClass> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, charClasses);
                spinnerClass.setAdapter(adapter);
            }
        });
        characterViewModel.getAllAlignments().observe(getViewLifecycleOwner(), new Observer<List<Alignment>>() {
            @Override
            public void onChanged(List<Alignment> alignments) {
                ArrayAdapter<Alignment> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, alignments);
                spinnerAlignment.setAdapter(adapter);
            }
        });
        characterViewModel.getAllBackgrounds().observe(getViewLifecycleOwner(), new Observer<List<Background>>() {
            @Override
            public void onChanged(List<Background> backgrounds) {
                ArrayAdapter<Background> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, backgrounds);
                spinnerBackground.setAdapter(adapter);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String characterName = editCharacterName.getText().toString();
                int raceSelectedItemPosition = spinnerRace.getSelectedItemPosition() + 1;
                int classSelectedItemPosition = spinnerClass.getSelectedItemPosition() + 1;
                int backgroundSelectedItemPosition = spinnerBackground.getSelectedItemPosition() + 1;
                int alignmentSelectedItemPosition = spinnerAlignment.getSelectedItemPosition() + 1;
                int ac;
                int speed;
                int xp;
                int hp;
                int strength;
                int dexterity;
                int constitution;
                int intelligence;
                int wisdom;
                int charisma;
                if (editAc.getText().toString().isEmpty()) {
                    ac = 10;
                } else {
                    ac = Integer.parseInt(editAc.getText().toString());
                }
                if (editSpeed.getText().toString().isEmpty()) {
                    speed = 30;
                } else {
                    speed = Integer.parseInt(editSpeed.getText().toString());
                }
                if (editXp.getText().toString().isEmpty()) {
                    xp = 0;
                } else {
                    xp = Integer.parseInt(editXp.getText().toString());
                }
                if (editHp.getText().toString().isEmpty()) {
                    hp = 10;
                } else{
                    hp = Integer.parseInt(editHp.getText().toString());
                }
                if (editStr.getText().toString().isEmpty()) {
                    strength = 10;
                } else{
                    strength = Integer.parseInt(editStr.getText().toString());
                }
                if (editDex.getText().toString().isEmpty()) {
                    dexterity = 10;
                } else{
                    dexterity = Integer.parseInt(editDex.getText().toString());
                }
                if (editCon.getText().toString().isEmpty()) {
                    constitution = 10;
                } else{
                    constitution = Integer.parseInt(editCon.getText().toString());
                }
                if (editInt.getText().toString().isEmpty()) {
                    intelligence = 10;
                } else{
                    intelligence = Integer.parseInt(editInt.getText().toString());
                }
                if (editWis.getText().toString().isEmpty()) {
                    wisdom = 10;
                } else{
                    wisdom = Integer.parseInt(editWis.getText().toString());
                }
                if (editCha.getText().toString().isEmpty()) {
                    charisma = 10;
                } else{
                    charisma = Integer.parseInt(editCha.getText().toString());
                }
                if (characterName.trim().isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a character name", Toast.LENGTH_SHORT).show();
                    return;
                }
                CharacterDao.BasicCharacterDetail basicCharacterDetail = new CharacterDao.BasicCharacterDetail(0, characterName, xp, spinnerClass.getSelectedItem().toString(), spinnerRace.getSelectedItem().toString());
                int level = basicCharacterDetail.getLevelFromXp(xp);
                Character character = new Character(characterName, raceSelectedItemPosition, classSelectedItemPosition,
                        backgroundSelectedItemPosition, alignmentSelectedItemPosition,
                        strength, dexterity, constitution, intelligence, wisdom, charisma,
                        hp, hp, ac, xp, speed, level);

                long character_id;
                try {
                    character_id = characterViewModel.insertCharacter(character);
                    Proficiencies proficiencies = new Proficiencies(character_id,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
                    characterViewModel.insertProficiencies(proficiencies);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return root;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        MenuInflater menuInflater = getActivity().getMenuInflater();
//        menuInflater.inflate(R.menu.add_character_menu, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.save_character:
//                saveCharacter();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    public void saveCharacter() {
//
//
//    }


}
