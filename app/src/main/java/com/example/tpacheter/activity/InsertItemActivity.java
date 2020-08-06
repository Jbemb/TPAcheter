package com.example.tpacheter.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tpacheter.R;
import com.example.tpacheter.bo.Item;
import com.example.tpacheter.repository.IItemRepository;
import com.example.tpacheter.repository.ItemDBRepository;
import com.facebook.stetho.Stetho;

import java.util.List;

public class InsertItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //import Stetho
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_insert_item);
    }

    public void onClickSaveItem(View view) {


        EditText etTitle = findViewById(R.id.et_title_item);
        EditText etPrice = findViewById(R.id.et_price_item);
        EditText etDescription = findViewById(R.id.et_description_item);
        RatingBar etRating = findViewById(R.id.et_rating_item);
        EditText etLink = findViewById(R.id.et_link_item);

        String title = etTitle.getText().toString();
        String stringPrice = etPrice.getText().toString();
        float price = Float.parseFloat(stringPrice);
        String description = etDescription.getText().toString();
        int rating = (int) etRating.getRating();
        String link = etLink.getText().toString();

        IItemRepository itemRepo = new ItemDBRepository(this);
        Item insert = new Item(0,title,price,description,rating,false,link);
        itemRepo.insert(insert);

        Toast.makeText(this, title + " is saved!", Toast.LENGTH_LONG).show();

        etTitle.setText("");
        etPrice.setText("");
        etDescription.setText("");
        etLink.setText("");
        etRating.setRating(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mon_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, InsertItemActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Preferences", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_list:
                intent = new Intent(this, ListItemActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Recherche", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}