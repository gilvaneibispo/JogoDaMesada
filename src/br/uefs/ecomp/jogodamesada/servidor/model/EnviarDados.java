/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.servidor.Model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.uefs.ecomp.jogodamesada.cliente.conexao.ProtocoloP2P;
import br.uefs.ecomp.jogodamesada.servidor.protocolos.ProtocoloServidor;


/**
 *
 * @author User
 */
class EnviarDados {
     private final static int PORT = 8888;
    private final static int SLEEP_TIME = 50;
    private final static GerenciadorDeIp IP = new GerenciadorDeIp();


    public void startGame(List<Usuario> players, int numPlayers, Sala sala) throws  UnknownHostException, SocketException, IOException {
         try {
             InetAddress address = EnviarDados.IP.getMulticastIP();
             StringBuilder data = new StringBuilder();
             data.append(sala.getIdSala()).append(ProtocoloServidor.SEPARATOR).append(sala.getPeriodo()).append(ProtocoloServidor.SEPARATOR);
             int id =0;
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
             
            /* //Preparação do pacote a ser enviado
             byte[] buffer = message.getBytes();
             DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, this.PORT);
             DatagramSocket socket = new DatagramSocket();
             
             socket.send(packet);
*/
             System.out.println("100%");
         } catch (InterruptedException ex) {
             Logger.getLogger(EnviarDados.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
