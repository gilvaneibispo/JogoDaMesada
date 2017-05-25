/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author User
 */
public class Servidor {

    private final ServerSocket server;
    private DistribuicaoDePlayers distribuidor;

    public Servidor(int port) throws IOException {
        this.server = new ServerSocket(port);
        this.distribuidor = new DistribuicaoDePlayers();

    }

    public void iniciar() {
        System.out.println("Esperando conexões...");
        while (true) {
            try {
                Socket clientSock = server.accept();
                System.err.println(clientSock.getInetAddress());
                ServerRecebedor r = new ServerRecebedor(clientSock, distribuidor);
                new Thread(r).start(); // mandou pra thread e iniciou ela

            } catch (IOException ex) {
                System.out.println("Erro na comunicação.");
            }
        }
    }

    public static void main(String[] args) {

        try {
            Servidor fs = new Servidor(12345);
            fs.iniciar();

        } catch (IOException ex) {
        }

    }

}
