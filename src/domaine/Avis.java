/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

/**
 *
 * @author Edouard Diep
 */
public class Avis {
    private int no;
    private String nature;
    private int userNo;

    /* CONSTRUCTEURS */
    public Avis(int no, String nature, int userNo) {
        this.no = no;
        this.nature = nature;
        this.userNo = userNo;
    }
    
    public Avis(String nature, int userNo) {
        this.nature = nature;
        this.userNo = userNo;
    }

    /* GETTERS / SETTERS */
    public void setNo(int no){
        this.no = no;
    }
    
    public int getNo() {
        return no;
    }

    public String getNature() {
        return nature;
    }

    public int getUserNo() {
        return userNo;
    }

    /* EQUALS */
    public boolean equals(Object o){
        return ((Avis)o).no == this.no;
    }

    /* TOSTRING */
    public String toString(){
        return this.nature;
    }
}
