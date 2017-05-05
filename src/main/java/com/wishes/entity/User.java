package com.wishes.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
//TODO remove unused import
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

  private List<Wish> wishes;
  private int id;

  @NotNull
  @Size(min=1,max=255)
  @Email
  @Column(unique=true)
  private String email;
  
  @NotNull
  @Size(min=1,max=50)
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
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  //TODO move to line 16
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  public List<Wish> getWishes() {
    return wishes;
  }

  public void setWishes(List<Wish> wishes) {
    this.wishes = wishes;
  }
}
