package com.colobus.dndplayercompanion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BackgroundDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Background background);

    @Update
    void update(Background background);

    @Delete
    void delete(Background background);

    @Query("SELECT * FROM background_table order by id")
    LiveData<List<Background>> getAllBackgrounds();

    @Query("SELECT * FROM background_table WHERE id = :id")
    LiveData<Background> getBackgroundById(int id);


}
