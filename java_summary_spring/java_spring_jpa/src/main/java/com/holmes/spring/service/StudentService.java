package com.holmes.spring.service;

import com.holmes.spring.dao.StudentDao;
import com.holmes.spring.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> selectList() {

        List<Student> list = studentDao.selectList();
        return list;
    }
}
