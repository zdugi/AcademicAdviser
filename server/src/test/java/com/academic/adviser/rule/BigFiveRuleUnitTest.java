package com.academic.adviser.rule;

import com.academic.adviser.constants.BigFiveTraitLevel;
import com.academic.adviser.dto.BigFiveAnswerDTO;
import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.CareerTestDTO;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.POQuestion;
import com.academic.adviser.model.QuestionPair;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.rule.impl.BigFiveRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.CharArrayReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigFiveRuleUnitTest {

    @MockBean
    private QuestionPairRepository questionPairRepository;

    @MockBean
    private CareerAreaRepository careerAreaRepository;

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
                    add(BigFiveTraitLevel.LOW_CONSCIENTIOUSNESS);
                }}, 120));
        when(careerAreaRepository.findAll()).thenReturn(careerAreas);
        when(questionPairRepository.getOne(anyInt())).thenReturn(new QuestionPair(
                1,
                new POQuestion(1, "dummy A", null),
                new POQuestion(2, "dummy B", null)));
    }


    @Test
    public void testRunRule() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));

        ArrayList<BigFiveAnswerDTO> bigFiveAnswerDTOS = new ArrayList<>();

        for (int i = 0; i <= 50; i++)
            bigFiveAnswerDTOS.add(new BigFiveAnswerDTO(i, 5));

        BigFiveSurveyAnswersDTO answers = new BigFiveSurveyAnswersDTO(bigFiveAnswerDTOS);

        BigFiveRule rule = new BigFiveRule(
                kContainer,
                answers,
                questionPairRepository,
                careerAreaRepository,
                1.0,
                1.0,
                1.0,
                1.0,
                1.0);

        CareerTestDTO careerTestDTO = (CareerTestDTO) rule.runRule();
        assertEquals(careerTestDTO.getQuestions().size(), 14);
    }
}
