package com.arun.controller;

import com.arun.dal.UserRepository;
import com.arun.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetUser() {

        User user = mock(User.class);
        user.setUserId("1");
        when(userRepository.findById("1").get()).thenReturn(user);
        User user1 = userController.getUser("1");
        verify(userRepository).findById("1").get();
        assertEquals("1", user1.getUserId());
        // when(userRepository.findById("1")).thenReturn(user);


    }
}
