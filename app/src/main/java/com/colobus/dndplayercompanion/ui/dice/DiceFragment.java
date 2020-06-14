package com.colobus.dndplayercompanion.ui.dice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.colobus.dndplayercompanion.R;

import java.util.concurrent.ThreadLocalRandom;

public class DiceFragment extends Fragment implements View.OnClickListener {

    private DiceViewModel diceViewModel;

    private Button btnD20, btnD2, btnD4, btnD6, btnD8, btnD10, btnD12, btnD100;
    private Button btnReset;
    private TextView valD20, valD2, valD4, valD6, valD8, valD10, valD12, valD100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diceViewModel =
                new ViewModelProvider(this).get(DiceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dice, container, false);
        getActivity().setTitle("Dice");
        getViews(root);

        diceViewModel.getD2_value().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                valD2.setText(s);
            }
        });
        diceViewModel.getD4_value().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                valD4.setText(s);
            }
        });
        diceViewModel.getD6_value().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                valD6.setText(s);
            }
        });
        diceViewModel.getD8_value().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                valD8.setText(s);
            }
        });
        diceViewModel.getD10_value().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                valD10.setText(s);
            }
        });
        diceViewModel.getD12_value().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                valD12.setText(s);
            }
        });
        diceViewModel.getD20_value().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                valD20.setText(s);
            }
        });
        diceViewModel.getD100_value().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                valD100.setText(s);
            }
        });

        return root;
    }

    private void getViews(View root) {
        btnD2 = root.findViewById(R.id.d2_roll_btn);
        btnD2.setOnClickListener(this);
        btnD4 = root.findViewById(R.id.d4_roll_btn);
        btnD4.setOnClickListener(this);
        btnD6 = root.findViewById(R.id.d6_roll_btn);
        btnD6.setOnClickListener(this);
        btnD8 = root.findViewById(R.id.d8_roll_btn);
        btnD8.setOnClickListener(this);
        btnD10 = root.findViewById(R.id.d10_roll_btn);
        btnD10.setOnClickListener(this);
        btnD12 = root.findViewById(R.id.d12_roll_btn);
        btnD12.setOnClickListener(this);
        btnD20 = root.findViewById(R.id.d20_roll_btn);
        btnD20.setOnClickListener(this);
        btnD100 = root.findViewById(R.id.d100_roll_btn);
        btnD100.setOnClickListener(this);

        btnReset = root.findViewById(R.id.reset_dice_btn);
        btnReset.setOnClickListener(this);

        valD2 = root.findViewById(R.id.d2_value);
        valD4 = root.findViewById(R.id.d4_value);
        valD6 = root.findViewById(R.id.d6_value);
        valD8 = root.findViewById(R.id.d8_value);
        valD10 = root.findViewById(R.id.d10_value);
        valD12 = root.findViewById(R.id.d12_value);
        valD20 = root.findViewById(R.id.d20_value);
        valD100 = root.findViewById(R.id.d100_value);

    }

    @Override
    public void onClick(View v) {
        int modifier = 0;
        int diceRoll = 0;
        switch (v.getId()) {
            case R.id.d2_roll_btn:
                // roll d2
                diceRoll = rollDice(2, modifier);
                diceViewModel.getD2_value().postValue(String.valueOf(diceRoll));
                break;
            case R.id.d4_roll_btn:
                // roll d4
                diceRoll = rollDice(4, modifier);
                diceViewModel.getD4_value().postValue(String.valueOf(diceRoll));
                break;
            case R.id.d6_roll_btn:
                // roll d6
                diceRoll = rollDice(6, modifier);
                diceViewModel.getD6_value().postValue(String.valueOf(diceRoll));
                break;
            case R.id.d8_roll_btn:
                // roll d8
                diceRoll = rollDice(8, modifier);
                diceViewModel.getD8_value().postValue(String.valueOf(diceRoll));
                break;
            case R.id.d10_roll_btn:
                // roll d10
                diceRoll = rollDice(10, modifier);
                diceViewModel.getD10_value().postValue(String.valueOf(diceRoll));
                break;
            case R.id.d12_roll_btn:
                // roll d12
                diceRoll = rollDice(12, modifier);
                diceViewModel.getD12_value().postValue(String.valueOf(diceRoll));
                break;
            case R.id.d20_roll_btn:
                // roll d20
                diceRoll = rollDice(20, modifier);
                diceViewModel.getD20_value().postValue(String.valueOf(diceRoll));
                break;
            case R.id.d100_roll_btn:
                // roll d100
                diceRoll = rollDice(100, modifier);
                diceViewModel.getD100_value().postValue(String.valueOf(diceRoll));
                break;
            case R.id.reset_dice_btn:
                valD2.setText("0");
                valD4.setText("0");
                valD6.setText("0");
                valD8.setText("0");
                valD10.setText("0");
                valD12.setText("0");
                valD20.setText("0");
                valD100.setText("0");
            default:
                break;
        }
    }

    private int rollDice(int dice, int modifier) {
        return ThreadLocalRandom.current().nextInt(1, dice + 1) + modifier;
    }
}
