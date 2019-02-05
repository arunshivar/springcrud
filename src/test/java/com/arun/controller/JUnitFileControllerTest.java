package com.arun.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class JUnitFileControllerTest {

    @Test
    public void testFileController() {

        FileUploadController fileUploadController = new FileUploadController();
        String result = fileUploadController.hello();
        assertEquals(result, "Hello World");

    }

}
