package com.example.ito5046;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CompetitionDao {

    @Insert
    void insertCompetition(Competition competition);

    @Update
    void updateCompetition(Competition competition);

    @Query("SELECT * FROM competition_table")
    List<Competition> getAllCompetitions();

    @Query("SELECT * FROM competition_table WHERE creator LIKE :creator")
    List<Competition> getAllUserCompetitions(String creator);

    @Query("SELECT * FROM competition_table WHERE competition_name LIKE :competitionName")
    Competition findCompetitionName(String competitionName);

    @Delete
    void deleteCompetition(Competition competition);
}
