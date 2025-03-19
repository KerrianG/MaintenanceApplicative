package main.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class Utilisateurs {
    private final List<Utilisateur> utilisateurs;

    public Utilisateurs() {
        this.utilisateurs = new ArrayList<>();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        if (existeUtilisateur(utilisateur.getNomUtilisateur())) {
            throw new IllegalArgumentException("Nom d'utilisateur déjà pris.");
        }
        utilisateurs.add(utilisateur);
    }

    public Utilisateur trouverUtilisateur(NomUtilisateur nomUtilisateur, MotDePasse motDePasse) {
        return utilisateurs.stream()
                .filter(u -> u.getNomUtilisateur().equals(nomUtilisateur) && u.getMotDePasse().equals(motDePasse))
                .findFirst()
                .orElse(null);
    }

    public boolean existeUtilisateur(NomUtilisateur nomUtilisateur) {
        return utilisateurs.stream()
                .anyMatch(u -> u.getNomUtilisateur().equals(nomUtilisateur));
    }
}