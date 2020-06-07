package com.colobus.dndplayercompanion;

import androidx.core.content.PermissionChecker;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "class_table")
public class CharClass {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String className;

    @ColumnInfo(name = "hitDiceType")
    private int hitDiceType;

    @ColumnInfo(name = "spellAbility")
    private String spellAbility;

    public CharClass(String className, int hitDiceType, String spellAbility) {
        this.className = className;
        this.hitDiceType = hitDiceType;
        this.spellAbility = spellAbility;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public int getHitDiceType() {
        return hitDiceType;
    }

    public String getSpellAbility() {
        return spellAbility;
    }

    @Override
    public String toString() {
        return className;
    }
}
