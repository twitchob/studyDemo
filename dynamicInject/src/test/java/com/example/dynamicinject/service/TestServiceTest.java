package com.example.dynamicinject.service;

import com.example.dynamicinject.controller.TestController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = TestServiceTest.class)

class TestServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void method1() {
        TestController testController = new TestController();
        testController.test();
    }

    @Test
    void method2() {
    }
}