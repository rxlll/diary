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

@Entity(nameInDb = "note",
        indexes = {
                @Index(value = "id DESC", unique = true)
        })
public final class Note implements Parcelable {
    @Id
    private Long id;
    private String login;
    private String text;

    @Generated(hash = 1303834805)
    public Note(Long id, String login, String text) {
        this.id = id;
        this.login = login;
        this.text = text;
    }

    @Generated(hash = 1272611929)
    public Note() {
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

    protected Note(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.login = in.readString();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
