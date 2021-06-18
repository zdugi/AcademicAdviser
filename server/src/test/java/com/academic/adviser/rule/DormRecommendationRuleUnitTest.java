package com.academic.adviser.rule;

import com.academic.adviser.model.AcademicLife;
import com.academic.adviser.model.*;
import com.academic.adviser.repository.DormitoryRepository;
import com.academic.adviser.rule.impl.DormRecommendationRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DormRecommendationRuleUnitTest {
    @MockBean
    private DormitoryRepository dormitoryRepository;

    private List<Major> majors;

    @Before
    public void init() {
        ArrayList<Candidate> candidatesDorm1 = new ArrayList<>() {{
           add(new Candidate(
                   new BigFiveResults(33, 25, 10, 2, 2)
           ));
           add(new Candidate(
                    new BigFiveResults(35, 35, 10, 10, 10)
           ));
           add(new Candidate(
                    new BigFiveResults(28, 15, 24, 10, 30)
           ));
            add(new Candidate(
                    new BigFiveResults(21, 25, 24, 25, 30)
            ));
        }};

        ArrayList<Candidate> candidatesDorm2 = new ArrayList<>() {{
            add(new Candidate(
                    new BigFiveResults(33, 31, 10, 12, 2)
            ));
            add(new Candidate(
                    new BigFiveResults(35, 35, 10, 9, 10)
            ));
            add(new Candidate(
                    new BigFiveResults(35, 32, 24, 10, 30)
            ));
            add(new Candidate(
                    new BigFiveResults(35, 34, 24, 11, 30)
            ));
        }};

        ArrayList<Dormitory> dormitories = new ArrayList<>() {{
           add(new Dormitory(
                   1,
                   "Dorm A",
                   new City(0, "Beograd", 55356.0, 40000.0) ,
                   candidatesDorm1));
           add(new Dormitory(
                    2,
                    "Dorm B",
                    new City(0, "Beograd", 55356.0, 40000.0) ,
                    candidatesDorm2));
        }};

        when(dormitoryRepository.findAll()).thenReturn(dormitories);

        majors = new ArrayList<>() {{
            add(new Major(1, "Major A", 60, null, new City(0, "Beograd", 55356.0, 40000.0)));
            add(new Major(2, "Major B", 60, null, new City(0, "Beograd", 55356.0, 40000.0)));

        }};
    }

    @Test
    public void testGetDormsMidExtrovert() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));

        Candidate candidate = new Candidate();
        candidate.setBigFiveResults(
                new BigFiveResults(33, 25, 10, 2, 2));

        DormRecommendationRule dormRecommendationRule = new DormRecommendationRule(
                kContainer,
                majors,
                candidate,
                dormitoryRepository
        );

        AcademicLife academicLife = (AcademicLife) dormRecommendationRule.runRule();
        assertEquals(academicLife.getDormitories().size(), 2);
        assertEquals(academicLife.getLifeCost().size(), 1);
    }

    @Test
    public void testGetDormsSimilar() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));

        Candidate candidate = new Candidate();
        candidate.setBigFiveResults(
                new BigFiveResults(20, 18, 10, 2, 2));

        DormRecommendationRule dormRecommendationRule = new DormRecommendationRule(
                kContainer,
                majors,
                candidate,
                dormitoryRepository
        );

        AcademicLife academicLife = (AcademicLife) dormRecommendationRule.runRule();
        assertEquals(academicLife.getDormitories().size(), 1);
        assertEquals(academicLife.getLifeCost().size(), 1);
    }

    @Test
    public void testGetDormsHighExtrovert() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));
        
        Candidate candidate = new Candidate();
        candidate.setBigFiveResults(
                new BigFiveResults(33, 33, 10, 2, 2));

        DormRecommendationRule dormRecommendationRule = new DormRecommendationRule(
                kContainer,
                majors,
                candidate,
                dormitoryRepository
        );

        AcademicLife academicLife = (AcademicLife) dormRecommendationRule.runRule();
        assertEquals(academicLife.getDormitories().size(), 2);
        assertEquals(academicLife.getLifeCost().size(), 0);
    }
}
