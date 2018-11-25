package com.holmes.spring.service;

import com.holmes.spring.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentServiceTest extends BaseTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void selectListTest() {
        System.out.println(studentService.selectList());
    }
}
