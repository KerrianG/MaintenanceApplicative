package main.Evenement;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class Periodique implements TypeEvenement {
    @Override
    public String description(Event event) {
        return "Événement périodique : " + event.title.getTitre() + " tous les " + event.frequenceJours.getFrequence() + " jours";
    }

    @Override
    public boolean isConflict(Event e1, Event e2) {
        return false; // Simplification abusive
    }

    @Override
    public boolean isWithinPeriod(Event event, LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime temp = event.dateDebut.getDateDebut();
        while (temp.isBefore(fin)) {
            if (!temp.isBefore(debut)) {
                return true;
            }
            temp = temp.plusDays(event.frequenceJours.getFrequence());
        }
        return false;
    }

    @Override
    public String getType() {
        return "PERIODIQUE";
    }
}