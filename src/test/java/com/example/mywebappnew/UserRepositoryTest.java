package com.example.mywebappnew;

import com.example.mywebappnew.user.User;
import com.example.mywebappnew.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;
    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("dominhhieu@gmail.com");
        user.setPassword("minhhieu123");
        user.setFirstName("Do");
        user.setLastName("Hieu");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void getAllUsers(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void updateUser(){
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("Hello3000");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("Hello3000");
    }
    @Test
    public void getUser(){
        Integer userId = 2;
        Optional<User> userOptional = repo.findById(userId);
        Assertions.assertThat(userOptional).isPresent();
        User user = userOptional.get();
        System.out.println(user);
    }
    @Test
    public void deleteUser(){
        repo.deleteById(2);

        Optional<User> optionalUser = repo.findById(2);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
