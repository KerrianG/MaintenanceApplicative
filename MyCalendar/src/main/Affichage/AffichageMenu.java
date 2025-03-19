package main.Affichage;

import java.util.Scanner;

public class AffichageMenu {
    private final Scanner scanner;

    public AffichageMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public int afficherMenuPrincipal() {
        System.out.println("1 - Se connecter");
        System.out.println("2 - Créer un compte");
        System.out.print("Choix : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int afficherMenuGestionnaire() {
        System.out.println("\n=== Menu Gestionnaire d'Événements ===");
        System.out.println("1 - Voir les événements");
        System.out.println("2 - Ajouter un rendez-vous perso");
        System.out.println("3 - Ajouter une réunion");
        System.out.println("4 - Ajouter un évènement périodique");
        System.out.println("5 - Se déconnecter");
        System.out.print("Votre choix : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int afficherMenuVisualisation() {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");
        return Integer.parseInt(scanner.nextLine());
    }
}