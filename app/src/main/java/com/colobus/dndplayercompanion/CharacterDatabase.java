package com.colobus.dndplayercompanion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Character.class, CharClass.class, Race.class, Alignment.class, Background.class, Proficiencies.class},
        version = 2)
public abstract class CharacterDatabase extends RoomDatabase {
    private static volatile CharacterDatabase instance;

    public abstract CharacterDao characterDao();

    public abstract ClassDao classDao();

    public abstract RaceDao raceDao();

    public abstract BackgroundDao backgroundDao();

    public abstract AlignmentDao alignmentDao();

    public abstract ProficiencyDao proficiencyDao();

    public static synchronized CharacterDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CharacterDatabase.class, "character_database")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ClassDao classDao;
        private RaceDao raceDao;
        private BackgroundDao backgroundDao;
        private AlignmentDao alignmentDao;
        private CharacterDao characterDao;
        private ProficiencyDao proficiencyDao;

        private PopulateDbAsyncTask(CharacterDatabase db) {
            raceDao = db.raceDao();
            classDao = db.classDao();
            alignmentDao = db.alignmentDao();
            backgroundDao = db.backgroundDao();
            characterDao = db.characterDao();
            proficiencyDao = db.proficiencyDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            insertStarterAlignments(alignmentDao);
            insertStarterBackgrounds(backgroundDao);
            insertStarterClasses(classDao);
            insertStarterRaces(raceDao);
            characterDao.insert(new Character("Example name", 1,1,1,1,10,10,10,10,10,10,8,8,10,0,30,1));
            proficiencyDao.insert(new Proficiencies(1,0,0,0,0, 0,0,
                    0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0));

            return null;
        }

        private void insertStarterRaces(RaceDao raceDao) {
            raceDao.insert(new Race("Dragonborn", 2, 0, 0, 0, 0, 1));
            raceDao.insert(new Race("Dwarf", 0, 0, 2, 0, 0, 0));
            raceDao.insert(new Race("Elf", 0, 2, 0, 0, 0, 0));
            raceDao.insert(new Race("Gnome", 0, 0, 0, 2, 0, 0));
            raceDao.insert(new Race("Half-Elf", 0, 0, 0, 0, 0, 2));
            raceDao.insert(new Race("Halfling", 0, 2, 0, 0, 0, 0));
            raceDao.insert(new Race("Half-Orc", 2, 0, 1, 0, 0, 0));
            raceDao.insert(new Race("Human", 1, 1, 1, 1, 1, 1));
            raceDao.insert(new Race("Tiefling", 0, 0, 0, 1, 0, 2));
        }

        private void insertStarterClasses(ClassDao classDao) {
            classDao.insert(new CharClass("Barbarian", 12, null));
            classDao.insert(new CharClass("Bard", 8, "Charisma"));
            classDao.insert(new CharClass("Cleric", 8, "Wisdom"));
            classDao.insert(new CharClass("Druid", 8, "Wisdom"));
            classDao.insert(new CharClass("Fighter", 10, null));
            classDao.insert(new CharClass("Monk", 8, "Wisdom"));
            classDao.insert(new CharClass("Paladin", 10, "Charisma"));
            classDao.insert(new CharClass("Ranger", 10, "Wisdom"));
            classDao.insert(new CharClass("Rogue", 8, "Intelligence"));
            classDao.insert(new CharClass("Sorcerer", 6, "Charisma"));
            classDao.insert(new CharClass("Warlock", 8, "Charisma"));
            classDao.insert(new CharClass("Wizard", 6, "Intelligence"));
        }

        private void insertStarterBackgrounds(BackgroundDao backgroundDao) {
            backgroundDao.insert(new Background("Acolyte"));
            backgroundDao.insert(new Background("Charlatan"));
            backgroundDao.insert(new Background("Criminal"));
            backgroundDao.insert(new Background("Entertainer"));
            backgroundDao.insert(new Background("Far Traveler"));
            backgroundDao.insert(new Background("Folk Hero"));
            backgroundDao.insert(new Background("Guild Artisan"));
            backgroundDao.insert(new Background("Hermit"));
            backgroundDao.insert(new Background("Noble"));
            backgroundDao.insert(new Background("Outlander"));
            backgroundDao.insert(new Background("Sage"));
            backgroundDao.insert(new Background("Sailor"));
            backgroundDao.insert(new Background("Soldier"));
            backgroundDao.insert(new Background("Urchin"));

        }

        private void insertStarterAlignments(AlignmentDao alignmentDao) {
            alignmentDao.insert(new Alignment("LG", "Lawful Good"));
            alignmentDao.insert(new Alignment("NG", "Neutral Good"));
            alignmentDao.insert(new Alignment("CG", "Chaotic Good"));
            alignmentDao.insert(new Alignment("LN", "Lawful Neutral"));
            alignmentDao.insert(new Alignment("N", "Neutral"));
            alignmentDao.insert(new Alignment("CN", "Chaotic Neutral"));
            alignmentDao.insert(new Alignment("LE", "Lawful Evil"));
            alignmentDao.insert(new Alignment("NE", "Neutral Evil"));
            alignmentDao.insert(new Alignment("CE", "Chaotic Evil"));

        }
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS 'proficiencies' ('id' INTEGER PRIMARY KEY, " +
                    "'character_id' INTEGER,'save_proficiency_str' INTEGER, 'save_proficiency_dex' INTEGER, " +
                    "'save_proficiency_con' INTEGER, 'save_proficiency_int' INTEGER, " +
                    "'save_proficiency_wis' INTEGER, 'save_proficiency_cha' INTEGER, " +
                    "'skill_proficiency_acr' INTEGER, 'skill_proficiency_ani' INTEGER, " +
                    "'skill_proficiency_arc' INTEGER, 'skill_proficiency_ath' INTEGER, " +
                    "'skill_proficiency_dec' INTEGER, 'skill_proficiency_his' INTEGER, " +
                    "'skill_proficiency_ins' INTEGER, 'skill_proficiency_int' INTEGER, " +
                    "'skill_proficiency_inv' INTEGER, 'skill_proficiency_med' INTEGER, " +
                    "'skill_proficiency_nat' INTEGER, 'skill_proficiency_prc' INTEGER, " +
                    "'skill_proficiency_prf' INTEGER, 'skill_proficiency_prs' INTEGER, " +
                    "'skill_proficiency_rel' INTEGER, 'skill_proficiency_sle' INTEGER, " +
                    "'skill_proficiency_ste' INTEGER, 'skill_proficiency_sur' INTEGER, " +
                    "FOREIGN KEY ('character_id') REFERENCES 'character_table'('id') ON DELETE CASCADE)");
            database.execSQL("INSERT INTO proficiencies SELECT character_id, 0,0,0,0,0,0,0,0,0,0,0,0" +
                    "0,0,0,0,0,0,0,0,0,0,0,0 FROM character_table");
        }
    };

}
