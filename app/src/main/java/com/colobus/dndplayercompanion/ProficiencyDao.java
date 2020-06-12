package com.colobus.dndplayercompanion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProficiencyDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Proficiencies proficiencies);

    @Update
    void update(Proficiencies proficiencies);

    @Delete
    void delete(Proficiencies proficiencies);

    @Query("SELECT * FROM proficiencies WHERE character_id = :character_id")
    LiveData<Proficiencies> getProfIdFromCharacterId(long character_id);

}
