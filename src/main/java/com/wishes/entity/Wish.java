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
    private String wishes;
    @NotNull
    private int priority;

    public Wish() {
    }

    public Wish(String wish,int priority) {
        this.wishes = wish;
        this.priority=priority;
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


}
