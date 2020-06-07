package com.colobus.dndplayercompanion.ui.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colobus.dndplayercompanion.Character;
import com.colobus.dndplayercompanion.CharacterAdapter;
import com.colobus.dndplayercompanion.CharacterDao;
import com.colobus.dndplayercompanion.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CharactersFragment extends Fragment {
    private CharacterViewModel characterViewModel;
    private FragmentManager fragmentManager;
    public static final int ADD_CHARACTER_REQUEST = 1;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_character, container, false);

        getActivity().setTitle("Characters");
        FloatingActionButton fab = root.findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAddCharacterFragment();
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final CharacterAdapter adapter = new CharacterAdapter();
        recyclerView.setAdapter(adapter);

        characterViewModel = new ViewModelProvider(getActivity()).get(CharacterViewModel.class);
        characterViewModel.getAllBasicCharacterDetails().observe(getViewLifecycleOwner(), new Observer<List<CharacterDao.BasicCharacterDetail>>() {
            @Override
            public void onChanged(List<CharacterDao.BasicCharacterDetail> characters) {
                adapter.setCharacters(characters);
            }
        });
        characterViewModel.getAllCharacters().observe(getViewLifecycleOwner(), new Observer<List<Character>>() {
            @Override
            public void onChanged(List<Character> characters) {

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int character_id = adapter.getBasicCharacterDetailAt(viewHolder.getAdapterPosition()).getId();
                characterViewModel.deleteCharacter(character_id);
                Toast.makeText(getContext(), "Character deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new CharacterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CharacterDao.BasicCharacterDetail basicCharacterDetail) {
                int character_id = basicCharacterDetail.getId();
                launchViewCharacterFragment(character_id);
            }
        });

        return root;
    }

    private void launchAddCharacterFragment() {
        Fragment newFragment = new AddCharacterFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), newFragment)
                .addToBackStack(null)
                .commit();
    }

    private void launchViewCharacterFragment(int character_id) {
        Fragment fragment = ViewCharacterFragment.newInstance(character_id);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}
