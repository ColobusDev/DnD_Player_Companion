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
    void insert(Character character);

    @Update
    void update(Character character);

    @Query("DELETE FROM character_table WHERE id = :id")
    void delete(int id);

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
    LiveData<FullCharacterDetail> getFullCharacterDetailById(int character_id);

    class BasicCharacterDetail {
        public int id;
        public String charName;
        public int xp;
        public String className;
        public String raceName;

        public BasicCharacterDetail(int id, String charName, int xp, String className, String raceName) {
            this.id = id;
            this.charName = charName;
            this.xp = xp;
            this.className = className;
            this.raceName = raceName;
        }

        public int getId() {
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
        public int id;
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
        private int skill_proficiency_acr;
        private int skill_proficiency_ani;
        private int skill_proficiency_arc;
        private int skill_proficiency_ath;
        private int skill_proficiency_dec;
        private int skill_proficiency_his;
        private int skill_proficiency_ins;
        private int skill_proficiency_int;
        private int skill_proficiency_inv;
        private int skill_proficiency_med;
        private int skill_proficiency_nat;
        private int skill_proficiency_prc;
        private int skill_proficiency_prf;
        private int skill_proficiency_prs;
        private int skill_proficiency_rel;
        private int skill_proficiency_sle;
        private int skill_proficiency_ste;
        private int skill_proficiency_sur;

        public FullCharacterDetail(int id, String charName, String className, String raceName,
                                   String backgroundName, String alignmentName, int xp,
                                   int armour_class, int speed, int current_HP, int max_HP,
                                   int num_hit_dice, int hitDiceType,
                                   int STR, int DEX, int CON, int INT, int WIS, int CHA,
                                   int save_proficiency_str, int save_proficiency_dex,
                                   int save_proficiency_con, int save_proficiency_int,
                                   int save_proficiency_wis, int save_proficiency_cha,
                                   int skill_proficiency_acr, int skill_proficiency_ani,
                                   int skill_proficiency_arc, int skill_proficiency_ath,
                                   int skill_proficiency_dec, int skill_proficiency_his,
                                   int skill_proficiency_ins, int skill_proficiency_int,
                                   int skill_proficiency_inv, int skill_proficiency_med,
                                   int skill_proficiency_nat, int skill_proficiency_prc,
                                   int skill_proficiency_prf, int skill_proficiency_prs,
                                   int skill_proficiency_rel, int skill_proficiency_sle,
                                   int skill_proficiency_ste, int skill_proficiency_sur) {
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

        public int getId() {
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
            int prof_type;
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
