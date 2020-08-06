package com.example.tpacheter.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.tpacheter.R;
import com.example.tpacheter.bo.Item;
import com.example.tpacheter.repository.IItemRepository;
import com.example.tpacheter.repository.ItemDBRepository;
import com.example.tpacheter.view_model.ItemViewModel;
import com.facebook.stetho.Stetho;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        IItemRepository itemRepo = new ItemDBRepository(this);
        ItemViewModel itemVM = ViewModelProviders.of(this).get(ItemViewModel.class);
        LiveData<List<Item>> observer = itemVM.get();

        Item one = new Item(0, "cookies", 0.25f, "Yummy", 4, false, "https://www.amazon.fr/Cookie-Monster/s?k=Cookie+Monster");
        itemRepo.insert(one);
        Log.i("=========Results!===========", "item : yoyooyoyoyoy=========" );
        observer.observe(this, new Observer<List<Item>>() {

            @Override
            public void onChanged(List<Item> items) {
                for (Item i : items) {
                    Log.i("=========Results!===========", "item : " + i);
                }
            }
        });

    }
}