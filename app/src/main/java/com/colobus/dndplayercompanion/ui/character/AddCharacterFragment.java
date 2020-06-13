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
    private static final String CHARACTER_ID = "character_id";
    private long character_id;
    private int profId;

    private CharacterViewModel characterViewModel;
    Spinner spinnerRace, spinnerClass, spinnerBackground, spinnerAlignment;
    ArrayAdapter<Race> raceArrayAdapter;
    ArrayAdapter<CharClass> charClassArrayAdapter;
    ArrayAdapter<Background> backgroundArrayAdapter;
    ArrayAdapter<Alignment> alignmentArrayAdapter;
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

    ProficiencySpinnerAdapter proficiencySpinnerAdapter;


    public static AddCharacterFragment newInstance(long character_id) {
        AddCharacterFragment fragment = new AddCharacterFragment();
        Bundle args = new Bundle();
        args.putLong(CHARACTER_ID, character_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            character_id = getArguments().getLong(CHARACTER_ID);
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        characterViewModel = new ViewModelProvider(getActivity()).get(CharacterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_character, container, false);
        getActivity().setTitle("Add Character");
        getViews(root);

        proficiencySpinnerAdapter = new ProficiencySpinnerAdapter(getActivity(), R.layout.spinner_item,
                getResources().getStringArray(R.array.proficiency_spinner),
                getResources().getStringArray(R.array.proficiency_spinner_short));

        spinnerSkillAcr.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillAni.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillArc.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillAth.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillDec.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillHis.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillIns.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillInt.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillInv.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillMed.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillNat.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillPrc.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillPrf.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillPrs.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillRel.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillSle.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillSte.setAdapter(proficiencySpinnerAdapter);
        spinnerSkillSur.setAdapter(proficiencySpinnerAdapter);


        characterViewModel.getAllRaces().observe(getViewLifecycleOwner(), new Observer<List<Race>>() {
            @Override
            public void onChanged(List<Race> races) {
                raceArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, races);
                spinnerRace.setAdapter(raceArrayAdapter);
            }
        });
        characterViewModel.getAllCharClasses().observe(getViewLifecycleOwner(), new Observer<List<CharClass>>() {
            @Override
            public void onChanged(final List<CharClass> charClasses) {
                charClassArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, charClasses);
                spinnerClass.setAdapter(charClassArrayAdapter);
                spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        CharClass selectedClass = charClasses.get(position);
                        tickProfsForSelectedClass(selectedClass);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
        characterViewModel.getAllAlignments().observe(getViewLifecycleOwner(), new Observer<List<Alignment>>() {
            @Override
            public void onChanged(List<Alignment> alignments) {
                alignmentArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, alignments);
                spinnerAlignment.setAdapter(alignmentArrayAdapter);
            }
        });
        characterViewModel.getAllBackgrounds().observe(getViewLifecycleOwner(), new Observer<List<Background>>() {
            @Override
            public void onChanged(List<Background> backgrounds) {
                backgroundArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, backgrounds);
                spinnerBackground.setAdapter(backgroundArrayAdapter);
            }
        });

        if (character_id > 0) {
            loadCharacterDetails();
        }

        return root;
    }

    private void tickProfsForSelectedClass(CharClass selectedClass) {
        String saveProfs = selectedClass.getSaveProficiencies();
        checkSaveStr.setChecked(false);
        checkSaveDex.setChecked(false);
        checkSaveCon.setChecked(false);
        checkSaveInt.setChecked(false);
        checkSaveWis.setChecked(false);
        checkSaveCha.setChecked(false);
        if (saveProfs.contains("STR")) {
            checkSaveStr.setChecked(true);
        }
        if (saveProfs.contains("DEX")) {
            checkSaveDex.setChecked(true);
        }
        if (saveProfs.contains("CON")) {
            checkSaveCon.setChecked(true);
        }
        if (saveProfs.contains("INT")) {
            checkSaveInt.setChecked(true);
        }
        if (saveProfs.contains("WIS")) {
            checkSaveWis.setChecked(true);
        }
        if (saveProfs.contains("CHA")) {
            checkSaveCha.setChecked(true);
        }
    }

    private void loadCharacterDetails() {
        characterViewModel.getCharacterDetailsForEdit(character_id).observe(getViewLifecycleOwner(), new Observer<CharacterDao.CharacterDetailsForEdit>() {
            @Override
            public void onChanged(CharacterDao.CharacterDetailsForEdit characterDetails) {
                // mains stats & details
                editCharacterName.setText(characterDetails.getCharName());
                spinnerAlignment.setSelection(characterDetails.getAlignment_id() - 1);
                spinnerBackground.setSelection(characterDetails.getBackground_id() - 1);
                spinnerClass.setSelection(characterDetails.getClass_id() - 1);
                spinnerRace.setSelection(characterDetails.getRace_id() - 1);
                editAc.setText(String.valueOf(characterDetails.getArmour_class()));
                editHp.setText(String.valueOf(characterDetails.getMax_HP()));
                editXp.setText(String.valueOf(characterDetails.getXp()));
                editSpeed.setText(String.valueOf(characterDetails.getSpeed()));

                //abilities
                editStr.setText(String.valueOf(characterDetails.getSTR()));
                editDex.setText(String.valueOf(characterDetails.getDEX()));
                editCon.setText(String.valueOf(characterDetails.getCON()));
                editInt.setText(String.valueOf(characterDetails.getINT()));
                editWis.setText(String.valueOf(characterDetails.getWIS()));
                editCha.setText(String.valueOf(characterDetails.getCHA()));

                // saving throw proficiencies
                checkSaveStr.setChecked(characterDetails.getSave_proficiency_str() == 1);
                checkSaveDex.setChecked(characterDetails.getSave_proficiency_dex() == 1);
                checkSaveCon.setChecked(characterDetails.getSave_proficiency_con() == 1);
                checkSaveInt.setChecked(characterDetails.getSave_proficiency_int() == 1);
                checkSaveWis.setChecked(characterDetails.getSave_proficiency_wis() == 1);
                checkSaveCha.setChecked(characterDetails.getSave_proficiency_cha() == 1);

                // skill proficiencies
                spinnerSkillAcr.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_acr()));
                spinnerSkillAni.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_ani()));
                spinnerSkillArc.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_arc()));
                spinnerSkillAth.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_ath()));
                spinnerSkillDec.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_dec()));
                spinnerSkillHis.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_his()));
                spinnerSkillIns.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_ins()));
                spinnerSkillInt.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_int()));
                spinnerSkillInv.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_inv()));
                spinnerSkillMed.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_med()));
                spinnerSkillNat.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_nat()));
                spinnerSkillPrc.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_prc()));
                spinnerSkillPrf.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_prf()));
                spinnerSkillPrs.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_prs()));
                spinnerSkillRel.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_rel()));
                spinnerSkillSle.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_sle()));
                spinnerSkillSte.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_ste()));
                spinnerSkillSur.setSelection(getSkillProfSpinnerIndex(characterDetails.getSkill_proficiency_sur()));

                profId = characterDetails.getProficiency_id();
            }
        });
    }

    private int getSkillProfSpinnerIndex(double multiplier) {
        if (multiplier < 0.5) {
            return 0;
        } else if (multiplier < 1) {
            return 2;
        } else if (multiplier < 2) {
            return 1;
        } else {
            return 3;
        }
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
            case "Half proficient":
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

        long newCharacterId;
        if (character_id > 0) {
            character.setId(character_id);
            characterViewModel.updateCharacter(character);
            Proficiencies proficiencies = buildProficiency(character_id);
            proficiencies.setId(profId);
            characterViewModel.updateProficiencies(proficiencies);
        } else {
            try {
                newCharacterId = characterViewModel.insertCharacter(character);
                Proficiencies proficiencies = buildProficiency(newCharacterId);
                characterViewModel.insertProficiencies(proficiencies);
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putLong("CURRENT_CHARACTER_ID", newCharacterId);
                editor.apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.add_character_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_character) {
            saveCharacter();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//
//    public void saveCharacter() {
//
//
//    }


}
