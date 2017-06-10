/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author User
 */
public class DistribuicaoDePlayers {

    private final LinkedList<Sala> salas;
    private final EnviarDados gameInicio = new EnviarDados();

    public DistribuicaoDePlayers() {
        this.salas = new LinkedList<>();
    }

    public synchronized void addPlayer(int numPlayers, int periodo, Usuario usuario) throws UnknownHostException {
        for (int i = 0; i < salas.size(); i++) {
            Sala sala = salas.get(i);
            if (sala.getTotalPlayers() == numPlayers && sala.getPeriodo() == periodo) {
                sala.getUsuariosSala().add(usuario);
                sala.setParcialPlayers(sala.getParcialPlayers() + 1);
                System.err.println(salas.size());
                return;
            }

        }
        Sala s = new Sala(usuario, numPlayers, periodo);
        salas.add(s);
    }

    public Sala testarCapacidadeSalas() throws UnknownHostException, SocketException, IOException {
        for (int i = 0; i < salas.size(); i++) {
            System.out.println(salas.size());
            Sala sala = salas.get(i);
            if (sala.getTotalPlayers() == sala.getParcialPlayers()) {
                Sala s = salas.remove(i);
                this.gameInicio.startGame(s.getUsuariosSala(), sala.getTotalPlayers(), s);
                return s;
            }

        }
        return null;
    }

}
