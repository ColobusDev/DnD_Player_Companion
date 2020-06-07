package com.colobus.dndplayercompanion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface ProficiencyDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Proficiencies proficiencies);

    @Update
    void update(Proficiencies proficiencies);

    @Delete
    void delete(Proficiencies proficiencies);

}
