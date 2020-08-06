package com.example.tpacheter.bo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class representing an item
 */
@Entity
public class Item implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private float price;
    private String description;
    private int rating;
    private boolean purchased;
    private String link;

    public Item(int id, String title, float price, String description, int rating, boolean purchased, String link) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.purchased = purchased;
        this.link = link;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        title = in.readString();
        price = in.readFloat();
        description = in.readString();
        rating = in.readInt();
        purchased = in.readByte() != 0;
        link = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public String getLink() {
        return link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", purchased=" + purchased +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeFloat(price);
        parcel.writeString(description);
        parcel.writeInt(rating);
        parcel.writeByte((byte) (purchased ? 1 : 0));
        parcel.writeString(link);
    }
}
