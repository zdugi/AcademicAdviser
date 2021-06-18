package com.academic.adviser.rule.impl;

import com.academic.adviser.drools.model.BigFiveFlyAnswer;
import com.academic.adviser.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class BigFiveCertaintyRule {
    private KieSession session;

    public BigFiveCertaintyRule(KieContainer kContainer) {
        this.session = kContainer.newKieSession("ksession-certainty-rules");
    }

    public void update(Object value, boolean execute) {
        session.insert(value);

        if (execute)
            session.fireAllRules();
    }
}
