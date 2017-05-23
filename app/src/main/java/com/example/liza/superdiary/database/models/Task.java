package com.example.liza.superdiary.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by User on 14.05.2017.
 */

@Entity(nameInDb = "task",
        indexes = {
                @Index(value = "id ASC", unique = true)
        })
public final class Task implements Parcelable {
    @Id
    private Long id;
    private Long userId;
    private String login;
    private String text;

    public Task(String text) {
        this.text = text;
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
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    protected Task(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.login = in.readString();
        this.text = in.readString();
    }

    @Generated(hash = 1410865648)
    public Task(Long id, Long userId, String login, String text) {
        this.id = id;
        this.userId = userId;
        this.login = login;
        this.text = text;
    }

    @Generated(hash = 733837707)
    public Task() {
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
