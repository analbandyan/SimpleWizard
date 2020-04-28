package com.playground.wizard;

import java.util.HashMap;
import java.util.Map;

public class WizardStepDefault implements WizardStep {

    private final String name;
    private WizardState currentState;
    private WizardStep previousStep;
    private final Map<WizardState, WizardStep> possibleNextSteps = new HashMap<>();
    private WizardStep defaultNextStep;

    public WizardStepDefault(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public WizardState getCurrentState() {
        return currentState;
    }

    @Override
    public WizardStep addNextStep(WizardState wizardState, WizardStep wizardStep) {
        possibleNextSteps.put(wizardState, wizardStep);
        return this;
    }

    @Override
    public WizardStep setDefaultNextStep(WizardStep defaultNextStep) {
        this.defaultNextStep = defaultNextStep;
        return this;
    }

    @Override
    public WizardStep setPreviousStep(WizardStep previousStep) {
        this.previousStep = previousStep;
        return this;
    }

    @Override
    public WizardStep getPreviousStep() {
        return previousStep;
    }

    @Override
    public WizardStep getNextStep(WizardState wizardState) {
        currentState = wizardState;

        WizardStep nextStep;

        if (currentState != null) {
            nextStep = possibleNextSteps.get(currentState);
            if (nextStep == null) {
                nextStep = defaultNextStep;
            }
        } else {
            nextStep = defaultNextStep;
        }


        if (nextStep != null) {
            nextStep.setPreviousStep(this);
        }

        return nextStep;
    }

}
