package com.colobus.dndplayercompanion.ui.character;

import android.media.tv.TvContentRating;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colobus.dndplayercompanion.Character;
import com.colobus.dndplayercompanion.CharacterDao;
import com.colobus.dndplayercompanion.R;


public class ViewCharacterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String CHARACTER_ID = "character_id";
    private CharacterViewModel characterViewModel;


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

        // Main stats
        final TextView valClass = root.findViewById(R.id.val_class);
        final TextView valRace = root.findViewById(R.id.val_race);
        final TextView valAlignment = root.findViewById(R.id.val_alignment);
        final TextView valBackground = root.findViewById(R.id.val_background);
        final TextView valXp = root.findViewById(R.id.val_xp);
        final TextView valProfBonus = root.findViewById(R.id.val_prof_bonus);
        final TextView valAc = root.findViewById(R.id.val_ac);
        final TextView valSpeed = root.findViewById(R.id.val_speed);
        final TextView valPassPerc = root.findViewById(R.id.val_pass_perc);
        final TextView valInitiative = root.findViewById(R.id.val_initiative);
        final TextView valHp = root.findViewById(R.id.val_hp);
        final TextView valHitDie = root.findViewById(R.id.val_hit_die);

        // Abilities
        final TextView modStr = root.findViewById(R.id.mod_str);
        final TextView modDex = root.findViewById(R.id.mod_dex);
        final TextView modCon = root.findViewById(R.id.mod_con);
        final TextView modInt = root.findViewById(R.id.mod_int);
        final TextView modWis = root.findViewById(R.id.mod_wis);
        final TextView modCha = root.findViewById(R.id.mod_cha);
        final TextView scoreStr = root.findViewById(R.id.score_str);
        final TextView scoreDex = root.findViewById(R.id.score_dex);
        final TextView scoreCon = root.findViewById(R.id.score_con);
        final TextView scoreInt = root.findViewById(R.id.score_int);
        final TextView scoreWis = root.findViewById(R.id.score_wis);
        final TextView scoreCha = root.findViewById(R.id.score_cha);
        
        // Saving throws
        final TextView saveStr = root.findViewById(R.id.save_str);
        final TextView saveDex = root.findViewById(R.id.save_dex);
        final TextView saveCon = root.findViewById(R.id.save_con);
        final TextView saveInt = root.findViewById(R.id.save_int);
        final TextView saveWis = root.findViewById(R.id.save_wis);
        final TextView saveCha = root.findViewById(R.id.save_cha);

        // Skills
        final TextView skillAcr = root.findViewById(R.id.skill_acr);
        final TextView skillAni = root.findViewById(R.id.skill_ani);
        final TextView skillArc = root.findViewById(R.id.skill_arc);
        final TextView skillAth = root.findViewById(R.id.skill_ath);
        final TextView skillDec = root.findViewById(R.id.skill_dec);
        final TextView skillIns = root.findViewById(R.id.skill_ins);
        final TextView skillInt = root.findViewById(R.id.skill_int);
        final TextView skillInv = root.findViewById(R.id.skill_inv);
        final TextView skillHis = root.findViewById(R.id.skill_his);
        final TextView skillMed = root.findViewById(R.id.skill_med);
        final TextView skillNat = root.findViewById(R.id.skill_nat);
        final TextView skillPrc = root.findViewById(R.id.skill_prc);
        final TextView skillPrf = root.findViewById(R.id.skill_prf);
        final TextView skillPrs = root.findViewById(R.id.skill_prs);
        final TextView skillRel = root.findViewById(R.id.skill_rel);
        final TextView skillSle = root.findViewById(R.id.skill_sle);
        final TextView skillSte = root.findViewById(R.id.skill_ste);
        final TextView skillSur = root.findViewById(R.id.skill_sur);
        

        characterViewModel.getFullCharacterDetailById(character_id).observe(getViewLifecycleOwner(), new Observer<CharacterDao.FullCharacterDetail>() {
            @Override
            public void onChanged(CharacterDao.FullCharacterDetail character) {
                int level = character.getLevelFromXp(character.getXp());
                getActivity().setTitle(getString(R.string.character_page_title,character.getCharName(),level,character.getClassName()));
                // main stats
                valClass.setText(character.getClassName());
                valRace.setText(character.getRaceName());
                valBackground.setText(character.getBackgroundName());
                valAlignment.setText(character.getAlignmentName());
                valXp.setText(String.valueOf(character.getXp()));
                valProfBonus.setText(String.valueOf(character.getProfBonusFromLevel(level)));
                valAc.setText(String.valueOf(character.getArmour_class()));
                valSpeed.setText(String.valueOf(character.getSpeed()));
                valPassPerc.setText(String.valueOf(10 + character.getModifier("prc_skill")));
                valInitiative.setText(String.valueOf(character.getDEX()));
                valHp.setText(getString(R.string.hp,character.getCurrent_HP(),character.getMax_HP()));
                valHitDie.setText(getString(R.string.hit_die,character.getNum_hit_dice(),character.getHitDiceType()));

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
}
