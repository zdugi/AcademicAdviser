package com.academic.adviser;

import com.academic.adviser.rule.BigFiveRuleUnitTest;
import com.academic.adviser.rule.CareerTestRuleUnitTest;
import com.academic.adviser.rule.DormRecommendationRuleUnitTest;
import com.academic.adviser.rule.MajorRecommendationRuleUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@SuiteClasses({
		BigFiveRuleUnitTest.class,
		CareerTestRuleUnitTest.class,
		DormRecommendationRuleUnitTest.class,
		MajorRecommendationRuleUnitTest.class
})
class AdviserApplicationTests {

}
