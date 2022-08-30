package com.example.ito5046;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Query("SELECT * FROM user_table WHERE id LIKE :id")
    User findUserById(int id);

    @Query("SELECT * FROM user_table WHERE username LIKE :username")
    User findUsername(String username);

    @Query("SELECT * FROM user_table WHERE username LIKE :username AND password LIKE :password")
    User matchUsernameAndPassword(String username, String password);


    @Query("SELECT * FROM user_table WHERE username LIKE :username AND email LIKE :email")
    User matchUsernameAndEmail(String username, String email);

    @Update
    void updatePassword(User user);

    @Delete
    void deleteUser(User user);
}
