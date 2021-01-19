package ai.fpt.speech.model;

import java.util.ArrayList;

public class RecognizeResponse {
    private ArrayList<Intent> intents;
    private ArrayList<Entity> entities;

    public ArrayList<Intent> getIntents() {
        return intents;
    }

    public void setIntents(ArrayList<Intent> intents) {
        this.intents = intents;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
