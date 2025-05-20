package com.estsoft.demo.blog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.estsoft.demo.blog.domain.User;
import com.estsoft.demo.blog.dto.AddUserRequest;
import com.estsoft.demo.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder encoder;

    @Test
    public void testSignUp() {
        AddUserRequest request = new AddUserRequest();
        request.setEmail("mockEmail");
        request.setPassword("mockPassword");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Mockito.when(userRepository.save(any()))
            .thenReturn(new User(request.getEmail(), passwordEncoder.encode(request.getPassword())));

        userService.signUp(request);

        Mockito.verify(userRepository, Mockito.times(1)).save(any());
        Mockito.verify(encoder, Mockito.times(1)).encode(any());
    }
}