package com.colobus.dndplayercompanion;

import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClassDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CharClass charClass);

    @Update
    void update(CharClass charClass);

    @Delete
    void delete(CharClass charClass);

    @Query("SELECT * FROM class_table order by id")
    LiveData<List<CharClass>> getAllClasses();

    @Query("SELECT * FROM class_table WHERE id = :id")
    LiveData<CharClass> getClassById(int id);

}
