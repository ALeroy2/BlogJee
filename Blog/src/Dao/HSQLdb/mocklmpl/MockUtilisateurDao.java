package Dao.HSQLdb.mocklmpl;

import Bean.Utilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockUtilisateurDao {

    private static List<Utilisateur> listOfUtilisateurs;

    public Utilisateur getUtilisateur(String email) {
        for (Utilisateur u : getListOfUtilisateur()) {
            if (u.getEmail().equals(email))
                return u;
        }
        return null;
    }

    public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
        getListOfUtilisateur().add(utilisateur);
    }

    public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
        for (Utilisateur u : getListOfUtilisateur()) {
            if (u.getEmail().equals(utilisateur.getEmail())) {
                u.setAdmin(utilisateur.getAdmin());
                u.setNom(utilisateur.getNom());
            }
        }
    }

    public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
        for (Utilisateur u : getListOfUtilisateur()) {
            if (u.getEmail().equals(utilisateur.getEmail())) {
                getListOfUtilisateur().remove(u);
                return;
            }
        }
    }

    private List<Utilisateur> getListOfUtilisateur() {
        if (listOfUtilisateurs == null) {
            listOfUtilisateurs = new ArrayList<Utilisateur>();
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setEmail("alexis.leroy1@epsi.fr");
            utilisateur.setNom("ADMIN");
            utilisateur.setAdmin(true);
            utilisateur.setDateCreation(new java.sql.Date(new Date().getTime()));
            listOfUtilisateurs.add(utilisateur);

            utilisateur = new Utilisateur();
            utilisateur.setEmail("test@aquasys.fr");
            utilisateur.setNom("TEST");
            utilisateur.setAdmin(false);
            utilisateur.setDateCreation(new java.sql.Date(new Date().getTime()));
            listOfUtilisateurs.add(utilisateur);
        }
        return listOfUtilisateurs;
    }

}
