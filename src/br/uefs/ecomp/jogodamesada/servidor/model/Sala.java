/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Sala implements Serializable{

    /**
     * @return the idSala
     */
    public int getIdSala() {
        return idSala;
    }

    private final int totalPlayers;
    private int parcialPlayers;
    private List<Usuario> usuariosSala;
    private InetAddress multicastIP;
    private final static GerenciadorDeIp IP = new GerenciadorDeIp();
    private int periodo;
    private static int numeroSalas;  //atributo da classe que contem o numero total de contas cadastradas
    private int idSala; 
    private int solicitacoes;

    public Sala(Usuario u, int players, int periodo) throws UnknownHostException {
        this.totalPlayers = players;
        this.usuariosSala = new ArrayList<>();
        usuariosSala.add(u);
        parcialPlayers++;
        // multicastIP = IP.getMulticastIP();
        this.periodo = periodo;
        this.idSala = (Sala.getNumeroSalas() + 1);
        Sala.numeroSalas=+1;
    }

    /**
     * @param parcialPlayers the parcialPlayers to set
     */
    public void setParcialPlayers(int parcialPlayers) {
        this.parcialPlayers = parcialPlayers;
    }

    /**
     * @return the players
     */
    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void adicionarPlayer(Usuario u) {
        getUsuariosSala().add(u);
    }

    /**
     * @return the parcialPlayers
     */
    public int getParcialPlayers() {
        return parcialPlayers;
    }

    /**
     * @return the periodo
     */
    public int getPeriodo() {
        return periodo;
    }

    /**
     * @return the usuariosSala
     */
    public List<Usuario> getUsuariosSala() {
        return usuariosSala;
    }

    /**
     * @return the multicastIP
     */
    public InetAddress getMulticastIP() {
        return multicastIP;
    }

    /**
     * @return the numeroSalas
     */
    public static int getNumeroSalas() {
        return numeroSalas;
    }

    /**
     * @param aNumeroSalas the numeroSalas to set
     */
    public static void setNumeroSalas(int aNumeroSalas) {
        numeroSalas = aNumeroSalas;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return getIdSala();
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.idSala = numero;
    }

    /**
     * @return the solicitacoes
     */
    public int getSolicitacoes() {
        return solicitacoes;
    }

    /**
     * @param solicitacoes the solicitacoes to set
     */
    public void setSolicitacoes(int solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

}
