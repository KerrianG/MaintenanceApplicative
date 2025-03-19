package main.Evenement;

public class Event {

    public static final String RDV_PERSONNEL = "RDV_PERSONNEL";
    public static final String REUNION = "REUNION";
    public static final String PERIODIQUE = "PERIODIQUE";
    public static final String WEBINAR = "WEBINAR";


    public TypeEvenement type; // "RDV_PERSONNEL", "REUNION", "PERIODIQUE"
    public TitreEvenement title;
    public ProprietaireEvenement proprietaire;
    public DateEvenement dateDebut;
    public DureeEvenement dureeMinutes;
    public LieuEvenement lieu; // utilisé seulement pour REUNION
    public Participants participants; // séparés par virgules (pour REUNION uniquement)
    public FrequenceEvenement frequenceJours; // uniquement pour PERIODIQUE

    public Event(TypeEvenement type, TitreEvenement title, ProprietaireEvenement proprietaire, DateEvenement dateDebut, DureeEvenement dureeMinutes,
                 LieuEvenement lieu, Participants participants, FrequenceEvenement frequenceJours) {
        this.type = type;
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.lieu = lieu;
        this.participants = participants;
        this.frequenceJours = frequenceJours;
    }

    public String description() {
        return type.description(this);
    }
}