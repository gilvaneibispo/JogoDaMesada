/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.uefs.ecomp.jogodamesada.servidor.protocolos.ProtocoloServidor;

/**
 * <strong>Envia Dados: </strong>
 * Responsável fazer o retorno das informações para o cliente utilizando o TCP. 
 * Envia ao as informações necessárias para estabelecimnto de comunicação
 * multicast entre eles, ao exemplo de IP's multicast, criando a ideia de sala.
 * @author Gilvanei Bispo
 * @author Rodrigo Santos
 * @author Dermeval Neves
 */
class EnviarDados {

    private final static int PORT = 8888;
    private final static int SLEEP_TIME = 50;
    private final static GerenciadorDeIp IP = new GerenciadorDeIp();

    public void startGame(List<Usuario> players, int numPlayers, Sala sala) throws UnknownHostException, SocketException, IOException {
        try {
            InetAddress address = EnviarDados.IP.getMulticastIP();
            StringBuilder data = new StringBuilder();
            data.append(sala.getIdSala()).append(ProtocoloServidor.SEPARATOR).append(sala.getPeriodo()).append(ProtocoloServidor.SEPARATOR);
            int id = 0;
            for (Usuario player : players) {
                System.out.println(player);
                data.append(player.getNome()).append(ProtocoloServidor.SEPARATOR).append(++id).append(ProtocoloServidor.SEPARATOR);
            }
            System.out.println(data);

            System.out.print("Enviando IP de Multicasting...");
            for (Usuario player : players) {
                //Enviar ip do multicast via TCP
                player.getOutput().writeObject(address);
            }
            Thread.sleep(this.SLEEP_TIME);
            String message = data.toString();
            for (Usuario player : players) {
                //Enviar ip do multicast via TCP
                player.getOutput().writeObject(message);
            }

            System.out.println("100%");
        } catch (InterruptedException ex) {
            Logger.getLogger(EnviarDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
