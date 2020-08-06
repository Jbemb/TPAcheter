package com.example.tpacheter.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tpacheter.bo.Item;
import com.example.tpacheter.repository.IItemRepository;
import com.example.tpacheter.repository.ItemDBRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    IItemRepository repo;
    private LiveData<List<Item>> observer = null;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        repo = new ItemDBRepository(application);
        observer = repo.get();
    }

    public LiveData<List<Item>> get()
    {
        return observer;
    }

    void insert(Item item)
    {
        repo.insert(item);
    }
    void update(Item item)
    {
        repo.update(item);
    }
    void delete(Item item)
    {
        repo.delete(item);
    }
    void delete()
    {
        repo.delete();
    }
}
