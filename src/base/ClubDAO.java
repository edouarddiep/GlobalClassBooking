/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import domaine.Club;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Edouard Diep
 */
public class ClubDAO {

    /**
     * Récupère et stock dans une ArrayList tous les clubs de la base de
     * données.
     */
    public static ArrayList getClubs() throws SQLException {
        ArrayList lstClubs = new ArrayList();
        String sql = "SELECT * FROM vw_clubs";
        Statement stmt = MyConnection.get().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("Numero");
            String nom = rs.getString("Nom");
            String lieu = rs.getString("Lieu");
            Club c = new Club(id, nom, lieu);
            lstClubs.add(c);
        }
        stmt.close();
        return lstClubs;
    }

    /**
     * Récupère et retourne un club de la base de données défini par son id.
     */
    public static Club getClub(int id) {
        try {
            String sql = "SELECT * FROM vw_clubs WHERE Numero = ?";
            PreparedStatement s = MyConnection.get().prepareStatement(sql);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            rs.next(); // just a single one
            int idClub = rs.getInt("Numero");
            String nom = rs.getString("Nom");
            String lieu = rs.getString("Lieu");
            Club c = new Club(idClub, nom, lieu);
            return c;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
