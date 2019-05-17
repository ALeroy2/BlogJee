package Test;

import Bean.Utilisateur;
import Dao.HSQLdb.UtilisateurDao;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurDaoTest {

    public Utilisateur monUser = new Utilisateur();
    public UtilisateurDao monUserDao = new UtilisateurDao();

    @Test
    void testGetUserNominal() {
        monUser = monUserDao.getUtilisateur("contact@aquasys.fr");
        assertEquals(monUser.getNom(), "Le prof");
        assertEquals(monUser.getPassword(), "Epsi2019");
        assertEquals(monUser.getAdmin(), true);
    }

    @Test
    void testGetUserNull() {
        monUser = monUserDao.getUtilisateur(null);
        assertEquals(monUser.getNom(), null);
        assertEquals(monUser.getPassword(), null);
        assertEquals(monUser.getAdmin(), null);
    }

    @Test
    void testGetUserMauvais() {
        monUser = monUserDao.getUtilisateur("utilisateurInexistant");
        assertEquals(monUser.getNom(), null);
        assertEquals(monUser.getPassword(), null);
        assertEquals(monUser.getAdmin(), null);
    }

    @Test
    void testADDUser() throws SQLException {
        Utilisateur user = new Utilisateur("email", "nom", "passord", new Date(2019, 05, 13), false);
        monUserDao.createUtilisateur(user);

        monUser = monUserDao.getUtilisateur("email");
        assertEquals(monUser.getNom(), "nom");
        assertEquals(monUser.getPassword(), "passord");
        assertEquals(monUser.getAdmin(), false);
    }
}