/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smi.entitycouche;

/**
 *
 * @author Joyce-Kabala
 */
public class Promotion {
    
    private int ide;
    private String libellePromo;
    private Division division;
    private Cycle cycle;
    private AnneeScolaire anneescol;
    
    public Promotion(){}

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getLibellePromo() {
        return libellePromo;
    }

    public void setLibellePromo(String libellePromo) {
        this.libellePromo = libellePromo;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public AnneeScolaire getAnneescol() {
        return anneescol;
    }

    public void setAnneescol(AnneeScolaire anneescol) {
        this.anneescol = anneescol;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
    
    
}
