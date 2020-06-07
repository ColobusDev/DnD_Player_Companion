package com.colobus.dndplayercompanion;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CharacterRepository {
    private CharacterDao characterDao;
    private ClassDao classDao;
    private RaceDao raceDao;
    private AlignmentDao alignmentDao;
    private BackgroundDao backgroundDao;
    private LiveData<List<Character>> allCharacters;
    private LiveData<List<CharClass>> allClasses;
    private LiveData<List<Race>> allRaces;
    private LiveData<List<Background>> allBackgrounds;
    private LiveData<List<Alignment>> allAlignments;
    private LiveData<List<CharacterDao.BasicCharacterDetail>> allBasicCharDetails;


    public CharacterRepository(Application application) {
        CharacterDatabase db = CharacterDatabase.getInstance(application);
        characterDao = db.characterDao();
        raceDao = db.raceDao();
        classDao = db.classDao();
        alignmentDao = db.alignmentDao();
        backgroundDao = db.backgroundDao();
        allCharacters = characterDao.getAllCharacters();
        allClasses = classDao.getAllClasses();
        allRaces = raceDao.getAllRaces();
        allBackgrounds = backgroundDao.getAllBackgrounds();
        allAlignments = alignmentDao.getAllAlignments();
        allBasicCharDetails = characterDao.getAllBasicCharacterDetails();
    }

    public LiveData<List<CharacterDao.BasicCharacterDetail>> getAllBasicCharDetails() {
        return allBasicCharDetails;
    }

    // character insert, update etc
    public void insertCharacter(Character character) {
        new InsertCharacterAsyncTask(characterDao).execute(character);
    }

    public void updateCharacter(Character character) {
        new UpdateCharacterAsyncTask(characterDao).execute(character);
    }

    public void deleteCharacter(int characterId) {
        new DeleteCharacterAsyncTask(characterDao).execute(characterId);
    }

    public LiveData<List<Character>> getAllCharacters() {
        return allCharacters;
    }

    // class insert, update etc
    public void insertClass(CharClass charClass) {
        new InsertClassAsyncTask(classDao).execute(charClass);
    }

    public void updateClass(CharClass charClass) {
        new UpdateClassAsyncTask(classDao).execute(charClass);
    }

    public void deleteClass(CharClass charClass) {
        new DeleteClassAsyncTask(classDao).execute(charClass);
    }

    public LiveData<List<CharClass>> getAllClasses() {
        return allClasses;
    }

    // race insert, update etc
    public void insertRace(Race race) {
        new InsertRaceAsyncTask(raceDao).execute(race);
    }

    public void updateRace(Race race) {
        new UpdateRaceAsyncTask(raceDao).execute(race);
    }

    public void deleteRace(Race race) {
        new DeleteRaceAsyncTask(raceDao).execute(race);
    }

    public LiveData<List<Race>> getAllRaces() {
        return allRaces;
    }

    // alignment insert, update etc
    public void insertAlignment(Alignment alignment) {
        new InsertAlignmentAsyncTask(alignmentDao).execute(alignment);
    }

    public void updateAlignment(Alignment alignment) {
        new UpdateAlignmentAsyncTask(alignmentDao).execute(alignment);
    }

    public void deleteAlignment(Alignment alignment) {
        new DeleteAlignmentAsyncTask(alignmentDao).execute(alignment);
    }

    public LiveData<List<Alignment>> getAllAlignments() {
        return allAlignments;
    }

    // background insert, update etc
    public void insertBackground(Background background) {
        new InsertBackgroundAsyncTask(backgroundDao).execute(background);
    }

    public void updateBackground(Background background) {
        new UpdateBackgroundAsyncTask(backgroundDao).execute(background);
    }

    public void deleteBackground(Background background) {
        new DeleteBackgroundAsyncTask(backgroundDao).execute(background);
    }

    public LiveData<List<Background>> getAllBackgrounds() {
        return allBackgrounds;
    }

    public LiveData<Character> getCharacterById(int id) {
        return characterDao.getCharacterById(id);
    }

    public LiveData<CharacterDao.FullCharacterDetail> getFullCharacterDetailById(int id) {
        return characterDao.getFullCharacterDetailById(id);
    }

    public LiveData<Race> getRaceById(int race_id) {
        return raceDao.getRaceById(race_id);
    }

    public LiveData<CharClass> getClassById(int class_id) {
        return classDao.getClassById(class_id);
    }

    public LiveData<Alignment> getAlignmentById(int alignment_id) {
        return alignmentDao.getAlignmentById(alignment_id);
    }

    public LiveData<Background> getBackgroundById(int background_id) {
        return backgroundDao.getBackgroundById(background_id);
    }



    private static class InsertCharacterAsyncTask extends AsyncTask<Character, Void, Void> {
        private CharacterDao characterDao;

        private InsertCharacterAsyncTask(CharacterDao characterDao) {
            this.characterDao = characterDao;
        }

        @Override
        protected Void doInBackground(Character... characters) {
            characterDao.insert(characters[0]);
            return null;
        }
    }

    private static class UpdateCharacterAsyncTask extends AsyncTask<Character, Void, Void> {
        private CharacterDao characterDao;

        private UpdateCharacterAsyncTask(CharacterDao characterDao) {
            this.characterDao = characterDao;
        }

        @Override
        protected Void doInBackground(Character... characters) {
            characterDao.update(characters[0]);
            return null;
        }
    }

    private static class DeleteCharacterAsyncTask extends AsyncTask<Integer, Void, Void> {
        private CharacterDao characterDao;

        private DeleteCharacterAsyncTask(CharacterDao characterDao) {
            this.characterDao = characterDao;
        }

        @Override
        protected Void doInBackground(Integer... ints) {
            characterDao.delete(ints[0]);
            return null;
        }
    }

    private static class InsertClassAsyncTask extends AsyncTask<CharClass, Void, Void> {
        private ClassDao classDao;

        private InsertClassAsyncTask(ClassDao classDao) {
            this.classDao = classDao;
        }

        @Override
        protected Void doInBackground(CharClass... charClasses) {
            classDao.insert(charClasses[0]);
            return null;
        }
    }

    private static class UpdateClassAsyncTask extends AsyncTask<CharClass, Void, Void> {
        private ClassDao classDao;

        private UpdateClassAsyncTask(ClassDao classDao) {
            this.classDao = classDao;
        }

        @Override
        protected Void doInBackground(CharClass... charClasses) {
            classDao.update(charClasses[0]);
            return null;
        }
    }

    private static class DeleteClassAsyncTask extends AsyncTask<CharClass, Void, Void> {
        private ClassDao classDao;

        private DeleteClassAsyncTask(ClassDao classDao) {
            this.classDao = classDao;
        }

        @Override
        protected Void doInBackground(CharClass... charClasses) {
            classDao.delete(charClasses[0]);
            return null;
        }
    }

    private static class InsertRaceAsyncTask extends AsyncTask<Race, Void, Void> {
        private RaceDao raceDao;

        private InsertRaceAsyncTask(RaceDao raceDao) {
            this.raceDao = raceDao;
        }

        @Override
        protected Void doInBackground(Race... races) {
            raceDao.insert(races[0]);
            return null;
        }
    }

    private static class UpdateRaceAsyncTask extends AsyncTask<Race, Void, Void> {
        private RaceDao raceDao;

        private UpdateRaceAsyncTask(RaceDao raceDao) {
            this.raceDao = raceDao;
        }

        @Override
        protected Void doInBackground(Race... races) {
            raceDao.update(races[0]);
            return null;
        }
    }

    private static class DeleteRaceAsyncTask extends AsyncTask<Race, Void, Void> {
        private RaceDao raceDao;

        private DeleteRaceAsyncTask(RaceDao raceDao) {
            this.raceDao = raceDao;
        }

        @Override
        protected Void doInBackground(Race... races) {
            raceDao.delete(races[0]);
            return null;
        }
    }


    private static class InsertAlignmentAsyncTask extends AsyncTask<Alignment, Void, Void> {
        private AlignmentDao alignmentDao;

        private InsertAlignmentAsyncTask(AlignmentDao alignmentDao) {
            this.alignmentDao = alignmentDao;
        }

        @Override
        protected Void doInBackground(Alignment... alignments) {
            alignmentDao.insert(alignments[0]);
            return null;
        }
    }

    private static class UpdateAlignmentAsyncTask extends AsyncTask<Alignment, Void, Void> {
        private AlignmentDao alignmentDao;

        private UpdateAlignmentAsyncTask(AlignmentDao alignmentDao) {
            this.alignmentDao = alignmentDao;
        }

        @Override
        protected Void doInBackground(Alignment... alignments) {
            alignmentDao.update(alignments[0]);
            return null;
        }
    }

    private static class DeleteAlignmentAsyncTask extends AsyncTask<Alignment, Void, Void> {
        private AlignmentDao alignmentDao;

        private DeleteAlignmentAsyncTask(AlignmentDao alignmentDao) {
            this.alignmentDao = alignmentDao;
        }

        @Override
        protected Void doInBackground(Alignment... alignments) {
            alignmentDao.delete(alignments[0]);
            return null;
        }
    }


    private static class InsertBackgroundAsyncTask extends AsyncTask<Background, Void, Void> {
        private BackgroundDao backgroundDao;

        private InsertBackgroundAsyncTask(BackgroundDao backgroundDao) {
            this.backgroundDao = backgroundDao;
        }

        @Override
        protected Void doInBackground(Background... backgrounds) {
            backgroundDao.insert(backgrounds[0]);
            return null;
        }
    }

    private static class UpdateBackgroundAsyncTask extends AsyncTask<Background, Void, Void> {
        private BackgroundDao backgroundDao;

        private UpdateBackgroundAsyncTask(BackgroundDao backgroundDao) {
            this.backgroundDao = backgroundDao;
        }

        @Override
        protected Void doInBackground(Background... backgrounds) {
            backgroundDao.update(backgrounds[0]);
            return null;
        }
    }

    private static class DeleteBackgroundAsyncTask extends AsyncTask<Background, Void, Void> {
        private BackgroundDao backgroundDao;

        private DeleteBackgroundAsyncTask(BackgroundDao backgroundDao) {
            this.backgroundDao = backgroundDao;
        }

        @Override
        protected Void doInBackground(Background... backgrounds) {
            backgroundDao.delete(backgrounds[0]);
            return null;
        }
    }
}