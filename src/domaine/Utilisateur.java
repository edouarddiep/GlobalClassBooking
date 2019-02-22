/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.util.Date;

/**
 *
 * @author Edouard Diep
 */
public class Utilisateur {

    private int no;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String email;
    private String username;
    private String password;
    private Date dateInscr;
    private boolean isAdmin; // le rang peut être "0" (pour l'utilisateur lambda) ou "1" (pour l'accès administrateur)

    public Utilisateur(int no, String nom, String prenom, Date dateNaissance, String email, String username, String password, Date dateInscr, boolean isAdmin) {
        this.no = no;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateInscr = dateInscr;
        this.isAdmin = isAdmin;
    }

    public Utilisateur(String username, String password, Date dateNaissance, Date dateInscr, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.dateInscr = dateInscr;
        this.isAdmin = isAdmin;
    }
    
    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Utilisateur(String username){
        this.username = username;
    }
    

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateInscr(Date dateInscr) {
        this.dateInscr = dateInscr;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateInscr() {
        return dateInscr;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public boolean equals(Object o) {
        return ((Utilisateur)o).username.equals(this.username);
    }

    public String toString() {
        return this.username;
    }
}
