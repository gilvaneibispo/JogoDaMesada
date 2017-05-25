/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Sala {

    private final int totalPlayers;
    private int parcialPlayers;
    private List<Usuario> usuariosSala;
    private InetAddress multicastIP;
    private final static GerenciadorDeIp IP = new GerenciadorDeIp();
    private int periodo;

    public Sala(Usuario u, int players, int periodo) throws UnknownHostException {
        this.totalPlayers = players;
        this.usuariosSala = new ArrayList<>();
        usuariosSala.add(u);
        parcialPlayers++;
        // multicastIP = IP.getMulticastIP();
        this.periodo = periodo;
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
}
