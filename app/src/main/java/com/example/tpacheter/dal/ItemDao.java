package com.example.tpacheter.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tpacheter.bo.Item;

import java.util.List;

/**
 * class with room instrustions for creating the the DAO and the item table
 */
@Dao
public interface ItemDao {
    @Insert
    void insert(Item item);

    @Insert
    void insert(Item ... items);

    @Query("Select * FROM Item")
    LiveData<List<Item>>get();

    @Query("SELECT * FROM Item WHERE id = :id")
    LiveData<Item> get(int id);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("Delete from Item")
    void delete();

}
