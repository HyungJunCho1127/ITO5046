package com.example.ito5046;

public class Ranking {
    private int competitorRank;
    private int competitorPosition;
    private int competitorScore;

    public Ranking() {

    }

    public Ranking(int rank, int position, int score) {
        competitorRank = rank;
        competitorPosition = position;
        competitorScore = score;

    }

    public int getCompetitorRank() {
        return competitorRank;
    }

    public int setCompetitorRank(int rank) {
        competitorRank = rank;
        return rank;
    }

    public int getCompetitorPosition() {
        return competitorPosition;
    }

    public int getCompetitorScore() {
        return competitorScore;
    }

    public void setCompetitorScore(int competitorScore) {
        this.competitorScore = competitorScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "Rank :" + competitorRank +
                ", Position :" + competitorPosition +
                ", Score :" + competitorScore +
                '}';
    }

}
