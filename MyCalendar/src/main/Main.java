package main;

import main.Affichage.ActionMenu;
import main.Affichage.AffichagePrincipal;
import main.Utilisateur.Utilisateur;
import main.Utilisateur.Utilisateurs;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);
        Utilisateur utilisateur = null;
        boolean continuer = true;

        Utilisateurs utilisateurs = new Utilisateurs();
        AffichagePrincipal affichagePrincipal = new AffichagePrincipal(scanner);
        ActionMenu actionMenu = new ActionMenu(scanner, utilisateurs, calendar);

        while (true) {
            if (utilisateur == null) {
                affichagePrincipal.afficher();
                String choix = String.valueOf(affichagePrincipal.afficherMenuPrincipal());
                utilisateur = actionMenu.executeActionLog(choix);
            }

            while (continuer && utilisateur != null) {
                System.out.println("\nBonjour, " + utilisateur.getNomUtilisateur().getNom());
                int choix = affichagePrincipal.afficherMenuGestionnaire();
                actionMenu.executeAction(choix);

                if (choix == 5) {
                    System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
                    continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");
                    utilisateur = null;
                }
            }
        }
    }
}