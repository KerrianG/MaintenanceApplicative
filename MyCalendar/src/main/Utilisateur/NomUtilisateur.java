package main.Utilisateur;

import java.util.Objects;

public class NomUtilisateur {
    private final String nom;

    public NomUtilisateur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NomUtilisateur that = (NomUtilisateur) o;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}