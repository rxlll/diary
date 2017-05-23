package com.example.liza.superdiary.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by User on 14.05.2017.
 */

@Entity(nameInDb = "notification",
        indexes = {
                @Index(value = "id ASC", unique = true)
        })
public final class Notification implements Parcelable {
    @Id
    private Long id;
    private String login;
    private String text;
    private String time;

    @Generated(hash = 1028598991)
    public Notification(Long id, String login, String text, String time) {
        this.id = id;
        this.login = login;
        this.text = text;
        this.time = time;
    }

    @Generated(hash = 1855225820)
    public Notification() {
    }

    public Notification(String text, String time) {
        this.text = text;
        this.time = time;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.login);
        dest.writeString(this.text);
        dest.writeString(this.time);
    }

    protected Notification(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.login = in.readString();
        this.text = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<Notification> CREATOR = new Parcelable.Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel source) {
            return new Notification(source);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };
}
