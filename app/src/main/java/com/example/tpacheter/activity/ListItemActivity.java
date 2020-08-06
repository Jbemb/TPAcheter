package com.example.tpacheter.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tpacheter.R;
import com.example.tpacheter.activity.adapter.ItemAdapter;
import com.example.tpacheter.bo.Item;
import com.example.tpacheter.view_model.ItemViewModel;
import com.facebook.stetho.Stetho;

import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    private ListView itemList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_list_item);

        itemList = findViewById(R.id.list_item);
        //define listener to access modification page
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get the object clicked
                Item item = (Item) itemList.getAdapter().getItem(i);
                //send the object to another page
                Intent intent = new Intent(ListItemActivity.this, DisplayItemActivity.class);
                intent.putExtra("displayItem", item);
                startActivity(intent);
            }
        });
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

    @Override
    protected void onResume() {
        super.onResume();

        ItemViewModel vm = ViewModelProviders.of(this).get(ItemViewModel.class);
        LiveData<List<Item>> observer = vm.get();
        observer.observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                ItemAdapter adapter = new ItemAdapter(ListItemActivity.this, R.layout.style_list_item, items);
                itemList.setAdapter(adapter);
            }
        });
    }

    public void onClickSaveItem(View view) {
        Intent intent = new Intent(this, InsertItemActivity.class);
        startActivity(intent);
    }

}