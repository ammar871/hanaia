package com.example.hanaiabeauty.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hanaiabeauty.model.Catogray;


import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Query("SELECT * FROM detail")
    List<Catogray> getAll();

    @Insert(onConflict = REPLACE)
    void insertUser(Catogray mUser);

    @Insert
    void insertAllUser(Catogray... mUsersList);

    @Delete
    void delete(Catogray mUser);

    @Update
    void updateUser(Catogray mUser);

    @Query("SELECT * FROM detail WHERE uid = :uId")
    Catogray getUserById(int uId);

    @Query("SELECT * FROM detail WHERE uid IN (:userIds)")
    List<Catogray> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM detail WHERE name LIKE :name LIMIT 1")
    boolean findByName(String name);

        @Query("DELETE FROM detail")
        void nukeTable();

}
