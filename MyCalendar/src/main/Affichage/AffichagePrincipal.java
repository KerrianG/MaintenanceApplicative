package main.Affichage;

import java.util.Scanner;

public class AffichagePrincipal {
    private final AffichageEnTete affichageEnTete;
    private final AffichageMenu affichageMenu;
    private final Scanner scanner;

    public AffichagePrincipal(Scanner scanner) {
        this.scanner = scanner;
        this.affichageEnTete = new AffichageEnTete();
        this.affichageMenu = new AffichageMenu(scanner);
    }

    public void afficher() {
        affichageEnTete.afficher();
    }

    public int afficherMenuPrincipal() {
        return affichageMenu.afficherMenuPrincipal();
    }

    public int afficherMenuGestionnaire() {
        return affichageMenu.afficherMenuGestionnaire();
    }

    public int afficherMenuVisualisation() {
        return affichageMenu.afficherMenuVisualisation();
    }
}