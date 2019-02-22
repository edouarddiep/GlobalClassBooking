/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import domaine.Coach;
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
public class CoachDAO {

    /**
     * Récupère et stock dans une ArrayList tous les coachs de la base de
     * données.
     */
    public static ArrayList getCoachs() throws SQLException {
        ArrayList lstCoachs = new ArrayList();
        String sql = "SELECT * FROM vw_coachs";
        Statement stmt = MyConnection.get().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("Numero");
            String nom = rs.getString("Nom");
            String prenom = rs.getString("Prénom");
            Coach c = new Coach(id, nom, prenom);
            lstCoachs.add(c);
        }
        stmt.close();
        return lstCoachs;
    }

}
