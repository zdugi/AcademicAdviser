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
public class RecommendationServiceUnitTest {
    @Autowired
    private RecommendationService recommendationService;

    @Test
    @Transactional
    public void testGetMajors() {
        for (Major major : recommendationService.getMajors())
            System.out.println(major.getName() + "; " + major.getCity().getName());
    }
}
