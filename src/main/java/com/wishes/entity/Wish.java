package com.wishes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by stazhor on 27.04.17.
 */
@Entity
@Table(name="wishes")
public class Wish {
    private User user;
    private int id;

    @NotNull
    @Column(unique=true)
    private String wishes;
    @NotNull
    private int priority;
    @NotNull
    private String link;

    public Wish() {
    }

    public Wish(String wish,int priority,User user) {
        this.wishes = wish;
        this.priority=priority;
        this.user = user;
    }
    public Wish(User user) {
       this.user = user;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWishes() {
        return wishes;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @ManyToOne
    @JoinColumn(name = "users_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
