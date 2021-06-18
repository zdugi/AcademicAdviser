package com.academic.adviser.rule;

import com.academic.adviser.constants.BigFiveTraitLevel;
import com.academic.adviser.constants.Gender;
import com.academic.adviser.drools.model.CareerTestNorm;
import com.academic.adviser.dto.CareerTestAnswerDTO;
import com.academic.adviser.dto.QuestionPairAnswerDTO;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.POQuestion;
import com.academic.adviser.model.QuestionPair;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.rule.impl.CareerTestRule;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CareerTestRuleUnitTest {
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private QuestionPairRepository questionPairRepository;

    @MockBean
    private CareerAreaRepository careerAreaRepository;

    public List<CareerTestNorm> getNorms() {
        List<CareerTestNorm> careerTestNorms = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(
                    resourceLoader.getResource("classpath:norm.json").getFile());
            if (root.isArray()) {
                for (JsonNode element : root) {
                    Map<String, Integer> normMap = objectMapper.convertValue(
                            element.get("norms"), new TypeReference<>() {
                            });
                    for(String key : normMap.keySet()) {
                        careerTestNorms.add(new CareerTestNorm(
                                element.get("gender").textValue().equals("M") ? Gender.MALE : Gender.FEMALE,
                                element.get("careerArea").textValue(),
                                normMap.get(key),
                                Integer.parseInt(key)
                        ));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return careerTestNorms;
    }

    @Before
    public void init() {
        ArrayList<CareerArea> careerAreas = new ArrayList<>();
        careerAreas.add(
                new CareerArea(1, "Area 1", new ArrayList<BigFiveTraitLevel>(){{
                    add(BigFiveTraitLevel.HIGH_AGREEABLENESS);
                    add(BigFiveTraitLevel.HIGH_CONSCIENTIOUSNESS);
                }}, 90));
        careerAreas.add(
                new CareerArea(2, "Area 2", new ArrayList<BigFiveTraitLevel>(){{
                    add(BigFiveTraitLevel.HIGH_AGREEABLENESS);
                    add(BigFiveTraitLevel.HIGH_CONSCIENTIOUSNESS);
                }}, 120));

        ArrayList<QuestionPair> pairs = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            pairs.add(new QuestionPair(
                    i,
                    new POQuestion(1, "dummy A", careerAreas.get(0)),
                    new POQuestion(2, "dummy B", careerAreas.get(1))));

        when(careerAreaRepository.findAll()).thenReturn(careerAreas);
        when(questionPairRepository.findAll()).thenReturn(pairs);
    }

    @Test
    public void testRunRule() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));

        ArrayList<QuestionPairAnswerDTO> answer = new ArrayList<>();
        for (int i = 0; i < 80; i++)
            answer.add(new QuestionPairAnswerDTO(i, 1, 0));
        CareerTestAnswerDTO careerTestAnswerDTO = new CareerTestAnswerDTO(answer);

        Candidate candidate = new Candidate(
                "perica", "12345", 4.5,
                "Petar", "Petrovic", Gender.MALE
        );

        CareerTestRule careerTestRule = new CareerTestRule(
                careerTestAnswerDTO,
                kContainer,
                questionPairRepository,
                careerAreaRepository,
                getNorms(),
                candidate);

        CareerArea area = (CareerArea) careerTestRule.runRule();
        assertEquals(area.getId(), 1);
    }
}
