/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

/**
 *
 * @author User
 */
public class Player {
    private String nome;
    private int id;
    
    public Player (String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}
