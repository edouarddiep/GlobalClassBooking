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
public class Coach {

    private int no;
    private String nom;
    private String prenom;

    /* CONSTRUCTEURS */    
    public Coach(int no, String nom, String prenom) {
        this.no = no;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public Coach(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }

    /* GETTERS / SETTERS */
    public int getNo() {
        return no;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    /* EQUALS */
    public boolean equals(Object o){
        return ((Coach)o).nom.equals(this.nom) && ((Coach)o).prenom.equals(this.prenom);
    }
    
    /* TOSTRING */
    public String toString(){
        return this.prenom+" "+this.nom;
    }
}
