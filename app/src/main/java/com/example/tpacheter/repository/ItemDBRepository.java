package com.example.tpacheter.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tpacheter.bo.Item;
import com.example.tpacheter.dal.AppDatabase;
import com.example.tpacheter.dal.ItemDao;

import java.util.List;

public class ItemDBRepository implements IItemRepository{

    private ItemDao itemDao;

    public ItemDBRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        itemDao = db.getItemDao();
    }

    @Override
    public void insert(Item item) {
        new AsyncTask<Item, Void, Void>(){

            @Override
            protected Void doInBackground(Item... items) {
                itemDao.insert(items[0]);
                return null;
            }
        }.execute(item);
    }

    @Override
    public LiveData<List<Item>> get() {
        return itemDao.get();
    }

    //need livedate
    @Override
    public Item get(int id) {
        return null;
    }

    @Override
    public void update(Item item) {
        new AsyncTask<Item, Void, Void>(){

            @Override
            protected Void doInBackground(Item... items) {
                itemDao.update(items[0]);
                return null;
            }
        }.execute(item);
    }

    @Override
    public void delete(Item item) {
        new AsyncTask<Item,Void,Void>(){
            // tell them what to do
            @Override
            protected Void doInBackground(Item... items) {
                //delete
                itemDao.delete(items[0]);
                return null;
            }
        }.execute(item);
    }

    @Override
    public void delete() {
        new AsyncTask<Item,Void,Void>(){
            // tell them what to do
            @Override
            protected Void doInBackground(Item... items) {
                //delete all
                itemDao.delete();
                return null;
            }
        }.execute();
    }
}
