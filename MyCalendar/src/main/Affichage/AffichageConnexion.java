package main.Affichage;

import java.util.Scanner;

public class AffichageConnexion {
    private final Scanner scanner;

    public AffichageConnexion(Scanner scanner) {
        this.scanner = scanner;
    }

    public String demanderNomUtilisateur() {
        System.out.print("Nom d'utilisateur: ");
        return scanner.nextLine();
    }

    public String demanderMotDePasse() {
        System.out.print("Mot de passe: ");
        return scanner.nextLine();
    }
}