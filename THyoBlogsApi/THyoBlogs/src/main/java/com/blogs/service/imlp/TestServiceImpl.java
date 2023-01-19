package com.blogs.service.imlp;

import com.blogs.mapper.test.TestMapper;
import com.blogs.model.test.Test;
import com.blogs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {

   @Autowired
    TestMapper testMapper;

    @Override
    public Test getUserId(Integer id) {
        Test test = testMapper.selectById(id);
        return test;
    }

    @Override
    public void adduser(Test user) {
           testMapper.insert(user);
    }
}
