package com.ua.glebkorobov.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Test;

public class UserBeanTest extends TestCase {

    @Test
    public void testUser() throws JsonProcessingException {
        UserBean userBean = new UserBean("John");

        String resultJson = new ObjectMapper().writeValueAsString(userBean);
        System.out.println(resultJson);

    }

}