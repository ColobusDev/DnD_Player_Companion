package com.colobus.dndplayercompanion.ui.character;

import android.media.tv.TvContentRating;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.colobus.dndplayercompanion.Character;
import com.colobus.dndplayercompanion.CharacterDao;
import com.colobus.dndplayercompanion.MainActivity;
import com.colobus.dndplayercompanion.R;

import org.jetbrains.annotations.NotNull;


public class ViewCharacterFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    private static final String CHARACTER_ID = "character_id";
    private CharacterViewModel characterViewModel;

    // Main stats
    TextView valClass, valRace, valAlignment, valBackground, valXp;
    TextView valProfBonus, valAc, valSpeed, valPassPrc, valInitiative;
    TextView valHp, valHitDie;

    // Abilities
    TextView modStr, modDex, modCon, modInt, modWis, modCha;
    TextView scoreStr, scoreDex, scoreCon, scoreInt, scoreWis, scoreCha;

    // Saving throws
    TextView saveStr, saveDex, saveCon, saveInt, saveWis, saveCha;

    // Skills
    TextView skillAcr, skillAni, skillArc, skillAth, skillDec, skillIns;
    TextView skillInt, skillInv, skillHis, skillMed, skillNat, skillPrc;
    TextView skillPrf, skillPrs, skillRel, skillSle, skillSte, skillSur;

    Button btnHeal, btnDamage, btnShortRest, btnLongRest;
    EditText editHp;
    private int currentHp, maxHp, numHitDice, level;


    // TODO: Rename and change types of parameters
    private long character_id;

    public ViewCharacterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param character_id Parameter 1.
     * @return A new instance of fragment ViewCharacterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewCharacterFragment newInstance(long character_id) {
        ViewCharacterFragment fragment = new ViewCharacterFragment();
        Bundle args = new Bundle();
        args.putLong(CHARACTER_ID, character_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            character_id = getArguments().getLong(CHARACTER_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        characterViewModel = new ViewModelProvider(getActivity()).get(CharacterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_character, container, false);
        getViews(root);

        characterViewModel.getFullCharacterDetailById(character_id).observe(getViewLifecycleOwner(), new Observer<CharacterDao.FullCharacterDetail>() {
            @Override
            public void onChanged(CharacterDao.FullCharacterDetail character) {
                level = character.getLevelFromXp(character.getXp());
                getActivity().setTitle(getString(R.string.character_page_title, character.getCharName(), level, character.getClassName()));

                // main stats
                valClass.setText(character.getClassName());
                valRace.setText(character.getRaceName());
                valBackground.setText(character.getBackgroundName());
                valAlignment.setText(character.getAlignmentName());
                valXp.setText(String.valueOf(character.getXp()));
                valProfBonus.setText(String.valueOf(character.getProfBonusFromLevel(level)));
                valAc.setText(String.valueOf(character.getArmour_class()));
                valSpeed.setText(String.valueOf(character.getSpeed()));
                valPassPrc.setText(String.valueOf(10 + character.getModifier("prc_skill")));
                valInitiative.setText(String.valueOf(character.getDEX()));
                currentHp = character.getCurrent_HP();
                maxHp = character.getMax_HP();
                valHp.setText(getString(R.string.hp, currentHp, maxHp));
                numHitDice = character.getNum_hit_dice();
                valHitDie.setText(getString(R.string.hit_die, numHitDice, character.getHitDiceType()));

                // Abilities
                modStr.setText(String.valueOf(character.getModifier("str_base")));
                modDex.setText(String.valueOf(character.getModifier("dex_base")));
                modCon.setText(String.valueOf(character.getModifier("con_base")));
                modInt.setText(String.valueOf(character.getModifier("int_base")));
                modWis.setText(String.valueOf(character.getModifier("wis_base")));
                modCha.setText(String.valueOf(character.getModifier("cha_base")));
                scoreStr.setText(String.valueOf(character.getSTR()));
                scoreDex.setText(String.valueOf(character.getDEX()));
                scoreCon.setText(String.valueOf(character.getCON()));
                scoreInt.setText(String.valueOf(character.getINT()));
                scoreWis.setText(String.valueOf(character.getWIS()));
                scoreCha.setText(String.valueOf(character.getCHA()));

                // Saving throws
                saveStr.setText(String.valueOf(character.getModifier("str_save")));
                saveDex.setText(String.valueOf(character.getModifier("dex_save")));
                saveCon.setText(String.valueOf(character.getModifier("con_save")));
                saveInt.setText(String.valueOf(character.getModifier("int_save")));
                saveWis.setText(String.valueOf(character.getModifier("wis_save")));
                saveCha.setText(String.valueOf(character.getModifier("cha_save")));

                // Skills
                skillAcr.setText(String.valueOf(character.getModifier("acr_skill")));
                skillAni.setText(String.valueOf(character.getModifier("ani_skill")));
                skillArc.setText(String.valueOf(character.getModifier("arc_skill")));
                skillAth.setText(String.valueOf(character.getModifier("ath_skill")));
                skillDec.setText(String.valueOf(character.getModifier("dec_skill")));
                skillIns.setText(String.valueOf(character.getModifier("ins_skill")));
                skillInt.setText(String.valueOf(character.getModifier("int_skill")));
                skillInv.setText(String.valueOf(character.getModifier("inv_skill")));
                skillHis.setText(String.valueOf(character.getModifier("his_skill")));
                skillMed.setText(String.valueOf(character.getModifier("med_skill")));
                skillNat.setText(String.valueOf(character.getModifier("nat_skill")));
                skillPrc.setText(String.valueOf(character.getModifier("prc_skill")));
                skillPrf.setText(String.valueOf(character.getModifier("prf_skill")));
                skillPrs.setText(String.valueOf(character.getModifier("prs_skill")));
                skillRel.setText(String.valueOf(character.getModifier("rel_skill")));
                skillSle.setText(String.valueOf(character.getModifier("sle_skill")));
                skillSte.setText(String.valueOf(character.getModifier("ste_skill")));
                skillSur.setText(String.valueOf(character.getModifier("sur_skill")));


            }
        });
        // Inflate the layout for this fragment
        return root;
    }


    private void getViews(View root) {
        // Main stats
        valClass = root.findViewById(R.id.val_class);
        valRace = root.findViewById(R.id.val_race);
        valAlignment = root.findViewById(R.id.val_alignment);
        valBackground = root.findViewById(R.id.val_background);
        valXp = root.findViewById(R.id.val_xp);
        valProfBonus = root.findViewById(R.id.val_prof_bonus);
        valAc = root.findViewById(R.id.val_ac);
        valSpeed = root.findViewById(R.id.val_speed);
        valPassPrc = root.findViewById(R.id.val_pass_perc);
        valInitiative = root.findViewById(R.id.val_initiative);
        valHp = root.findViewById(R.id.val_hp);
        valHitDie = root.findViewById(R.id.val_hit_die);

        // Abilities
        modStr = root.findViewById(R.id.mod_str);
        modDex = root.findViewById(R.id.mod_dex);
        modCon = root.findViewById(R.id.mod_con);
        modInt = root.findViewById(R.id.mod_int);
        modWis = root.findViewById(R.id.mod_wis);
        modCha = root.findViewById(R.id.mod_cha);
        scoreStr = root.findViewById(R.id.score_str);
        scoreDex = root.findViewById(R.id.score_dex);
        scoreCon = root.findViewById(R.id.score_con);
        scoreInt = root.findViewById(R.id.score_int);
        scoreWis = root.findViewById(R.id.score_wis);
        scoreCha = root.findViewById(R.id.score_cha);

        // Saving throws
        saveStr = root.findViewById(R.id.save_str);
        saveDex = root.findViewById(R.id.save_dex);
        saveCon = root.findViewById(R.id.save_con);
        saveInt = root.findViewById(R.id.save_int);
        saveWis = root.findViewById(R.id.save_wis);
        saveCha = root.findViewById(R.id.save_cha);

        // Skills
        skillAcr = root.findViewById(R.id.skill_acr);
        skillAni = root.findViewById(R.id.skill_ani);
        skillArc = root.findViewById(R.id.skill_arc);
        skillAth = root.findViewById(R.id.skill_ath);
        skillDec = root.findViewById(R.id.skill_dec);
        skillIns = root.findViewById(R.id.skill_ins);
        skillInt = root.findViewById(R.id.skill_int);
        skillInv = root.findViewById(R.id.skill_inv);
        skillHis = root.findViewById(R.id.skill_his);
        skillMed = root.findViewById(R.id.skill_med);
        skillNat = root.findViewById(R.id.skill_nat);
        skillPrc = root.findViewById(R.id.skill_prc);
        skillPrf = root.findViewById(R.id.skill_prf);
        skillPrs = root.findViewById(R.id.skill_prs);
        skillRel = root.findViewById(R.id.skill_rel);
        skillSle = root.findViewById(R.id.skill_sle);
        skillSte = root.findViewById(R.id.skill_ste);
        skillSur = root.findViewById(R.id.skill_sur);

        // buttons
        editHp = root.findViewById(R.id.edit_hp);
        btnDamage = root.findViewById(R.id.btn_damage);
        btnDamage.setOnClickListener(this);
        btnHeal = root.findViewById(R.id.btn_heal);
        btnHeal.setOnClickListener(this);
        btnLongRest = root.findViewById(R.id.btn_long_rest);
        btnLongRest.setOnClickListener(this);
        btnShortRest = root.findViewById(R.id.btn_short_rest);
        btnShortRest.setOnClickListener(this);
    }

    private void editCharacter(long character_id) {
        Fragment fragment = AddCharacterFragment.newInstance(character_id);
        String fragmentTag = "EDIT_CHARACTER";
        ((MainActivity) getActivity()).navigateToFragment(fragment, fragmentTag);
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.view_character_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_character) {
            editCharacter(character_id);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_damage:
                int damageAmount = Integer.parseInt(editHp.getText().toString());
                int newHp = currentHp - damageAmount;
                if (newHp < 1) {
                    newHp = 0;
                } else if (newHp > maxHp) {
                    newHp = maxHp;
                }
                characterViewModel.updateCharacterHp(character_id, numHitDice, newHp);

                Toast.makeText(getActivity(), "Damage taken: " + damageAmount + " HP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_heal:
                Toast.makeText(getActivity(), "Healed: " + 1 + " HP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_short_rest:
                Toast.makeText(getActivity(), "Short rest", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_long_rest:
                Toast.makeText(getActivity(), "Long rest", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
