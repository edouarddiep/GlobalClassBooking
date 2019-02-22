/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Edouard Diep
 */
public class Cours {
    private int no;
    private String nom;
    private Date dateCours;
    private String horaire;
    private int nbMaxInscrits;
    private int noClub;
    private Coach coach;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    /* CONSTRUCTEURS */
    public Cours(int no, String nom, Coach coach, Date dateCours, String horaire, int nbMaxInscrits, int noClub) {
        this.no = no;
        this.nom = nom;
        this.coach = coach;
        this.dateCours = dateCours;
        this.horaire = horaire;
        this.nbMaxInscrits = nbMaxInscrits;
        this.noClub = noClub;
    }

    public Cours(String nom, Date dateCours, String horaire, Coach c, int nbMaxInscrits, int noClub) {
        this.nom = nom;
        this.dateCours = dateCours;
        this.horaire = horaire;
        this.coach = c;
        this.nbMaxInscrits = nbMaxInscrits;
        this.noClub = noClub;
    }
    
    public Cours(int no, String nom, Coach coach, Date dateCours, String horaire){
        this.no = no;
        this.nom = nom;
        this.coach = coach;
        this.dateCours = dateCours;
        this.horaire = horaire;
    }   

    /* GETTERS / SETTERS */

    public int getNo() {
        return no;
    }

    public String getNom() {
        return nom;
    }
    
    public Date getDateCours(){
        return dateCours;
    }

    public String getHoraire() {
        return horaire;
    }

    public int getNbMaxInscrits(){
        return nbMaxInscrits;
    }    
    
    public int getNoClub() {
        return noClub;
    }
    
    public Coach getCoach(){
        return coach;
    }

    /* EQUALS */
    public boolean equals(Object o){
        return ((Cours)o).no == this.no;
    }

    /* TOSTRING */
    public String toString(){
        return this.nom+" donné par " + this.coach +" le "+sdf.format(this.dateCours)+" à "+this.horaire;
    }
    
}
