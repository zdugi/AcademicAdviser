package com.academic.adviser.service;

import com.academic.adviser.model.Major;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CareerTestServiceUnitTest {
    @Autowired
    private CareerTestService careerTestService;

    @Test
    @Transactional
    public void testSubmitTest() {

    }
}
