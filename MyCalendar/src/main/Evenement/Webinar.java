package main.Evenement;

import java.time.LocalDateTime;

public class Webinar implements TypeEvenement {
    @Override
    public String description(Event event) {
        return "Webinar : " + event.title.getTitre() + " en ligne à " + event.dateDebut.getDateDebut().toString();
    }

    @Override
    public boolean isConflict(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.getDateDebut().plusMinutes(e1.dureeMinutes.getDuree());
        LocalDateTime fin2 = e2.dateDebut.getDateDebut().plusMinutes(e2.dureeMinutes.getDuree());
        return e1.dateDebut.getDateDebut().isBefore(fin2) && fin1.isAfter(e2.dateDebut.getDateDebut());
    }

    @Override
    public boolean isWithinPeriod(Event event, LocalDateTime debut, LocalDateTime fin) {
        return !event.dateDebut.getDateDebut().isBefore(debut) && !event.dateDebut.getDateDebut().isAfter(fin);
    }

    @Override
    public String getType() {
        return "WEBINAR";
    }
}