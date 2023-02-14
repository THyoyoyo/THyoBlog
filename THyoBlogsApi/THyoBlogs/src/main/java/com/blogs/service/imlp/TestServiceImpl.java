package com.blogs.service.imlp;

import com.blogs.mapper.test.TestMapper;
import com.blogs.model.test.Test;
import com.blogs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


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



    private final Lock lock = new ReentrantLock();
    @Override
    public Boolean instantCommodity() {
        lock.lock();
        try {
            Test test = testMapper.selectById(1);
            if (test.getNum()>0){
                test.setNum(test.getNum()-1);
                testMapper.updateById(test);
                return true;
            }
            return false;
        }finally {
            lock.unlock();
        }
    }
}
