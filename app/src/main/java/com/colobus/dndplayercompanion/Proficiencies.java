package com.colobus.dndplayercompanion;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "proficiencies", foreignKeys = {
        @ForeignKey(
                entity = Character.class,
                parentColumns = "id",
                childColumns = "character_id",
                onDelete = ForeignKey.CASCADE)})

public class Proficiencies {
    
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "character_id", index = true)
    private int characterId;

    // Saving throws
    @ColumnInfo(name = "save_proficiency_str")
    private int saveProfStr;

    @ColumnInfo(name = "save_proficiency_dex")
    private int saveProfDex;

    @ColumnInfo(name = "save_proficiency_con")
    private int saveProfCon;

    @ColumnInfo(name = "save_proficiency_int")
    private int saveProfInt;

    @ColumnInfo(name = "save_proficiency_wis")
    private int saveProfWis;

    @ColumnInfo(name = "save_proficiency_cha")
    private int saveProfCha;

    // Skills
    @ColumnInfo(name = "skill_proficiency_acr")
    private double skillProfAcr;

    @ColumnInfo(name = "skill_proficiency_ani")
    private double skillProfAni;

    @ColumnInfo(name = "skill_proficiency_arc")
    private double skillProfArc;

    @ColumnInfo(name = "skill_proficiency_ath")
    private double skillProfAth;

    @ColumnInfo(name = "skill_proficiency_dec")
    private double skillProfDec;

    @ColumnInfo(name = "skill_proficiency_his")
    private double skillProfHis;

    @ColumnInfo(name = "skill_proficiency_ins")
    private double skillProfIns;

    @ColumnInfo(name = "skill_proficiency_int")
    private double skillProfInt;

    @ColumnInfo(name = "skill_proficiency_inv")
    private double skillProfInv;

    @ColumnInfo(name = "skill_proficiency_med")
    private double skillProfMed;

    @ColumnInfo(name = "skill_proficiency_nat")
    private double skillProfNat;

    @ColumnInfo(name = "skill_proficiency_prc")
    private double skillProfPrc;

    @ColumnInfo(name = "skill_proficiency_prf")
    private double skillProfPrf;

    @ColumnInfo(name = "skill_proficiency_prs")
    private double skillProfPrs;

    @ColumnInfo(name = "skill_proficiency_rel")
    private double skillProfRel;

    @ColumnInfo(name = "skill_proficiency_sle")
    private double skillProfSle;

    @ColumnInfo(name = "skill_proficiency_ste")
    private double skillProfSte;

    @ColumnInfo(name = "skill_proficiency_sur")
    private double skillProfSur;

    public Proficiencies(int characterId,
                         int saveProfStr, int saveProfDex, int saveProfCon,
                         int saveProfInt, int saveProfWis, int saveProfCha,
                         double skillProfAcr, double skillProfAni, double skillProfArc,
                         double skillProfAth, double skillProfDec, double skillProfHis,
                         double skillProfIns, double skillProfInt, double skillProfInv,
                         double skillProfMed, double skillProfNat, double skillProfPrc,
                         double skillProfPrf, double skillProfPrs, double skillProfRel,
                         double skillProfSle, double skillProfSte, double skillProfSur) {
        this.characterId = characterId;
        this.saveProfStr = saveProfStr;
        this.saveProfDex = saveProfDex;
        this.saveProfCon = saveProfCon;
        this.saveProfInt = saveProfInt;
        this.saveProfWis = saveProfWis;
        this.saveProfCha = saveProfCha;
        this.skillProfAcr = skillProfAcr;
        this.skillProfAni = skillProfAni;
        this.skillProfArc = skillProfArc;
        this.skillProfAth = skillProfAth;
        this.skillProfDec = skillProfDec;
        this.skillProfHis = skillProfHis;
        this.skillProfIns = skillProfIns;
        this.skillProfInt = skillProfInt;
        this.skillProfInv = skillProfInv;
        this.skillProfMed = skillProfMed;
        this.skillProfNat = skillProfNat;
        this.skillProfPrc = skillProfPrc;
        this.skillProfPrf = skillProfPrf;
        this.skillProfPrs = skillProfPrs;
        this.skillProfRel = skillProfRel;
        this.skillProfSle = skillProfSle;
        this.skillProfSte = skillProfSte;
        this.skillProfSur = skillProfSur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCharacterId() {
        return characterId;
    }

    public int getSaveProfStr() {
        return saveProfStr;
    }

    public int getSaveProfDex() {
        return saveProfDex;
    }

    public int getSaveProfCon() {
        return saveProfCon;
    }

    public int getSaveProfInt() {
        return saveProfInt;
    }

    public int getSaveProfWis() {
        return saveProfWis;
    }

    public int getSaveProfCha() {
        return saveProfCha;
    }

    public double getSkillProfAcr() {
        return skillProfAcr;
    }

    public double getSkillProfAni() {
        return skillProfAni;
    }

    public double getSkillProfArc() {
        return skillProfArc;
    }

    public double getSkillProfAth() {
        return skillProfAth;
    }

    public double getSkillProfDec() {
        return skillProfDec;
    }

    public double getSkillProfHis() {
        return skillProfHis;
    }

    public double getSkillProfIns() {
        return skillProfIns;
    }

    public double getSkillProfInt() {
        return skillProfInt;
    }

    public double getSkillProfInv() {
        return skillProfInv;
    }

    public double getSkillProfMed() {
        return skillProfMed;
    }

    public double getSkillProfNat() {
        return skillProfNat;
    }

    public double getSkillProfPrc() {
        return skillProfPrc;
    }

    public double getSkillProfPrf() {
        return skillProfPrf;
    }

    public double getSkillProfPrs() {
        return skillProfPrs;
    }

    public double getSkillProfRel() {
        return skillProfRel;
    }

    public double getSkillProfSle() {
        return skillProfSle;
    }

    public double getSkillProfSte() {
        return skillProfSte;
    }

    public double getSkillProfSur() {
        return skillProfSur;
    }
}
