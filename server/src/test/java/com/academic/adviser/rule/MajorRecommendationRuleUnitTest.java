package com.academic.adviser.rule;

import com.academic.adviser.constants.BigFiveTraitLevel;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.City;
import com.academic.adviser.model.Major;
import com.academic.adviser.repository.MajorRepository;
import com.academic.adviser.rule.impl.MajorRecommendationRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorRecommendationRuleUnitTest {
    @MockBean
    private MajorRepository majorRepository;

    @Before
    public void init() {
        HashSet<CareerArea> areas = new HashSet<>() {{
            add(new CareerArea(1, "Area 1", new ArrayList<BigFiveTraitLevel>(){{
                add(BigFiveTraitLevel.HIGH_AGREEABLENESS);
                add(BigFiveTraitLevel.HIGH_CONSCIENTIOUSNESS);
            }}, 90));
        }};

        when(majorRepository.findAll()).thenReturn(new ArrayList<>() {{
            add(new Major(
                    1,
                    "Major A",
                    20,
                    areas,
                    new City(0, "Beograd", 55356.0, 40000.0)));
            add(new Major(
                    2,
                    "Major B",
                    10,
                    areas,
                    new City(0, "Beograd", 55356.0, 40000.0)));
            add(new Major(
                    2,
                    "Major C",
                    10,
                    areas,
                    new City(1, "Novi Sad", 55356.0, 40000.0)));
        }});
    }

    @Test
    public void testRunRole() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));

        CareerArea finalArea = new CareerArea(1, "Area 1", new ArrayList<BigFiveTraitLevel>(){{
            add(BigFiveTraitLevel.HIGH_AGREEABLENESS);
            add(BigFiveTraitLevel.HIGH_CONSCIENTIOUSNESS);
        }}, 90);
        City desiredCity = new City(0, "Beograd", 55356.0, 40000.0);
        Candidate candidate = new Candidate(4.5);

        MajorRecommendationRule majorRecommendationRule = new MajorRecommendationRule(
            kContainer,
            majorRepository,
            finalArea,
            desiredCity,
            candidate);

        List<Major> majors = (List<Major>) majorRecommendationRule.runRule();
        assertEquals(majors.size(), 2);
    }
}
