package com.playground.wizard;

public class Wizard {

    private WizardStep currentStep;

    public Wizard(WizardStep currentStep) {
        this.currentStep = currentStep;
    }

    public WizardStep getCurrentStep() {
        return currentStep;
    }

    public Wizard next(WizardState wizardState) {
        WizardStep next = currentStep.getNextStep(wizardState);
        if(next != null) {
            currentStep = next;
            return this;
        }
        return null;
    }

    public Wizard back() {
        WizardStep previous = currentStep.getPreviousStep();
        if(previous != null) {
            currentStep = previous;
            return this;
        }
        return null;
    }

    public WizardState getSummaryData() {
        WizardState state = new WizardState();

        WizardStep step = currentStep;
        do {
            state = state.merge(step.getCurrentState());
            step = step.getPreviousStep();
        } while (step != null);

        return state;

    }

}
