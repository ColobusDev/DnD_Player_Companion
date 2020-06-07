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
public interface AlignmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Alignment alignment);

    @Update
    void update(Alignment alignment);

    @Delete
    void delete(Alignment alignment);

    @Query("SELECT * FROM alignment_table order by id")
    LiveData<List<Alignment>> getAllAlignments();

    @Query("SELECT * FROM alignment_table WHERE id = :id")
    LiveData<Alignment> getAlignmentById(int id);

}
