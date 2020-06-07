package com.colobus.dndplayercompanion;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alignment_table")
public class Alignment {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "short_name")
    private String shortName;

    @ColumnInfo(name = "name")
    private String name;

    public Alignment(String shortName, String name) {
        this.shortName = shortName;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return shortName;
    }
}
