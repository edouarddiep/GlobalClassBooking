/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.util.ArrayList;

/**
 *
 * @author Edouard Diep
 */
public class Club {
    private int no;
    private String nom;
    private String lieu;

    /* CONSTRUCTEURS */
    public Club(int no, String nom, String lieu) {
        this.no = no;
        this.nom = nom;
        this.lieu = lieu;
    }

    /* GETTERS / SETTERS */
    public int getNo() {
        return no;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    /* EQUALS */
    public boolean equals(Object o){
        return ((Club)o).no == (this.no);
    }

    /* TOSTRING */
    public String toString(){
        return this.nom;
    }
}
