package com.example.ito5046;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "competition_table")
public class Competition {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name ="creator")
    private String creator;

    @ColumnInfo(name = "competition_name")
    private String competitionName;

    @ColumnInfo(name = "judge_number")
    private int judgeNumber;

    @ColumnInfo(name = "competitor_number")
    private int competitorNumber;

    @ColumnInfo(name = "judge_one")
    private String judgeOne;

    @ColumnInfo(name = "judge_two")
    private String judgeTwo;

    @ColumnInfo(name = "judge_three")
    private String judgeThree;

    public Competition(){

    }

    public Competition(String creator, String competitionName, int judgeNumber, int competitorNumber) {
        this.creator = creator;
        this.competitionName = competitionName;
        this.judgeNumber = judgeNumber;
        this.competitorNumber = competitorNumber;
    }

    public Competition(String competitionName, int judgeNumber, int competitorNumber, String judgeOne, String judgeTwo, String judgeThree) {
        this.competitionName = competitionName;
        this.judgeNumber = judgeNumber;
        this.competitorNumber = competitorNumber;
        this.judgeOne = judgeOne;
        this.judgeTwo = judgeTwo;
        this.judgeThree = judgeThree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public int getJudgeNumber() {
        return judgeNumber;
    }

    public void setJudgeNumber(int judgeNumber) {
        this.judgeNumber = judgeNumber;
    }

    public int getCompetitorNumber() {
        return competitorNumber;
    }

    public void setCompetitorNumber(int competitorNumber) {
        this.competitorNumber = competitorNumber;
    }

    public String getJudgeOne() {
        return judgeOne;
    }

    public void setJudgeOne(String judgeOne) {
        this.judgeOne = judgeOne;
    }

    public String getJudgeTwo() {
        return judgeTwo;
    }

    public void setJudgeTwo(String judgeTwo) {
        this.judgeTwo = judgeTwo;
    }

    public String getJudgeThree() {
        return judgeThree;
    }

    public void setJudgeThree(String judgeThree) {
        this.judgeThree = judgeThree;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", creator='" + creator + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", judgeNumber=" + judgeNumber +
                ", competitorNumber=" + competitorNumber +
                ", judgeOne='" + judgeOne + '\'' +
                ", judgeTwo='" + judgeTwo + '\'' +
                ", judgeThree='" + judgeThree + '\'' +
                '}';
    }
}
