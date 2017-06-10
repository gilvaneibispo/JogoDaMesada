/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author User
 */
public class Usuario implements Serializable, Comparable<Usuario> {

    private String nome;
    private String senha;
    private transient ObjectOutputStream output;
    private InetAddress endereco;
    private double saldoFinal;


    public Usuario(String nome, String senha, ObjectOutputStream output, InetAddress endereco) {
        this.nome = nome;
        this.senha = senha;
        this.output = output;
        this.endereco = endereco;
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

    /**
     * @return the endereco
     */
    public InetAddress getEndereco() {
        return endereco;
    }

    /**
     * @return the saldoFinal
     */
    public double getSaldoFinal() {
        return saldoFinal;
    }

    /**
     * @param saldoFinal the saldoFinal to set
     */
    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    @Override
    public int compareTo(Usuario o) {
            if (this.saldoFinal < o.saldoFinal) {
            return -1;
        }
        if (this.saldoFinal > o.saldoFinal) {
            return 1;
        }
        return 0;
    }
    
}
