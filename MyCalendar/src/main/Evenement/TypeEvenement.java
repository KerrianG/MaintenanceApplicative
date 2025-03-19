package main.Evenement;

import java.time.LocalDateTime;

public interface TypeEvenement {
    String description(Event event);
    boolean isConflict(Event e1, Event e2);
    boolean isWithinPeriod(Event event, LocalDateTime debut, LocalDateTime fin);
    String getType();
}