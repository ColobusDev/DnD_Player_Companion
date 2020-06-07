package com.colobus.dndplayercompanion;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "race_table")
public class Race {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String race;

    @ColumnInfo(name = "strength_bonus")
    private int strengthBonus;

    @ColumnInfo(name = "dexterity_bonus")
    private int dexterityBonus;

    @ColumnInfo(name = "constitution_bonus")
    private int constitutionBonus;

    @ColumnInfo(name = "intelligence_bonus")
    private int intelligenceBonus;

    @ColumnInfo(name = "wisdom_bonus")
    private int wisdomBonus;

    @ColumnInfo(name = "charisma_bonus")
    private int charismaBonus;

    public Race(String race, int strengthBonus, int dexterityBonus, int constitutionBonus,
                int intelligenceBonus, int wisdomBonus, int charismaBonus) {
        this.race = race;
        this.strengthBonus = strengthBonus;
        this.dexterityBonus = dexterityBonus;
        this.constitutionBonus = constitutionBonus;
        this.intelligenceBonus = intelligenceBonus;
        this.wisdomBonus = wisdomBonus;
        this.charismaBonus = charismaBonus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getRace() {
        return race;
    }

    public int getStrengthBonus() {
        return strengthBonus;
    }

    public int getDexterityBonus() {
        return dexterityBonus;
    }

    public int getConstitutionBonus() {
        return constitutionBonus;
    }

    public int getIntelligenceBonus() {
        return intelligenceBonus;
    }

    public int getWisdomBonus() {
        return wisdomBonus;
    }

    public int getCharismaBonus() {
        return charismaBonus;
    }

    @Override
    public String toString() {
        return race;
    }
}
