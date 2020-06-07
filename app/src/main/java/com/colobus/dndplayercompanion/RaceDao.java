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
public interface RaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Race race);

    @Update
    void update(Race race);

    @Delete
    void delete(Race race);

    @Query("SELECT * FROM race_table order by id")
    LiveData<List<Race>> getAllRaces();

    @Query("SELECT * FROM race_table WHERE id = :id")
    LiveData<Race> getRaceById(int id);

}
