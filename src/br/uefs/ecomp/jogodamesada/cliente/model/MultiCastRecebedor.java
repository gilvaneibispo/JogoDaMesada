/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class MultiCastRecebedor extends Thread {

    private static final int PORT = 8888;
    private final MulticastSocket socket;
    private final ClienteP2P player;

    public MultiCastRecebedor(InetAddress address, ClienteP2P player) throws IOException {
        this.socket = new MulticastSocket(PORT);
        this.socket.joinGroup(address);
        this.player = player;
    }

    @Override
    public void run() {
        byte[] inBuffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(inBuffer, inBuffer.length);
        String message;

        while (true) {
            try {
                this.socket.receive(packet);
                message = new String(inBuffer, 0, packet.getLength());
                System.out.println(message);
                this.player.jogadas(message);

            } catch (IOException ex) {
                
            }
        }

    }
}
