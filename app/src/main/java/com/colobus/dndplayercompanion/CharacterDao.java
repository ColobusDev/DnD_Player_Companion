package com.colobus.dndplayercompanion;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.colobus.dndplayercompanion.Character.*;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Character character);

    @Update
    void update(Character character);

    @Query("DELETE FROM character_table WHERE id = :id")
    void delete(long id);

    @Query("SELECT * FROM character_table ORDER BY name")
    LiveData<List<Character>> getAllCharacters();

    @Query("SELECT * FROM character_table WHERE id = :id")
    LiveData<Character> getCharacterById(int id);

    @Query("SELECT CH.id AS id, CH.name AS charName, CH.XP AS xp, CL.name AS className, R.name AS raceName " +
            "FROM character_table CH " +
            "JOIN class_table CL ON CH.class_id = CL.id " +
            "JOIN race_table R ON R.id = CH.race_id")
    LiveData<List<BasicCharacterDetail>> getAllBasicCharacterDetails();

    @Query("SELECT CH.id AS id, CH.name AS charName, CL.name AS className, R.name AS raceName, " +
            "b.name as backgroundName, a.short_name as alignmentName, CH.XP AS xp, " +
            "ch.armour_class, ch.speed, ch.current_HP, ch.max_HP, " +
            "ch.num_hit_dice, cl.hitDiceType," +
            "ch.STR, ch.DEX, ch.CON, ch.INT, ch.WIS, ch.CHA, " +
            "p.save_proficiency_str, p.save_proficiency_dex, p.save_proficiency_con, " +
            "p.save_proficiency_int, p.save_proficiency_wis, p.save_proficiency_cha," +
            "p.skill_proficiency_acr, p.skill_proficiency_ani, p.skill_proficiency_arc, " +
            "p.skill_proficiency_ath, p.skill_proficiency_dec, p.skill_proficiency_his, " +
            "p.skill_proficiency_ins, p.skill_proficiency_int, p.skill_proficiency_inv, " +
            "p.skill_proficiency_med, p.skill_proficiency_nat, p.skill_proficiency_prc, " +
            "p.skill_proficiency_prf, p.skill_proficiency_prs, p.skill_proficiency_rel, " +
            "p.skill_proficiency_sle, p.skill_proficiency_ste, p.skill_proficiency_sur " +
            "FROM character_table ch " +
            "JOIN class_table CL ON CH.class_id = CL.id " +
            "JOIN race_table R ON R.id = CH.race_id " +
            "JOIN alignment_table a ON a.id = CH.alignment_id " +
            "JOIN background_table b ON b.id = CH.background_id " +
            "JOIN proficiencies p ON p.character_id = CH.id " +
            "WHERE CH.id = :character_id")
    LiveData<FullCharacterDetail> getFullCharacterDetailById(long character_id);

    @Query("SELECT CH.id AS id, CH.name AS charName, CL.name AS className, CL.id AS class_id, " +
            "R.name AS raceName, R.id AS race_id, b.name as backgroundName, b.id as background_id, " +
            "a.short_name as alignmentName, a.id as alignment_id, CH.XP AS xp, " +
            "ch.armour_class, ch.speed, ch.max_HP, " +
            "ch.num_hit_dice, " +
            "ch.STR, ch.DEX, ch.CON, ch.INT, ch.WIS, ch.CHA, " +
            "p.id AS proficiency_id, " +
            "p.save_proficiency_str, p.save_proficiency_dex, p.save_proficiency_con, " +
            "p.save_proficiency_int, p.save_proficiency_wis, p.save_proficiency_cha," +
            "p.skill_proficiency_acr, p.skill_proficiency_ani, p.skill_proficiency_arc, " +
            "p.skill_proficiency_ath, p.skill_proficiency_dec, p.skill_proficiency_his, " +
            "p.skill_proficiency_ins, p.skill_proficiency_int, p.skill_proficiency_inv, " +
            "p.skill_proficiency_med, p.skill_proficiency_nat, p.skill_proficiency_prc, " +
            "p.skill_proficiency_prf, p.skill_proficiency_prs, p.skill_proficiency_rel, " +
            "p.skill_proficiency_sle, p.skill_proficiency_ste, p.skill_proficiency_sur " +
            "FROM character_table ch " +
            "JOIN class_table CL ON CH.class_id = CL.id " +
            "JOIN race_table R ON R.id = CH.race_id " +
            "JOIN alignment_table a ON a.id = CH.alignment_id " +
            "JOIN background_table b ON b.id = CH.background_id " +
            "JOIN proficiencies p ON p.character_id = CH.id " +
            "WHERE CH.id = :character_id")
    LiveData<CharacterDetailsForEdit> getEditCharacterDetails(long character_id);

    class CharacterDetailsForEdit {
        public long id;
        private String charName;
        private String className;
        private int class_id;
        private String raceName;
        private int race_id;
        private String backgroundName;
        private int background_id;
        private String alignmentName;
        private int alignment_id;
        private int xp;
        private int armour_class;
        private int speed;
        private int max_HP;
        private int num_hit_dice;
        private int STR;
        private int DEX;
        private int CON;
        private int INT;
        private int WIS;
        private int CHA;
        private int proficiency_id;
        private int save_proficiency_str;
        private int save_proficiency_dex;
        private int save_proficiency_con;
        private int save_proficiency_int;
        private int save_proficiency_wis;
        private int save_proficiency_cha;
        private double skill_proficiency_acr;
        private double skill_proficiency_ani;
        private double skill_proficiency_arc;
        private double skill_proficiency_ath;
        private double skill_proficiency_dec;
        private double skill_proficiency_his;
        private double skill_proficiency_ins;
        private double skill_proficiency_int;
        private double skill_proficiency_inv;
        private double skill_proficiency_med;
        private double skill_proficiency_nat;
        private double skill_proficiency_prc;
        private double skill_proficiency_prf;
        private double skill_proficiency_prs;
        private double skill_proficiency_rel;
        private double skill_proficiency_sle;
        private double skill_proficiency_ste;
        private double skill_proficiency_sur;

        public CharacterDetailsForEdit(long id, String charName, String className, int class_id,
                                       String raceName, int race_id, String backgroundName, int background_id,
                                       String alignmentName, int alignment_id, int xp,
                                       int armour_class, int speed, int max_HP, int num_hit_dice,
                                       int STR, int DEX, int CON, int INT, int WIS, int CHA,
                                       int proficiency_id,
                                       int save_proficiency_str, int save_proficiency_dex,
                                       int save_proficiency_con, int save_proficiency_int,
                                       int save_proficiency_wis, int save_proficiency_cha,
                                       double skill_proficiency_acr, double skill_proficiency_ani,
                                       double skill_proficiency_arc, double skill_proficiency_ath,
                                       double skill_proficiency_dec, double skill_proficiency_his,
                                       double skill_proficiency_ins, double skill_proficiency_int,
                                       double skill_proficiency_inv, double skill_proficiency_med,
                                       double skill_proficiency_nat, double skill_proficiency_prc,
                                       double skill_proficiency_prf, double skill_proficiency_prs,
                                       double skill_proficiency_rel, double skill_proficiency_sle,
                                       double skill_proficiency_ste, double skill_proficiency_sur) {
            this.id = id;
            this.charName = charName;
            this.className = className;
            this.class_id = class_id;
            this.raceName = raceName;
            this.race_id = race_id;
            this.backgroundName = backgroundName;
            this.background_id = background_id;
            this.alignmentName = alignmentName;
            this.alignment_id = alignment_id;
            this.xp = xp;
            this.armour_class = armour_class;
            this.speed = speed;
            this.max_HP = max_HP;
            this.num_hit_dice = num_hit_dice;
            this.STR = STR;
            this.DEX = DEX;
            this.CON = CON;
            this.INT = INT;
            this.WIS = WIS;
            this.CHA = CHA;
            this.proficiency_id = proficiency_id;
            this.save_proficiency_str = save_proficiency_str;
            this.save_proficiency_dex = save_proficiency_dex;
            this.save_proficiency_con = save_proficiency_con;
            this.save_proficiency_int = save_proficiency_int;
            this.save_proficiency_wis = save_proficiency_wis;
            this.save_proficiency_cha = save_proficiency_cha;
            this.skill_proficiency_acr = skill_proficiency_acr;
            this.skill_proficiency_ani = skill_proficiency_ani;
            this.skill_proficiency_arc = skill_proficiency_arc;
            this.skill_proficiency_ath = skill_proficiency_ath;
            this.skill_proficiency_dec = skill_proficiency_dec;
            this.skill_proficiency_his = skill_proficiency_his;
            this.skill_proficiency_ins = skill_proficiency_ins;
            this.skill_proficiency_int = skill_proficiency_int;
            this.skill_proficiency_inv = skill_proficiency_inv;
            this.skill_proficiency_med = skill_proficiency_med;
            this.skill_proficiency_nat = skill_proficiency_nat;
            this.skill_proficiency_prc = skill_proficiency_prc;
            this.skill_proficiency_prf = skill_proficiency_prf;
            this.skill_proficiency_prs = skill_proficiency_prs;
            this.skill_proficiency_rel = skill_proficiency_rel;
            this.skill_proficiency_sle = skill_proficiency_sle;
            this.skill_proficiency_ste = skill_proficiency_ste;
            this.skill_proficiency_sur = skill_proficiency_sur;
        }


        public long getId() {
            return id;
        }

        public String getCharName() {
            return charName;
        }

        public String getClassName() {
            return className;
        }

        public int getClass_id() {
            return class_id;
        }

        public String getRaceName() {
            return raceName;
        }

        public int getRace_id() {
            return race_id;
        }

        public String getBackgroundName() {
            return backgroundName;
        }

        public int getBackground_id() {
            return background_id;
        }

        public String getAlignmentName() {
            return alignmentName;
        }

        public int getAlignment_id() {
            return alignment_id;
        }

        public int getXp() {
            return xp;
        }

        public int getArmour_class() {
            return armour_class;
        }

        public int getSpeed() {
            return speed;
        }

        public int getMax_HP() {
            return max_HP;
        }

        public int getNum_hit_dice() {
            return num_hit_dice;
        }

        public int getSTR() {
            return STR;
        }

        public int getDEX() {
            return DEX;
        }

        public int getCON() {
            return CON;
        }

        public int getINT() {
            return INT;
        }

        public int getWIS() {
            return WIS;
        }

        public int getCHA() {
            return CHA;
        }

        public int getProficiency_id() {
            return proficiency_id;
        }

        public int getSave_proficiency_str() {
            return save_proficiency_str;
        }

        public int getSave_proficiency_dex() {
            return save_proficiency_dex;
        }

        public int getSave_proficiency_con() {
            return save_proficiency_con;
        }

        public int getSave_proficiency_int() {
            return save_proficiency_int;
        }

        public int getSave_proficiency_wis() {
            return save_proficiency_wis;
        }

        public int getSave_proficiency_cha() {
            return save_proficiency_cha;
        }

        public double getSkill_proficiency_acr() {
            return skill_proficiency_acr;
        }

        public double getSkill_proficiency_ani() {
            return skill_proficiency_ani;
        }

        public double getSkill_proficiency_arc() {
            return skill_proficiency_arc;
        }

        public double getSkill_proficiency_ath() {
            return skill_proficiency_ath;
        }

        public double getSkill_proficiency_dec() {
            return skill_proficiency_dec;
        }

        public double getSkill_proficiency_his() {
            return skill_proficiency_his;
        }

        public double getSkill_proficiency_ins() {
            return skill_proficiency_ins;
        }

        public double getSkill_proficiency_int() {
            return skill_proficiency_int;
        }

        public double getSkill_proficiency_inv() {
            return skill_proficiency_inv;
        }

        public double getSkill_proficiency_med() {
            return skill_proficiency_med;
        }

        public double getSkill_proficiency_nat() {
            return skill_proficiency_nat;
        }

        public double getSkill_proficiency_prc() {
            return skill_proficiency_prc;
        }

        public double getSkill_proficiency_prf() {
            return skill_proficiency_prf;
        }

        public double getSkill_proficiency_prs() {
            return skill_proficiency_prs;
        }

        public double getSkill_proficiency_rel() {
            return skill_proficiency_rel;
        }

        public double getSkill_proficiency_sle() {
            return skill_proficiency_sle;
        }

        public double getSkill_proficiency_ste() {
            return skill_proficiency_ste;
        }

        public double getSkill_proficiency_sur() {
            return skill_proficiency_sur;
        }
    }

    class BasicCharacterDetail {
        public long id;
        public String charName;
        public int xp;
        public String className;
        public String raceName;

        public BasicCharacterDetail(long id, String charName, int xp, String className, String raceName) {
            this.id = id;
            this.charName = charName;
            this.xp = xp;
            this.className = className;
            this.raceName = raceName;
        }

        public long getId() {
            return id;
        }

        public String getCharName() {
            return charName;
        }

        public int getXp() {
            return xp;
        }

        public String getClassName() {
            return className;
        }

        public String getRaceName() {
            return raceName;
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

    class FullCharacterDetail {
        public long id;
        private String charName;
        private String className;
        private String raceName;
        private String backgroundName;
        private String alignmentName;
        private int xp;
        private int armour_class;
        private int speed;
        private int current_HP;
        private int max_HP;
        private int num_hit_dice;
        private int hitDiceType;
        private int STR;
        private int DEX;
        private int CON;
        private int INT;
        private int WIS;
        private int CHA;
        private int save_proficiency_str;
        private int save_proficiency_dex;
        private int save_proficiency_con;
        private int save_proficiency_int;
        private int save_proficiency_wis;
        private int save_proficiency_cha;
        private double skill_proficiency_acr;
        private double skill_proficiency_ani;
        private double skill_proficiency_arc;
        private double skill_proficiency_ath;
        private double skill_proficiency_dec;
        private double skill_proficiency_his;
        private double skill_proficiency_ins;
        private double skill_proficiency_int;
        private double skill_proficiency_inv;
        private double skill_proficiency_med;
        private double skill_proficiency_nat;
        private double skill_proficiency_prc;
        private double skill_proficiency_prf;
        private double skill_proficiency_prs;
        private double skill_proficiency_rel;
        private double skill_proficiency_sle;
        private double skill_proficiency_ste;
        private double skill_proficiency_sur;

        public FullCharacterDetail(long id, String charName, String className, String raceName,
                                   String backgroundName, String alignmentName, int xp,
                                   int armour_class, int speed, int current_HP, int max_HP,
                                   int num_hit_dice, int hitDiceType,
                                   int STR, int DEX, int CON, int INT, int WIS, int CHA,
                                   int save_proficiency_str, int save_proficiency_dex,
                                   int save_proficiency_con, int save_proficiency_int,
                                   int save_proficiency_wis, int save_proficiency_cha,
                                   double skill_proficiency_acr, double skill_proficiency_ani,
                                   double skill_proficiency_arc, double skill_proficiency_ath,
                                   double skill_proficiency_dec, double skill_proficiency_his,
                                   double skill_proficiency_ins, double skill_proficiency_int,
                                   double skill_proficiency_inv, double skill_proficiency_med,
                                   double skill_proficiency_nat, double skill_proficiency_prc,
                                   double skill_proficiency_prf, double skill_proficiency_prs,
                                   double skill_proficiency_rel, double skill_proficiency_sle,
                                   double skill_proficiency_ste, double skill_proficiency_sur) {
            this.id = id;
            this.charName = charName;
            this.className = className;
            this.raceName = raceName;
            this.backgroundName = backgroundName;
            this.alignmentName = alignmentName;
            this.xp = xp;
            this.armour_class = armour_class;
            this.speed = speed;
            this.current_HP = current_HP;
            this.max_HP = max_HP;
            this.num_hit_dice = num_hit_dice;
            this.hitDiceType = hitDiceType;
            this.STR = STR;
            this.DEX = DEX;
            this.CON = CON;
            this.INT = INT;
            this.WIS = WIS;
            this.CHA = CHA;
            this.save_proficiency_str = save_proficiency_str;
            this.save_proficiency_dex = save_proficiency_dex;
            this.save_proficiency_con = save_proficiency_con;
            this.save_proficiency_int = save_proficiency_int;
            this.save_proficiency_wis = save_proficiency_wis;
            this.save_proficiency_cha = save_proficiency_cha;
            this.skill_proficiency_acr = skill_proficiency_acr;
            this.skill_proficiency_ani = skill_proficiency_ani;
            this.skill_proficiency_arc = skill_proficiency_arc;
            this.skill_proficiency_ath = skill_proficiency_ath;
            this.skill_proficiency_dec = skill_proficiency_dec;
            this.skill_proficiency_his = skill_proficiency_his;
            this.skill_proficiency_ins = skill_proficiency_ins;
            this.skill_proficiency_int = skill_proficiency_int;
            this.skill_proficiency_inv = skill_proficiency_inv;
            this.skill_proficiency_med = skill_proficiency_med;
            this.skill_proficiency_nat = skill_proficiency_nat;
            this.skill_proficiency_prc = skill_proficiency_prc;
            this.skill_proficiency_prf = skill_proficiency_prf;
            this.skill_proficiency_prs = skill_proficiency_prs;
            this.skill_proficiency_rel = skill_proficiency_rel;
            this.skill_proficiency_sle = skill_proficiency_sle;
            this.skill_proficiency_ste = skill_proficiency_ste;
            this.skill_proficiency_sur = skill_proficiency_sur;
        }

        public long getId() {
            return id;
        }

        public String getCharName() {
            return charName;
        }

        public String getClassName() {
            return className;
        }

        public String getRaceName() {
            return raceName;
        }

        public String getBackgroundName() {
            return backgroundName;
        }

        public String getAlignmentName() {
            return alignmentName;
        }

        public int getXp() {
            return xp;
        }

        public int getArmour_class() {
            return armour_class;
        }

        public int getSpeed() {
            return speed;
        }

        public int getCurrent_HP() {
            return current_HP;
        }

        public int getMax_HP() {
            return max_HP;
        }

        public int getNum_hit_dice() {
            return num_hit_dice;
        }

        public int getHitDiceType() {
            return hitDiceType;
        }

        public int getSTR() {
            return STR;
        }

        public int getDEX() {
            return DEX;
        }

        public int getCON() {
            return CON;
        }

        public int getINT() {
            return INT;
        }

        public int getWIS() {
            return WIS;
        }

        public int getCHA() {
            return CHA;
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

        public int getModifier(@NotNull String type) {
            double prof_type;
            int ability;
            int prof_bonus = getProfBonusFromLevel(getLevelFromXp(getXp()));
            switch (type) {
                case "str_base":
                    prof_type = 0;
                    ability = getSTR();
                    break;
                case "dex_base":
                    prof_type = 0;
                    ability = getDEX();
                    break;
                case "con_base":
                    prof_type = 0;
                    ability = getCON();
                    break;
                case "int_base":
                    prof_type = 0;
                    ability = getINT();
                    break;
                case "wis_base":
                    prof_type = 0;
                    ability = getWIS();
                    break;
                case "cha_base":
                    prof_type = 0;
                    ability = getCHA();
                    break;
                case "str_save":
                    prof_type = save_proficiency_str;
                    ability = getSTR();
                    break;
                case "dex_save":
                    prof_type = save_proficiency_dex;
                    ability = getDEX();
                    break;
                case "con_save":
                    prof_type = save_proficiency_con;
                    ability = getCON();
                    break;
                case "int_save":
                    prof_type = save_proficiency_int;
                    ability = getINT();
                    break;
                case "wis_save":
                    prof_type = save_proficiency_wis;
                    ability = getWIS();
                    break;
                case "cha_save":
                    prof_type = save_proficiency_cha;
                    ability = getCHA();
                    break;
                case "acr_skill":
                    prof_type = skill_proficiency_acr;
                    ability = getDEX();
                    break;
                case "ani_skill":
                    prof_type = skill_proficiency_ani;
                    ability = getWIS();
                    break;
                case "arc_skill":
                    prof_type = skill_proficiency_arc;
                    ability = getINT();
                    break;
                case "ath_skill":
                    prof_type = skill_proficiency_ath;
                    ability = getSTR();
                    break;
                case "dec_skill":
                    prof_type = skill_proficiency_dec;
                    ability = getCHA();
                    break;
                case "his_skill":
                    prof_type = skill_proficiency_his;
                    ability = getINT();
                    break;
                case "ins_skill":
                    prof_type = skill_proficiency_ins;
                    ability = getWIS();
                    break;
                case "int_skill":
                    prof_type = skill_proficiency_int;
                    ability = getCHA();
                    break;
                case "inv_skill":
                    prof_type = skill_proficiency_inv;
                    ability = getINT();
                    break;
                case "med_skill":
                    prof_type = skill_proficiency_med;
                    ability = getWIS();
                    break;
                case "nat_skill":
                    prof_type = skill_proficiency_nat;
                    ability = getINT();
                    break;
                case "prc_skill":
                    prof_type = skill_proficiency_prc;
                    ability = getWIS();
                    break;
                case "prf_skill":
                    prof_type = skill_proficiency_prf;
                    ability = getCHA();
                    break;
                case "prs_skill":
                    prof_type = skill_proficiency_prs;
                    ability = getCHA();
                    break;
                case "rel_skill":
                    prof_type = skill_proficiency_rel;
                    ability = getINT();
                    break;
                case "sle_skill":
                    prof_type = skill_proficiency_sle;
                    ability = getDEX();
                    break;
                case "ste_skill":
                    prof_type = skill_proficiency_ste;
                    ability = getDEX();
                    break;
                case "sur_skill":
                    prof_type = skill_proficiency_sur;
                    ability = getWIS();
                    break;
                default:
                    return 999;
            }
            int baseMod = (int) Math.floor(((double) ability - 10) / 2);
            return baseMod + (int) Math.floor(prof_type * prof_bonus);
        }


        public int getProfBonusFromLevel(int level) {
            if (level < 5) {
                return 2;
            } else if (level < 9) {
                return 3;
            } else if (level < 13) {
                return 4;
            } else if (level < 17) {
                return 5;
            } else {
                return 6;
            }
        }
    }
}
