package com.wishes;

import com.wishes.dao.UserRepository;
import com.wishes.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner demo(UserRepository repository) {
    //TODO There are many database migration management systems (e.g. liquibase). Would be better to implement it.
    return (args) -> {
      // save students
      repository.save(new User(1,"John", "john@mail.ru"));
      repository.save(new User(2,"Steve", "Stevens@gmail.com"));
      repository.save(new User(3,"Mary", "mary@robinson.com"));
      repository.save(new User(4,"Kate", "kate@kate.com"));
      repository.save(new User(5,"Diana", "diana@doll.com"));
    };
  }

}
