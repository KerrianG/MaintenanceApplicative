package main.Utilisateur;

import java.util.Objects;

public class MotDePasse {
    private final String motDePasse;

    public MotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotDePasse that = (MotDePasse) o;
        return Objects.equals(motDePasse, that.motDePasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motDePasse);
    }
}