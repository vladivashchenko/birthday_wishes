package com.wishes.entities;

import org.hibernate.validator.constraints.Email;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Wish> wishes;

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

  public List<Wish> getWishes() {
    return wishes;
  }

  public void setWishes(List<Wish> wishes) {
    this.wishes = wishes;
  }
}
