/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class Usuario implements Serializable {

    private String nome;
    private String senha;
    private transient ObjectOutputStream output;

    public Usuario(String nome, String senha, ObjectOutputStream output) {
        this.nome = nome;
        this.senha = senha;
        this.output = output;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @return the output
     */
    public ObjectOutputStream getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }
}
