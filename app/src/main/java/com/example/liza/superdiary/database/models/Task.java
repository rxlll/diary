package com.example.liza.superdiary.database.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by User on 14.05.2017.
 */

@Entity(nameInDb = "task",
        indexes = {
                @Index(value = "id DESC", unique = true)
        })
public final class Task {
    @Id
    private Long id;
    private String login;
    private String text;
@Generated(hash = 659926125)
public Task(Long id, String login, String text) {
    this.id = id;
    this.login = login;
    this.text = text;
}
@Generated(hash = 733837707)
public Task() {
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
}
