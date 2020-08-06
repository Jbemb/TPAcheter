package com.example.tpacheter.repository;

import androidx.lifecycle.LiveData;

import com.example.tpacheter.bo.Item;

import java.util.List;

public interface IItemRepository {
    void insert(Item item);

    LiveData<List<Item>> get();
    //not the same as Anthony's code, he doesnt use a LiveData
    Item get(int id);

    void update(Item item);

    void delete(Item item);

    void delete();
}
