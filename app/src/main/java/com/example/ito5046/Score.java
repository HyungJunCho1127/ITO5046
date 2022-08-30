package com.example.ito5046;

public class Score {
    private String battleName;
    private int[] scoreArray;
    private int position;

    public Score(){
    }

    public String getBattleName() {
        return battleName;
    }

    public void setBattleName(String battleName) {
        this.battleName = battleName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int[] getScoreArray() {
        return scoreArray;
    }

    public void increasePosition(int position){
        this.position = position +1;
    }

    public void setScoreArray(int[] scoreArray) {
        this.scoreArray = scoreArray;
    }

}
