package com.colobus.dndplayercompanion.ui.character;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
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
import com.colobus.dndplayercompanion.ProficiencySpinnerAdapter;
import com.colobus.dndplayercompanion.R;
import com.colobus.dndplayercompanion.Race;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class AddCharacterFragment extends Fragment {
    private CharacterViewModel characterViewModel;
    Spinner spinnerRace, spinnerClass, spinnerBackground, spinnerAlignment;
    EditText editCharacterName, editAc, editSpeed, editHp, editXp;
    EditText editStr, editDex, editCon, editInt, editWis, editCha;

    //saving throws
    CheckBox checkSaveStr, checkSaveDex, checkSaveCon;
    CheckBox checkSaveInt, checkSaveWis, checkSaveCha;

    //skills
    Spinner spinnerSkillAcr, spinnerSkillAni, spinnerSkillArc, spinnerSkillAth;
    Spinner spinnerSkillDec, spinnerSkillHis, spinnerSkillInt, spinnerSkillIns;
    Spinner spinnerSkillInv, spinnerSkillMed, spinnerSkillNat, spinnerSkillPrc;
    Spinner spinnerSkillPrf, spinnerSkillPrs, spinnerSkillRel;
    Spinner spinnerSkillSle, spinnerSkillSte, spinnerSkillSur;

    ProficiencySpinnerAdapter adapter;

    Button btnSave;

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
        getViews(root);

        adapter = new ProficiencySpinnerAdapter(getActivity(), R.layout.spinner_item,
                getResources().getStringArray(R.array.proficiency_spinner),
                getResources().getStringArray(R.array.proficiency_spinner_short));

        spinnerSkillAcr.setAdapter(adapter);
        spinnerSkillAni.setAdapter(adapter);
        spinnerSkillArc.setAdapter(adapter);
        spinnerSkillAth.setAdapter(adapter);
        spinnerSkillDec.setAdapter(adapter);
        spinnerSkillHis.setAdapter(adapter);
        spinnerSkillIns.setAdapter(adapter);
        spinnerSkillInt.setAdapter(adapter);
        spinnerSkillInv.setAdapter(adapter);
        spinnerSkillMed.setAdapter(adapter);
        spinnerSkillNat.setAdapter(adapter);
        spinnerSkillPrc.setAdapter(adapter);
        spinnerSkillPrf.setAdapter(adapter);
        spinnerSkillPrs.setAdapter(adapter);
        spinnerSkillRel.setAdapter(adapter);
        spinnerSkillSle.setAdapter(adapter);
        spinnerSkillSte.setAdapter(adapter);
        spinnerSkillSur.setAdapter(adapter);


        btnSave = root.findViewById(R.id.button_save);

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
                saveCharacter();
            }
        });

        return root;
    }

    private void getViews(View root) {

        spinnerRace = root.findViewById(R.id.spinner_race);
        spinnerClass = root.findViewById(R.id.spinner_class);
        spinnerBackground = root.findViewById(R.id.spinner_background);
        spinnerAlignment = root.findViewById(R.id.spinner_alignment);
        editCharacterName = root.findViewById(R.id.edit_char_name);
        editAc = root.findViewById(R.id.edit_ac);
        editSpeed = root.findViewById(R.id.edit_speed);
        editHp = root.findViewById(R.id.edit_hp);
        editXp = root.findViewById(R.id.edit_xp);
        editStr = root.findViewById(R.id.edit_str);
        editDex = root.findViewById(R.id.edit_dex);
        editCon = root.findViewById(R.id.edit_con);
        editInt = root.findViewById(R.id.edit_int);
        editWis = root.findViewById(R.id.edit_wis);
        editCha = root.findViewById(R.id.edit_cha);

        //saving throws
        checkSaveStr = root.findViewById(R.id.save_str);
        checkSaveDex = root.findViewById(R.id.save_dex);
        checkSaveCon = root.findViewById(R.id.save_con);
        checkSaveInt = root.findViewById(R.id.save_int);
        checkSaveWis = root.findViewById(R.id.save_wis);
        checkSaveCha = root.findViewById(R.id.save_cha);

        //skills
        spinnerSkillAcr = root.findViewById(R.id.skill_acr);
        spinnerSkillAni = root.findViewById(R.id.skill_ani);
        spinnerSkillArc = root.findViewById(R.id.skill_arc);
        spinnerSkillAth = root.findViewById(R.id.skill_ath);
        spinnerSkillDec = root.findViewById(R.id.skill_dec);
        spinnerSkillHis = root.findViewById(R.id.skill_his);
        spinnerSkillIns = root.findViewById(R.id.skill_ins);
        spinnerSkillInt = root.findViewById(R.id.skill_int);
        spinnerSkillInv = root.findViewById(R.id.skill_inv);
        spinnerSkillMed = root.findViewById(R.id.skill_med);
        spinnerSkillNat = root.findViewById(R.id.skill_nat);
        spinnerSkillPrc = root.findViewById(R.id.skill_prc);
        spinnerSkillPrf = root.findViewById(R.id.skill_prf);
        spinnerSkillPrs = root.findViewById(R.id.skill_prs);
        spinnerSkillRel = root.findViewById(R.id.skill_rel);
        spinnerSkillSle = root.findViewById(R.id.skill_sle);
        spinnerSkillSte = root.findViewById(R.id.skill_ste);
        spinnerSkillSur = root.findViewById(R.id.skill_sur);
    }

    private double getSkillProfMultiplier(String profType) {
        double multiplier;
        switch (profType) {
            case "Proficient":
                multiplier = 1;
                break;
            case "Half Proficient":
                multiplier = 0.5;
                break;
            case "Expertise":
                multiplier = 2;
                break;
            default:
                multiplier = 0;
        }
        return multiplier;
    }

    private int getSaveProfMultiplier(boolean isChecked) {
        if (isChecked) {
            return 1;
        } else {
            return 0;
        }
    }

    private void saveCharacter() {
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
        } else {
            hp = Integer.parseInt(editHp.getText().toString());
        }
        if (editStr.getText().toString().isEmpty()) {
            strength = 10;
        } else {
            strength = Integer.parseInt(editStr.getText().toString());
        }
        if (editDex.getText().toString().isEmpty()) {
            dexterity = 10;
        } else {
            dexterity = Integer.parseInt(editDex.getText().toString());
        }
        if (editCon.getText().toString().isEmpty()) {
            constitution = 10;
        } else {
            constitution = Integer.parseInt(editCon.getText().toString());
        }
        if (editInt.getText().toString().isEmpty()) {
            intelligence = 10;
        } else {
            intelligence = Integer.parseInt(editInt.getText().toString());
        }
        if (editWis.getText().toString().isEmpty()) {
            wisdom = 10;
        } else {
            wisdom = Integer.parseInt(editWis.getText().toString());
        }
        if (editCha.getText().toString().isEmpty()) {
            charisma = 10;
        } else {
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
            Proficiencies proficiencies = buildProficiency(character_id);
            characterViewModel.insertProficiencies(proficiencies);
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putLong("CURRENT_CHARACTER_ID", character_id);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();

    }

    private Proficiencies buildProficiency(long character_id) {
        return new Proficiencies(character_id,
                getSaveProfMultiplier(checkSaveStr.isChecked()),
                getSaveProfMultiplier(checkSaveDex.isChecked()),
                getSaveProfMultiplier(checkSaveCon.isChecked()),
                getSaveProfMultiplier(checkSaveInt.isChecked()),
                getSaveProfMultiplier(checkSaveWis.isChecked()),
                getSaveProfMultiplier(checkSaveCha.isChecked()),
                getSkillProfMultiplier(spinnerSkillAcr.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillAni.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillArc.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillAth.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillDec.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillHis.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillIns.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillInt.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillInv.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillMed.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillNat.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillPrc.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillPrf.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillPrs.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillRel.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillSle.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillSte.getSelectedItem().toString()),
                getSkillProfMultiplier(spinnerSkillSur.getSelectedItem().toString()));
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
