package com.wishes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  @NotNull
  private String email;
  
  @NotNull
  private String name;

  public User() { }

  public User(int id) {
    this.id = id;
  }

  public User(int id,String name, String email) {
    this.id=id;
    this.email = email;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int value) {
    this.id = value;
  }

  public String getEmail() {
    return email;
  }
  
  public void setEmail(String value) {
    this.email = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }
  
} // class User
