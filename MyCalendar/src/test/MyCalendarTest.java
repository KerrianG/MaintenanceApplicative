package test;

import main.*;
import main.Evenement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyCalendarTest {
    private CalendarManager calendarManager;

    @BeforeEach
    public void setUp() {
        calendarManager = new CalendarManager();
    }

    private Event createEvent(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes,
                              String lieu, List<String> participants, int frequenceJours) {
        TypeEvenement typeEvenement = new TypeEvenement(type);
        TitreEvenement titre = new TitreEvenement(title);
        ProprietaireEvenement proprietaireEvenement = new ProprietaireEvenement(proprietaire);
        DateEvenement dateEvenement = new DateEvenement(dateDebut);
        DureeEvenement dureeEvenement = new DureeEvenement(dureeMinutes);
        LieuEvenement lieuEvenement = new LieuEvenement(lieu);
        Participants participantsEvenement = new Participants(participants.stream().map(Participant::new).toList());
        FrequenceEvenement frequenceEvenement = new FrequenceEvenement(frequenceJours);

        return new Event(typeEvenement, titre, proprietaireEvenement, dateEvenement, dureeEvenement, lieuEvenement, participantsEvenement, frequenceEvenement);
    }

    @Test
    public void testAjouterEvent() {
        Event event = createEvent("REUNION", "Réunion de projet", "Alice",
                LocalDateTime.of(2023, 10, 10, 10, 0), 60, "Salle de conférence",
                List.of("Bob", "Charlie"), 0);

        calendarManager.ajouterEvent(event.type.getType(), event.title.getTitre(), event.proprietaire.getProprietaire(),
                event.dateDebut.getDateDebut(), event.dureeMinutes.getDuree(), event.lieu.getLieu(),
                event.participants.getParticipants(), event.frequenceJours.getFrequence());

        assertEquals(1, calendarManager.events.size());
        Event addedEvent = calendarManager.events.getEvent(0);
        assertEquals("Réunion de projet", addedEvent.title.getTitre());
        assertEquals("Alice", addedEvent.proprietaire.getProprietaire());
        assertEquals(LocalDateTime.of(2023, 10, 10, 10, 0), addedEvent.dateDebut.getDateDebut());
        assertEquals(60, addedEvent.dureeMinutes.getDuree());
        assertEquals("Salle de conférence", addedEvent.lieu.getLieu());

        List<String> expectedParticipants = List.of("Bob", "Charlie");
        List<String> actualParticipants = addedEvent.participants.getParticipants().stream()
                .map(Participant::getParticipant)
                .toList();
        assertEquals(expectedParticipants, actualParticipants);
    }

    @Test
    public void testEventsDansPeriode() {
        Event event = createEvent("REUNION", "Réunion de projet", "Alice",
                LocalDateTime.of(2023, 10, 10, 10, 0), 60, "Salle de conférence",
                List.of("Bob", "Charlie"), 0);

        calendarManager.ajouterEvent(event.type.getType(), event.title.getTitre(), event.proprietaire.getProprietaire(),
                event.dateDebut.getDateDebut(), event.dureeMinutes.getDuree(), event.lieu.getLieu(),
                event.participants.getParticipants(), event.frequenceJours.getFrequence());

        Evenements events = calendarManager.eventsDansPeriode(LocalDateTime.of(2023, 10, 1, 0, 0), LocalDateTime.of(2023, 10, 31, 23, 59));
        assertEquals(1, events.size());
    }

    @Test
    public void testConflit() {
        Event event1 = createEvent("REUNION", "Réunion de projet", "Alice",
                LocalDateTime.of(2023, 10, 10, 10, 0), 60, "Salle de conférence",
                List.of("Bob", "Charlie"), 0);

        Event event2 = createEvent("RDV_PERSONNEL", "RDV médical", "Alice",
                LocalDateTime.of(2023, 10, 10, 10, 30), 30, "Clinique",
                List.of(), 0);

        assertTrue(calendarManager.conflit(event1, event2));
    }
}