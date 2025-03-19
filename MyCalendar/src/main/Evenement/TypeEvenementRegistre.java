package main.Evenement;

import java.util.HashMap;
import java.util.Map;

public class TypeEvenementRegistre {
    private static final Map<String, TypeEvenement> eventTypeMap = new HashMap<>();

    static {
        eventTypeMap.put(Event.RDV_PERSONNEL, new RdvPersonnel());
        eventTypeMap.put(Event.REUNION, new Reunion());
        eventTypeMap.put(Event.PERIODIQUE, new Periodique());
    }

    public static TypeEvenement getEventType(String type) {
        TypeEvenement eventType = eventTypeMap.get(type);
        if (eventType == null) {
            throw new IllegalArgumentException("Unknown event type: " + type);
        }
        return eventType;
    }
}
