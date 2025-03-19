package main.Evenement;

public class Event {
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
        String desc = "";
        if (type.equals("RDV_PERSONNEL")) {
            desc = "RDV : " + title.getTitre() + " à " + dateDebut.getDateDebut().toString();
        } else if (type.equals("REUNION")) {
            desc = "Réunion : " + title.getTitre() + " à " + lieu.getLieu() + " avec " + participants.getParticipants();
        } else if (type.equals("PERIODIQUE")) {
            desc = "Événement périodique : " + title.getTitre() + " tous les " + frequenceJours.getFrequence() + " jours";
        }
        return desc;
    }
}