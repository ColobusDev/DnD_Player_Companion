package com.colobus.dndplayercompanion.ui.character;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.colobus.dndplayercompanion.Alignment;
import com.colobus.dndplayercompanion.Background;
import com.colobus.dndplayercompanion.CharClass;
import com.colobus.dndplayercompanion.Character;
import com.colobus.dndplayercompanion.CharacterDao;
import com.colobus.dndplayercompanion.CharacterRepository;
import com.colobus.dndplayercompanion.Proficiencies;
import com.colobus.dndplayercompanion.Race;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {
    private CharacterRepository repository;
    private LiveData<List<Character>> allCharacters;
    private LiveData<List<CharClass>> allClasses;
    private LiveData<List<Race>> allRaces;
    private LiveData<List<Background>> allBackgrounds;
    private LiveData<List<Alignment>> allAlignments;
    private LiveData<List<CharacterDao.BasicCharacterDetail>> allBasicCharacterDetails;


    public CharacterViewModel(@NonNull Application application) {
        super(application);
        repository = new CharacterRepository(application);
        allCharacters = repository.getAllCharacters();
        allClasses = repository.getAllClasses();
        allRaces = repository.getAllRaces();
        allBackgrounds = repository.getAllBackgrounds();
        allAlignments = repository.getAllAlignments();
        allBasicCharacterDetails = repository.getAllBasicCharDetails();
    }

    public LiveData<List<CharacterDao.BasicCharacterDetail>> getAllBasicCharacterDetails() {
        return allBasicCharacterDetails;
    }

    public long insertCharacter(Character character) throws Exception {
        return repository.insertCharacter(character);
    }

    public void updateCharacter(Character character) {
        repository.updateCharacter(character);
    }

    public void deleteCharacter(long characterId) {
        repository.deleteCharacter(characterId);
    }

    public void updateCharacterHp(long character_id, int numHitDice, int newHp) {
        repository.updateCharacterHp(character_id, numHitDice, newHp);
    }

    public LiveData<List<Character>> getAllCharacters() {
        return allCharacters;
    }

    public void insertCharClass(CharClass charClass) {
        repository.insertClass(charClass);
    }

    public void updateCharClass(CharClass charClass) {
        repository.updateClass(charClass);
    }

    public void deleteCharClass(CharClass charClass) {
        repository.deleteClass(charClass);
    }

    public LiveData<List<CharClass>> getAllCharClasses() {
        return allClasses;
    }

    public void insertRace(Race race) {
        repository.insertRace(race);
    }

    public void updateRace(Race race) {
        repository.updateRace(race);
    }

    public void deleteRace(Race race) {
        repository.deleteRace(race);
    }

    public LiveData<List<Race>> getAllRaces() {
        return allRaces;
    }

    public void insertBackground(Background background) {
        repository.insertBackground(background);
    }

    public void updateBackground(Background background) {
        repository.updateBackground(background);
    }

    public void deleteBackground(Background background) {
        repository.deleteBackground(background);
    }

    public LiveData<List<Background>> getAllBackgrounds() {
        return allBackgrounds;
    }

    public void insertAlignment(Alignment alignment) {
        repository.insertAlignment(alignment);
    }

    public void updateAlignment(Alignment alignment) {
        repository.updateAlignment(alignment);
    }

    public void deleteAlignment(Alignment alignment) {
        repository.deleteAlignment(alignment);
    }

    public LiveData<List<Alignment>> getAllAlignments() {
        return allAlignments;
    }

    public void insertProficiencies(Proficiencies proficiencies) {
        repository.insertProficiencies(proficiencies);
    }

    public void updateProficiencies(Proficiencies proficiencies) {
        repository.updateProficiencies(proficiencies);
    }

    public void deleteProficiencies(Proficiencies proficiencies) {
        repository.deleteProficiencies(proficiencies);
    }

    public LiveData<Character> getCharacterById(long id) {
        return repository.getCharacterById(id);
    }

    public LiveData<CharacterDao.FullCharacterDetail> getFullCharacterDetailById(long id) {
        return repository.getFullCharacterDetailById(id);
    }

    public LiveData<CharacterDao.CharacterDetailsForEdit> getCharacterDetailsForEdit(long character_id) {
        return repository.getCharacterDetailsForEdit(character_id);
    }

    public LiveData<Proficiencies> getProfFromCharacterId(long character_id) {
        return repository.getProfFromCharacterId(character_id);
    }

    public LiveData<Race> getRaceById(int race_id) {
        return repository.getRaceById(race_id);
    }

    public LiveData<CharClass> getClassById(int class_id) {
        return repository.getClassById(class_id);
    }

    public LiveData<Alignment> getAlignmentById(int alignment_id) {
        return repository.getAlignmentById(alignment_id);
    }

    public LiveData<Background> getBackgroundById(int background_id) {
        return repository.getBackgroundById(background_id);
    }

}
