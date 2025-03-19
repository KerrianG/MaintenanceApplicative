package main.Utilisateur;

public class Utilisateur {
    private final NomUtilisateur nomUtilisateur;
    private final MotDePasse motDePasse;

    public Utilisateur(NomUtilisateur nomUtilisateur, MotDePasse motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }

    public NomUtilisateur getNomUtilisateur() {
        return nomUtilisateur;
    }

    public MotDePasse getMotDePasse() {
        return motDePasse;
    }
}