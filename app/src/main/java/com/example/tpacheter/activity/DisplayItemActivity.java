package com.example.tpacheter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class DisplayItemActivity extends AppCompatActivity {

    private Item item = null;
    private IItemRepository itemRepo = new ItemDBRepository(this);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);
        //recouperate object to display - modify
        Intent intent = getIntent();
        item = intent.getParcelableExtra("displayItem");
        //get the placeholders
        EditText etTitle = findViewById(R.id.et_title);
        EditText etPrice = findViewById(R.id.et_price);
        EditText etDescription = findViewById(R.id.et_description);
        RatingBar etRating = findViewById(R.id.rb_rating);
        EditText etLink = findViewById(R.id.et_link);
        CheckBox cbBought = findViewById(R.id.cb_bought);
        // set the placeholders
        etTitle.setText(item.getTitle());
        etPrice.setText(String.valueOf(item.getPrice()));
        etDescription.setText(item.getDescription());
        etRating.setRating(item.getRating());
        etLink.setText(item.getLink());
        // cbBought
        if(item.isPurchased()){
            cbBought.setChecked(true);
        }
    }

    public void onClickModifyItem(View view) {
        //modify
        //get the placeholders
        EditText etTitle = findViewById(R.id.et_title);
        EditText etPrice = findViewById(R.id.et_price);
        EditText etDescription = findViewById(R.id.et_description);
        RatingBar etRating = findViewById(R.id.rb_rating);
        EditText etLink = findViewById(R.id.et_link);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_bought);

        // set the info in the item
        item.setTitle(etTitle.getText().toString());
        //price
        String stringPrice = etPrice.getText().toString();
        float price = Float.parseFloat(stringPrice);
        item.setPrice(price);
        item.setDescription(etDescription.getText().toString());;
        //rating
        int rating = (int) etRating.getRating();
        item.setRating(rating);
        item.setLink(etLink.getText().toString());;
        item.setPurchased(checkBox.isChecked());
        //update
        itemRepo.update(item);
        Intent intent = new Intent(this, ListItemActivity.class);
        startActivity(intent);
        Toast.makeText(this, item.getTitle() + " is modified!", Toast.LENGTH_LONG).show();
    }

    public void onClickDeleteItem(View view) {
        //delete item
        itemRepo.delete(item);

        //return on the list page
        Intent intent = new Intent(this, ListItemActivity.class);
        startActivity(intent);
        Toast.makeText(this, item.getTitle() + " is deleted!", Toast.LENGTH_LONG).show();
    }
}