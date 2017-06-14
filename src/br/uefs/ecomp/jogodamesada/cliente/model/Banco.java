/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

/**
 *
 * @author Gilvanei
 */
public class Banco {
    private double sorteGrande;
    
    public Banco(){
        this.sorteGrande = 0;
    }

    /**
     * @return the sorteGrande
     */
    public double getSorteGrande() {
        return sorteGrande;
    }

    /**
     * @param sorteGrande the sorteGrande to set
     */
    public void setSorteGrande(double valor) {
        this.sorteGrande = sorteGrande + valor;
    }

    public void resgatarPremio() {
        this.sorteGrande = 0;
    }
}
