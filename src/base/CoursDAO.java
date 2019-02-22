/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import domaine.Club;
import domaine.Coach;
import domaine.Cours;
import domaine.Utilisateur;
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
public class CoursDAO {

    /**
     * Récupère et stock dans une ArrayList toutes les données de la BDD liées
     * aux cours.
     */
    public static ArrayList getCours() throws SQLException {
        ArrayList lstCours = new ArrayList();
        String sql = "SELECT * FROM vw_cours ORDER BY Nom, Date_cours";
        Statement stmt = MyConnection.get().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("Numero");
            String nom = rs.getString("Nom");
            int noCoach = rs.getInt("No_Coach");
            String prenomCoach = rs.getString("Prenom_coach");
            String nomCoach = rs.getString("Nom_Coach");
            Coach coach = new Coach(noCoach, prenomCoach, nomCoach); // j'instancie un nouveau coach pour le donné au cours que je stocke
            Date date = rs.getDate("Date_cours");
            String horaire = rs.getString("Horaire");
            int nbParticipants = rs.getInt("nbParticipants");
            int clubNo = rs.getInt("Club");
            Cours c = new Cours(id, nom, coach, date, horaire, nbParticipants, clubNo);
            lstCours.add(c);
        }
        stmt.close();
        return lstCours;
    }

    /**
     * Récupère et stock dans une ArrayList toutes les données de la BDD liées
     * aux cours SELON un club.
     */
    public static ArrayList getCoursSelonClub(int noClub) throws SQLException {
        ArrayList lstCours = new ArrayList();
        String sql = "SELECT * FROM vw_cours WHERE Club = ? ORDER BY Nom, Date_cours";
        PreparedStatement stmt = MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, noClub);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Numero");
            String nom = rs.getString("Nom");
            int noCoach = rs.getInt("No_Coach");
            String prenomCoach = rs.getString("Prenom_coach");
            String nomCoach = rs.getString("Nom_Coach");
            Coach coach = new Coach(noCoach, prenomCoach, nomCoach); // j'instancie un nouveau coach pour le donné au cours que je stocke
            Date date = rs.getDate("Date_cours");
            String horaire = rs.getString("Horaire");
            int nbParticipants = rs.getInt("nbParticipants");
            int clubNo = rs.getInt("Club");
            Cours c = new Cours(id, nom, coach, date, horaire, nbParticipants, clubNo);
            lstCours.add(c);
        }
        stmt.close();
        return lstCours;
    }

    /**
     * Récupère et stock dans une ArrayList toutes les données de la BDD liées
     * aux cours SELON un club ET une date.
     */
    public static ArrayList getCoursSelonClubDate(int noClub, String dCours) throws SQLException {
        ArrayList lstCours = new ArrayList();
        String sql = "SELECT * FROM vw_cours WHERE Club = ? AND Date_cours = ? ORDER BY Nom, Date_cours";
        PreparedStatement stmt = MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, noClub);
        stmt.setString(2, dCours);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Numero");
            String nom = rs.getString("Nom");
            int noCoach = rs.getInt("No_Coach");
            String prenomCoach = rs.getString("Prenom_coach");
            String nomCoach = rs.getString("Nom_Coach");
            Coach coach = new Coach(noCoach, prenomCoach, nomCoach); // j'instancie un nouveau coach pour le donné au cours que je stocke
            Date date = rs.getDate("Date_cours");
            String horaire = rs.getString("Horaire");
            int nbParticipants = rs.getInt("nbParticipants");
            int clubNo = rs.getInt("Club");
            Cours c = new Cours(id, nom, coach, date, horaire, nbParticipants, clubNo);
            lstCours.add(c);
        }
        stmt.close();
        return lstCours;
    }

    /**
     * Met à jour le nombre de participants maximum inscrits au cour.
     */
    public static void updateParticipants(int id, int nb) throws SQLException {
        String sql = "UPDATE vw_cours_only SET NbParticipants = ? WHERE Numero = ?";
        PreparedStatement stmt = MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, nb);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Met à jour les informations du cours passée en paramètre.
     */
    public static void updateCours(Cours c) throws SQLException {
        Coach coach = c.getCoach(); // je récupère le coach lié au cours afin de mettre à jour la table des coachs
        String sql = "UPDATE vw_cours_only SET Nom = ?, Horaire = ? WHERE Numero = ?";
        String sql2 = "UPDATE vw_coachs SET Prénom = ?, Nom = ? WHERE Numero = ?";
        PreparedStatement stmt = MyConnection.get().prepareStatement(sql);
        PreparedStatement stmt2 = MyConnection.get().prepareStatement(sql2);
        stmt.setString(1, c.getNom());
        stmt.setString(2, c.getHoraire());
        stmt.setInt(3, c.getNo());
        stmt2.setString(1, coach.getPrenom());
        stmt2.setString(2, coach.getNom());
        stmt2.setInt(3, coach.getNo());
        stmt.executeUpdate();
        stmt2.executeUpdate();
        stmt.close();
        stmt2.close();
    }

    /**
     * Insère un nouveau cours dans la base de données.
     */
    public static void insert(Cours c) throws SQLException {
        String sql = "INSERT INTO vw_cours_only (Numero, Nom, Date_cours, Horaire, NbParticipants, Club) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = (OraclePreparedStatement) MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, 0);
        stmt.setString(2, c.getNom());
        stmt.setDate(3, new java.sql.Date(c.getDateCours().getTime()));
        stmt.setString(4, c.getHoraire());
        stmt.setInt(5, c.getNbMaxInscrits());
        stmt.setInt(6, c.getNoClub());
        stmt.executeUpdate();
        stmt.close();
    } // insert 

    public static void insertParticipation(int noUser, int noCours) throws SQLException {
        String sql = "INSERT INTO vw_participe (IDUser, IDCours) VALUES (?, ?)";
        PreparedStatement stmt = MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, noUser);
        stmt.setInt(2, noCours);
        stmt.executeUpdate();
        stmt.close();
    } // insert     

    /**
     * Supprime le cours défini par "id" de la base de données.
     */
    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM vw_cours WHERE Numero = ?";
        PreparedStatement stmt = MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

}
