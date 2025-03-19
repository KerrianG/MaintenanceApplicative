package main.Affichage;

import main.CalendarManager;
import main.Utilisateur.*;
import main.Evenement.*;
import main.Evenements;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.function.Supplier;

public class ActionMenu {
    private final Scanner scanner;
    private final Utilisateurs utilisateurs;
    private final CalendarManager calendar;
    private final Map<String, Supplier<Utilisateur>> actionsLog;
    private final Map<Integer, Runnable> actions;
    private final Map<Integer, Runnable> visualisationActions;

    public ActionMenu(Scanner scanner, Utilisateurs utilisateurs, CalendarManager calendar) {
        this.scanner = scanner;
        this.utilisateurs = utilisateurs;
        this.calendar = calendar;
        this.actions = new HashMap<>();
        this.actionsLog = new HashMap<>();
        this.visualisationActions = new HashMap<>();
        initializeActionsLog();
        initializeActions();
        initializeVisualisationActions();
    }

    private void initializeVisualisationActions() {
        visualisationActions.put(1, this::visualiserTousLesEvenements);
        visualisationActions.put(2, this::visualiserEvenementsMois);
        visualisationActions.put(3, this::visualiserEvenementsSemaine);
        visualisationActions.put(4, this::visualiserEvenementsJour);
        visualisationActions.put(5, () -> System.out.println("Retour au menu précédent"));
    }

    private void initializeActions() {
        actions.put(1, this::visualiserEvenements);
        actions.put(2, this::ajouterRdvPersonnel);
        actions.put(3, this::ajouterReunion);
        actions.put(4, this::ajouterEvenementPeriodique);
    }

    private void initializeActionsLog() {
        actionsLog.put("1", this::connexion);
        actionsLog.put("2", this::enregistrement);
    }

    public Utilisateur executeActionLog(String actionKey) {
        Supplier<Utilisateur> action = actionsLog.get(actionKey);
        if (action != null) {
            return action.get();
        } else {
            System.out.println("Action non reconnue.");
            return null;
        }
    }

    public void executeAction(int actionKey) {
        Runnable action = actions.get(actionKey);
        if (action != null) {
            action.run();
        } else {
            System.out.println("Action non reconnue.");
        }
    }

    public Utilisateur connexion() {
        AffichageConnexion affichageConnexion = new AffichageConnexion(scanner);
        String nomUtilisateurStr = affichageConnexion.demanderNomUtilisateur();
        NomUtilisateur nomUtilisateur = new NomUtilisateur(nomUtilisateurStr);
        String motDePasseStr = affichageConnexion.demanderMotDePasse();
        MotDePasse motDePasse = new MotDePasse(motDePasseStr);

        Utilisateur utilisateur = utilisateurs.trouverUtilisateur(nomUtilisateur, motDePasse);
        if (utilisateur == null) {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
        }
        return utilisateur;
    }

    public Utilisateur enregistrement() {
        AffichageConnexion affichageConnexion = new AffichageConnexion(scanner);
        String nomUtilisateurStr = affichageConnexion.demanderNomUtilisateur();
        NomUtilisateur nomUtilisateur = new NomUtilisateur(nomUtilisateurStr);
        if (utilisateurs.existeUtilisateur(nomUtilisateur)) {
            System.out.println("Nom d'utilisateur déjà pris.");
            return null;
        }
        String motDePasseStr = affichageConnexion.demanderMotDePasse();
        MotDePasse motDePasse = new MotDePasse(motDePasseStr);
        System.out.print("Répéter mot de passe: ");
        if (scanner.nextLine().equals(motDePasse.getMotDePasse())) {
            Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse);
            utilisateurs.ajouterUtilisateur(utilisateur);
            return utilisateur;
        } else {
            System.out.println("Les mots de passes ne correspondent pas...");
            return null;
        }
    }

    private void visualiserEvenements() {
        int choixVisualisation = new AffichagePrincipal(scanner).afficherMenuVisualisation();
        Runnable action = visualisationActions.get(choixVisualisation);
        if (action != null) {
            action.run();
        } else {
            System.out.println("Choix non reconnu.");
        }
    }

    private void ajouterRdvPersonnel() {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());

        calendar.ajouterEvent("RDV_PERSONNEL", titre, "", LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree, "", null, 0);

        System.out.println("Événement ajouté.");
    }

    private void ajouterReunion() {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());
        System.out.println("Lieu :");
        String lieu = scanner.nextLine();

        List<Participant> participants = new ArrayList<>();
        participants.add(new Participant(""));

        boolean encore = true;
        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().equals("oui")) {
            System.out.print("Participants : " + participants);
            participants.add(new Participant(scanner.nextLine()));
        }

        calendar.ajouterEvent("REUNION", titre, "", LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree, lieu, participants, 0);

        System.out.println("Événement ajouté.");
    }

    private void ajouterEvenementPeriodique() {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Frequence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());

        calendar.ajouterEvent("PERIODIQUE", titre, "", LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), 0, "", null, frequence);

        System.out.println("Événement ajouté.");
    }

    private void afficherListe(Evenements evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }

    private void visualiserTousLesEvenements() {
        calendar.afficherEvenements();
    }

    private void visualiserEvenementsMois() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
    }

    private void visualiserEvenementsSemaine() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeSemaine = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le numéro de semaine (1-52) : ");
        int semaine = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
    }

    private void visualiserEvenementsJour() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int moisJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
    }
}