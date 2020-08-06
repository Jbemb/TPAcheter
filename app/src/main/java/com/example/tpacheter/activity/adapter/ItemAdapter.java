package com.example.tpacheter.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tpacheter.R;
import com.example.tpacheter.bo.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View newLine, @NonNull ViewGroup parent) {

        if (newLine == null) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            newLine = li.inflate(R.layout.style_list_item, parent, false);
        }

        TextView tvTitle = newLine.findViewById(R.id.tv_title);
        tvTitle.setText(getItem(position).getTitle());

        TextView tvPrice = newLine.findViewById(R.id.tv_price);
        tvPrice.setText(toString().valueOf(getItem(position).getPrice()));

        TextView tvDescription = newLine.findViewById(R.id.tv_description);
        tvDescription.setText(getItem(position).getDescription());

        RatingBar rbRating = newLine.findViewById(R.id.rb_rating);
        rbRating.setRating(getItem(position).getRating());

        if (getItem(position).isPurchased()){
        ImageView imageView = (ImageView) newLine.findViewById(R.id.is_purchased);
        imageView.setVisibility(View.VISIBLE);
        }

        return newLine;
    }
}
