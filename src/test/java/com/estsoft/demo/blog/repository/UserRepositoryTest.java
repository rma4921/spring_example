package com.estsoft.demo.blog.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.estsoft.demo.blog.domain.User;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {

        String email = "mockEmail";
        String password = "mockPassword";
        User savedUser = userRepository.save(new User(email, password));

        User returnUser = userRepository.findByEmail(email)
            .orElseThrow();

        assertEquals(savedUser.getId(), returnUser.getId());
        assertEquals(savedUser.getEmail(), returnUser.getEmail());
        assertEquals(savedUser.getPassword(), returnUser.getPassword());
    }

    @Disabled
    @Test
    public void testFindAll() {
        String email1 = "mockEmail1";
        String password1 = "mockPassword1";
        String email2 = "mockEmail2";
        String password2 = "mockPassword2";

        userRepository.save(new User(email1, password1));
        userRepository.save(new User(email2, password2));

        List<User> users = userRepository.findAll();

        assertEquals(2, users.size());
        assertTrue(users.contains(userRepository.findByEmail(email1)));
        assertTrue(users.contains(userRepository.findByEmail(email2)));
    }

    @Test
    public void testSave() {
        User user = new User("mockEmail", "mockPassword");

        User savedUser = userRepository.save(user);

        assertEquals(savedUser.getId(), user.getId());
        assertEquals(savedUser.getEmail(), user.getEmail());
        assertEquals(savedUser.getPassword(), user.getPassword());
    }
}