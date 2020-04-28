package com.playground;

import com.playground.wizard.Wizard;
import com.playground.wizard.WizardStep;
import com.playground.wizard.WizardStepDefault;
import com.playground.wizard.WizardState;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        performWizardDemo();
    }

    private static void performWizardDemo() {
        Wizard wizard = createWizard();
        out.println("step name = " + wizard.getCurrentStep().getName());

        out.println("next");
        wizard.next(
                new WizardState()
                        .append("setting key 1", "setting value 1")
                        .append("setting key 2", "setting value 2")
        );
        out.println("step name = " + wizard.getCurrentStep().getName());

        out.println("back");
        wizard.back();
        out.println("step name = " + wizard.getCurrentStep().getName());

        out.println("next");
        wizard.next(
                new WizardState()
                        .append("setting key 1", "setting value 3")
                        .append("setting key 2", "setting value 4")
        );
        out.println("step name = " + wizard.getCurrentStep().getName());

        out.println("next");
        wizard.next(null);
        out.println("step name = " + wizard.getCurrentStep().getName());

        out.println("next");
        wizard.next(
                new WizardState()
                        .append("setting key 3", "setting value 5")
                        .append("setting key 4", "setting value 6")
        );
        out.println("step name = " + wizard.getCurrentStep().getName());

        WizardState summaryData = wizard.getSummaryData();
        String result = summaryData.toJsonString();
        out.println("result config = " + result);
    }

    private static Wizard createWizard() {
        return new Wizard(
                new WizardStepDefault("firstStep")

                        .addNextStep(

                                new WizardState()
                                        .append("setting key 1", "setting value 1")
                                        .append("setting key 2", "setting value 2"),

                                new WizardStepDefault("secondStep1")

                        )

                        .addNextStep(

                                new WizardState()
                                        .append("setting key 1", "setting value 3")
                                        .append("setting key 2", "setting value 4"),

                                new WizardStepDefault("secondStep2")
                                        .setDefaultNextStep(
                                                new WizardStepDefault("Last step")
                                                .addNextStep(
                                                        new WizardState()
                                                                .append("setting key 3", "setting value 5")
                                                                .append("setting key 4", "setting value 6"),
                                                        new WizardStepDefault("thirdStep")
                                                )
                                        )

                        )

                        .setDefaultNextStep(
                                new WizardStepDefault("secondStepDefault")
                        )
        );

    }

}
