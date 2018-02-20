package com.example.kinhangpoon.simpsonscharacter.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KinhangPoon on 17/2/2018.
 */

public class Characters implements Parcelable{
    String title,description,imageUrl;

    public Characters(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    protected Characters(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Characters> CREATOR = new Creator<Characters>() {
        @Override
        public Characters createFromParcel(Parcel in) {
            return new Characters(in);
        }

        @Override
        public Characters[] newArray(int size) {
            return new Characters[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageUrl);
    }
}
