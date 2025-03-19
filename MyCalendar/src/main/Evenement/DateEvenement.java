package main.Evenement;

import java.time.LocalDateTime;

public class DateEvenement {

    private final LocalDateTime dateDebut;

    public DateEvenement(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }
}
