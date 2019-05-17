package Dao.HSQLdb;

import Bean.Utilisateur;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UtilisateurDao {

    private static final String SQL_INSERT = "INSERT INTO USERS (IS_ADMIN,PASSWORD,DATE_CREATION,NOM,EMAIL) VALUES (?,?,?,?,?)";
    private static final Logger logger = LogManager.getLogger(UtilisateurDao.class);

    public Utilisateur getUtilisateur(String email) {
        email = email == null ? email = "" : email;
        Utilisateur myUser = new Utilisateur();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                myUser = new Utilisateur();
                myUser.setEmail(rs.getString(1));
                myUser.setNom(rs.getString(2));
                myUser.setPassword(rs.getString(4));
                myUser.setAdmin(rs.getBoolean(5));
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            logger.error("Error lors de la creation de l'utilisateur: " + email, e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error lors de la fermetture de la connexion");
            }
        }

        return myUser;
    }

    public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
            PreparedStatement preparedStatement = initialisationRequetePreparee( con, SQL_INSERT, true, utilisateur.getAdmin(), utilisateur.getPassword(), utilisateur.getDateCreation(),utilisateur.getNom(),utilisateur.getEmail());
            int statut = preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            logger.error("Error lors de la creation de l'utilsateur: ", e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error lors de la fermeture de connexion");
            }
        }

    }

    public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
// TODO Auto-generated method stub

    }

    public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
// TODO Auto-generated method stub

    }

    public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }

}
