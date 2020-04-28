package com.playground.wizard;

public interface WizardStep {

    String getName();

    WizardState getCurrentState();

    WizardStep addNextStep(WizardState wizardState, WizardStep wizardStep);

    WizardStep setDefaultNextStep(WizardStep defaultNextStep);

    WizardStep setPreviousStep(WizardStep wizardStep);

    WizardStep getPreviousStep();

    WizardStep getNextStep(WizardState wizardState);

}
