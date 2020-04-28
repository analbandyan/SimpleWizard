package com.playground.wizard;

import org.json.JSONArray;
import org.json.JSONObject;

public class WizardState {

    private final JSONObject state = new JSONObject();

    public WizardState append(String key, Object value) {
        state.append(key, value);
        return this;
    }

    public String toJsonString() {
        return this.toString();
    }

    public WizardState merge(WizardState other) {
        WizardState result = new WizardState();

        append(result, this);
        append(result, other);

        return result;
    }

    private static void append(WizardState result, WizardState toAppend) {
        if(toAppend == null) {
            return;
        }

        for (String key : toAppend.state.keySet()) {
            result.state.append(
                    key,
                    ((JSONArray)toAppend.state.get(key)).get(0)
            );
        }
    }

    @Override
    public String toString() {
        return state.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WizardState)) {
            return false;
        }

        WizardState other = (WizardState)obj;

        return this.state.toString().equals(other.state.toString());
    }

    @Override
    public int hashCode() {
        return this.state.toString().hashCode();
    }
}
