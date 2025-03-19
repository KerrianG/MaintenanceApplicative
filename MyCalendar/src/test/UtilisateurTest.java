package test;

import main.Utilisateur.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurTest {
    private Utilisateurs utilisateurs;

    @BeforeEach
    public void setUp() {
        utilisateurs = new Utilisateurs();
    }

    @Test
    public void testAjouterUtilisateur() {
        NomUtilisateur nom = new NomUtilisateur("testUser");
        MotDePasse motDePasse = new MotDePasse("password123");
        Utilisateur utilisateur = new Utilisateur(nom, motDePasse);

        utilisateurs.ajouterUtilisateur(utilisateur);

        assertTrue(utilisateurs.existeUtilisateur(nom));
    }

    @Test
    public void testTrouverUtilisateur() {
        NomUtilisateur nom = new NomUtilisateur("testUser");
        MotDePasse motDePasse = new MotDePasse("password123");
        Utilisateur utilisateur = new Utilisateur(nom, motDePasse);

        utilisateurs.ajouterUtilisateur(utilisateur);

        Utilisateur foundUser = utilisateurs.trouverUtilisateur(nom, motDePasse);
        assertNotNull(foundUser);
        assertEquals(nom, foundUser.getNomUtilisateur());
        assertEquals(motDePasse, foundUser.getMotDePasse());
    }

    @Test
    public void testUtilisateurInexistant() {
        NomUtilisateur nom = new NomUtilisateur("testUser");
        MotDePasse motDePasse = new MotDePasse("password123");

        Utilisateur foundUser = utilisateurs.trouverUtilisateur(nom, motDePasse);
        assertNull(foundUser);
    }

    @Test
    public void testNomUtilisateurDejaPris() {
        NomUtilisateur nom = new NomUtilisateur("testUser");
        MotDePasse motDePasse1 = new MotDePasse("password123");
        MotDePasse motDePasse2 = new MotDePasse("password456");
        Utilisateur utilisateur1 = new Utilisateur(nom, motDePasse1);
        Utilisateur utilisateur2 = new Utilisateur(nom, motDePasse2);

        utilisateurs.ajouterUtilisateur(utilisateur1);
        assertThrows(IllegalArgumentException.class, () -> utilisateurs.ajouterUtilisateur(utilisateur2));
    }
}