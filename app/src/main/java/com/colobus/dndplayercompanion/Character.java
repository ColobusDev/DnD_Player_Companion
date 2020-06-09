package com.colobus.dndplayercompanion;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "character_table",
    foreignKeys = {
        @ForeignKey(
                entity = CharClass.class,
                parentColumns = "id",
                childColumns = "class_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(
                entity = Background.class,
                parentColumns = "id",
                childColumns = "background_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(
                entity = Alignment.class,
                parentColumns = "id",
                childColumns = "alignment_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(
                entity = Race.class,
                parentColumns = "id",
                childColumns = "race_id",
                onDelete = ForeignKey.CASCADE)
})
public class Character {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String charName;

    @ColumnInfo(name = "race_id", index = true)
    private int raceId;

    @ColumnInfo(name = "class_id", index = true)
    private int classId;

    @ColumnInfo(name = "background_id", index = true)
    private int backgroundId;

    @ColumnInfo(name = "alignment_id", index = true)
    private int alignmentId;

    @ColumnInfo(name = "STR")
    private int strength;

    @ColumnInfo(name = "DEX")
    private int dexterity;

    @ColumnInfo(name = "CON")
    private int constitution;

    @ColumnInfo(name = "INT")
    private int intelligence;

    @ColumnInfo(name = "WIS")
    private int wisdom;

    @ColumnInfo(name = "CHA")
    private int charisma;

    @ColumnInfo(name = "max_HP")
    private int maxHP;

    @ColumnInfo(name = "current_HP")
    private int currentHP;

    @ColumnInfo(name = "armour_class")
    private int armourClass;

    @ColumnInfo(name = "XP")
    private int currentXP;

    @ColumnInfo(name = "speed")
    private int speed;

    @ColumnInfo(name = "num_hit_dice")
    private int numHitDice;

    public Character(String charName, int raceId, int classId, int backgroundId,
                     int alignmentId, int strength, int dexterity, int constitution,
                     int intelligence, int wisdom, int charisma, int maxHP, int currentHP,
                     int armourClass, int currentXP, int speed, int numHitDice) {
        this.charName = charName;
        this.raceId = raceId;
        this.classId = classId;
        this.backgroundId = backgroundId;
        this.alignmentId = alignmentId;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.armourClass = armourClass;
        this.currentXP = currentXP;
        this.speed = speed;
        this.numHitDice = numHitDice;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCharName() {
        return charName;
    }

    public int getRaceId() {
        return raceId;
    }

    public int getClassId() {
        return classId;
    }

    public int getBackgroundId() {
        return backgroundId;
    }

    public int getAlignmentId() {
        return alignmentId;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public int getCurrentXP() {
        return currentXP;
    }

    public int getSpeed() {
        return speed;
    }

    public int getNumHitDice() {
        return numHitDice;
    }

    public int getLevelFromXp(int xp) {
        if (xp < 300) {
            return 1;
        } else if (xp < 900) {
            return 2;
        } else if (xp < 2700) {
            return 3;
        } else if (xp < 6500) {
            return 4;
        } else if (xp < 14000) {
            return 5;
        } else if (xp < 23000) {
            return 6;
        } else if (xp < 34000) {
            return 7;
        } else if (xp < 48000) {
            return 8;
        } else if (xp < 64000) {
            return 9;
        } else if (xp < 85000) {
            return 10;
        } else if (xp < 100000) {
            return 11;
        } else if (xp < 120000) {
            return 12;
        } else if (xp < 140000) {
            return 13;
        } else if (xp < 165000) {
            return 14;
        } else if (xp < 195000) {
            return 15;
        } else if (xp < 225000) {
            return 16;
        } else if (xp < 265000) {
            return 17;
        } else if (xp < 305000) {
            return 18;
        } else if (xp < 355000) {
            return 19;
        } else {
            return 20;
        }
    }
}
