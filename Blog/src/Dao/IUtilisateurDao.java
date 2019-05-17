package Dao;

import Bean.Utilisateur;

import java.sql.SQLException;

public interface IUtilisateurDao {

    Utilisateur getUtilisateur(String email);
    void createUtilisateur(Utilisateur utilisateur) throws SQLException;
    void updateUtilisateur(Utilisateur utilisateur) throws SQLException;
    void deleteUtilisateur(Utilisateur utilisateur) throws SQLException;

}
