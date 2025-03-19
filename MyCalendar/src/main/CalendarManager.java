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

        Event e = new Event(new TypeEvenement(type), new TitreEvenement(title), new ProprietaireEvenement(proprietaire),
                new DateEvenement(dateDebut), new DureeEvenement(dureeMinutes), new LieuEvenement(lieu),
                new Participants(participants), new FrequenceEvenement(frequenceJours));
        events.addEvent(e);
    }

    public Evenements eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        Evenements result = new Evenements();
        for (Event e : events) {
            if (e.type.getType().equals("PERIODIQUE")) {
                LocalDateTime temp = e.dateDebut.getDateDebut();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.addEvent(e);
                        break;
                    }
                    temp = temp.plusDays(e.frequenceJours.getFrequence());
                }
            } else if (!e.dateDebut.getDateDebut().isBefore(debut) && !e.dateDebut.getDateDebut().isAfter(fin)) {
                result.addEvent(e);
            }
        }
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.getDateDebut().plusMinutes(e1.dureeMinutes.getDuree());
        LocalDateTime fin2 = e2.dateDebut.getDateDebut().plusMinutes(e2.dureeMinutes.getDuree());

        if (e1.type.getType().equals("PERIODIQUE") || e2.type.getType().equals("PERIODIQUE")) {
            return false; // Simplification abusive
        }

        if (e1.dateDebut.getDateDebut().isBefore(fin2) && fin1.isAfter(e2.dateDebut.getDateDebut())) {
            return true;
        }
        return false;
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

}