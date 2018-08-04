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
public class Section {
    
    private int idSection;
    private String libelleSection;
    private Cycle cycle;
    
    public Section(){}

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public String getLibelleSection() {
        return libelleSection;
    }

    public void setLibelleSection(String libelleSection) {
        this.libelleSection = libelleSection;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
    
    
}
