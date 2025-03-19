package main;

import main.Evenement.*;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarManager {
    public Evenements events;

    public CalendarManager() {
        this.events = new Evenements();
    }

    public void ajouterEvent(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes,
                             String lieu, List<Participant> participants, int frequenceJours) {

        TypeEvenement typeEvenement = TypeEvenementRegistre.getEventType(type);

        Event e = new Event(typeEvenement, new TitreEvenement(title), new ProprietaireEvenement(proprietaire),
                new DateEvenement(dateDebut), new DureeEvenement(dureeMinutes), new LieuEvenement(lieu),
                new Participants(participants), new FrequenceEvenement(frequenceJours));
        events.addEvent(e);
    }

    public Evenements eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        Evenements result = new Evenements();
        for (Event e : events) {
            if(e.type.isWithinPeriod(e, debut, fin)) {
                result.addEvent(e);
            }
        }
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        return e1.type.isConflict(e1, e2);
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

}