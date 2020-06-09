package com.colobus.dndplayercompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProficiencySpinnerAdapter extends ArrayAdapter<String> {
    Context mContext;
    int mTextViewResourceID;
    String[] mObjects;
    String[] mShortNameObjects;

    public ProficiencySpinnerAdapter(Context context, int textViewResourceID,
                                     String[] objects, String[] shortNameObjects) {
        super(context, textViewResourceID, objects);
        mContext = context;
        mTextViewResourceID = textViewResourceID;
        mObjects = objects;
        mShortNameObjects = shortNameObjects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView textView = (TextView) layoutInflater.inflate(mTextViewResourceID, parent, false);
        textView.setText(mObjects[position]);
        return textView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView textView = (TextView) layoutInflater.inflate(mTextViewResourceID, parent, false);
        textView.setText(mShortNameObjects[position]);
        return textView;
    }
}
