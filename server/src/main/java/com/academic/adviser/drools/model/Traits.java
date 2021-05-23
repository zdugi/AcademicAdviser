package com.academic.adviser.drools.model;

import com.academic.adviser.constants.BigFiveTraitLevel;

import java.util.List;

public class Traits {
    private List<BigFiveTraitLevel> traits;

    public Traits(List<BigFiveTraitLevel> traits) {
        this.traits = traits;
    }

    public void addTrait(BigFiveTraitLevel trait) { traits.add(trait); }

    public List<BigFiveTraitLevel> getTraits() { return this.traits; }
}
