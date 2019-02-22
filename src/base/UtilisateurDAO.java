/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import domaine.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author Edouard Diep
 */
public class UtilisateurDAO {

    public static ArrayList getUsers() throws SQLException {
        ArrayList lstUsers = new ArrayList();
        String sql = "SELECT * FROM vw_user_infos";
        Statement stmt = MyConnection.get().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int no = rs.getInt("Identifiant");
            String nom = rs.getString("Nom");
            String prenom = rs.getString("Prénom");
            Date dateNaissance = rs.getDate("Date_de_naissance");
            String email = rs.getString("Email");
            String username = rs.getString("Username");
            String password = rs.getString("Mot_de_passe");
            Date dateInscr = rs.getDate("Date_inscription");
            Boolean isAdmin = rs.getBoolean("Rang");
            Utilisateur u = new Utilisateur(no, nom, prenom, dateNaissance, email, username, password, dateInscr, isAdmin);
            lstUsers.add(u);
        }
        stmt.close();
        return lstUsers;
    }

    /**
     * Insère un nouvel utilisateur passé en paramètre ("user") avec toutes ses
     * infos dans la base de données.
     */
    public static void insert(Utilisateur user) throws SQLException {
        String sql = "INSERT INTO vw_user_infos VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = (OraclePreparedStatement)MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, 0);
        stmt.setString(2, user.getNom());
        stmt.setString(3, user.getPrenom());
        stmt.setDate(4, new java.sql.Date(user.getDateNaissance().getTime()));
        stmt.setString(5, user.getEmail());
        stmt.setString(6, user.getUsername());
        stmt.setString(7, user.getPassword());
        stmt.setDate(8, new java.sql.Date(user.getDateInscr().getTime()));
        stmt.setBoolean(9, user.getIsAdmin());
        stmt.executeUpdate();
        stmt.close();
    } // insert  

    /**
     * Insère rapidement l'utilisateur "user" dans la base de données.
     * (uniquement les infos id,username, mot de passe et rang)
     */
    public static void quickInsert(Utilisateur user) throws SQLException {
        String sql = "INSERT INTO vw_user_infos_small (Identifiant, Username, Mot_de_passe, Date_inscription, Rang) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = (OraclePreparedStatement)MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, 0);
        stmt.setString(2, user.getUsername());
        stmt.setString(3, user.getPassword());
        stmt.setDate(4, new java.sql.Date(user.getDateInscr().getTime()));
        stmt.setBoolean(5, user.getIsAdmin());
        stmt.executeUpdate();
        stmt.close();
    } // insert  

    /**
     * Met à jour les données d'un utilisateur dans la base de données.
     */
    public static void update(int id, String username, String pw, String nom, String prenom, Date dateNaissance, String email, boolean isAdmin) throws SQLException {
        String sql = "UPDATE vw_user_infos SET Username = ?, Mot_de_passe = ?, Nom = ?, Prénom = ?, Date_de_naissance = ?, Email = ?, Rang = ? WHERE Identifiant = ?";
        PreparedStatement stmt = MyConnection.get().prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, pw);
        stmt.setString(3, nom);
        stmt.setString(4, prenom);
        stmt.setDate(5, new java.sql.Date(dateNaissance.getTime()));
        stmt.setString(6, email);
        stmt.setBoolean(7, isAdmin);
        stmt.setInt(8, id);
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Met à jour le mot de passe d'un utilisateur dans la base de données.
     * (dans le cas où il devrait réinitialiser son mot de passe)
     */
    public static void updatePassword(int id, String password) throws SQLException {
        String sql = "UPDATE vw_user_infos SET Mot_de_passe = ? WHERE Identifiant = ?";
        PreparedStatement stmt = MyConnection.get().prepareStatement(sql);
        stmt.setString(1, password);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
