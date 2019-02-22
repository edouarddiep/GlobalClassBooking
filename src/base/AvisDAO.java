/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import domaine.Avis;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author Edouard Diep
 */
public class AvisDAO {

    /**
     * Insère un nouvel avis dans la base de données.
     */
    public static void insert(Avis a) throws SQLException{
        String sql = "INSERT INTO vw_avis (Numero, Nature, Utilisateur) VALUES (?, ?, ?)";
        PreparedStatement stmt = (OraclePreparedStatement)MyConnection.get().prepareStatement(sql);
        stmt.setInt(1, 1);
        stmt.setString(2, a.getNature());
        stmt.setInt(3, a.getUserNo());
        stmt.executeUpdate();
        stmt.close();
    }
}
