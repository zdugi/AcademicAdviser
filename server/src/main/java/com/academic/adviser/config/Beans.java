package com.academic.adviser.config;

import com.academic.adviser.service.BigFiveService;
import com.academic.adviser.service.impl.BigFiveServiceImpl;
import com.academic.adviser.service.impl.UserDetailsServiceImpl;
import com.academic.adviser.constants.Gender;
import com.academic.adviser.drools.model.CareerTestNorm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class Beans {
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public KieContainer getKieContainerInstance() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        // kScanner.start(10_000);
        return kContainer;
    }

    @Bean
    public UserDetailsServiceImpl getUserDetailsServiceInstance() {
        return new UserDetailsServiceImpl();
    }

    @Bean
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
}
