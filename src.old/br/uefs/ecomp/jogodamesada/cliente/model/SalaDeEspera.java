/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.jogodamesada.cliente.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;


/**
 *
 * @author User
 */
public class SalaDeEspera  implements Runnable{
    private final Cliente client;
    private final ObjectInputStream input;
    
    public SalaDeEspera (Cliente client, ObjectInputStream input){
        this.client = client;
        this.input = input;
    }
            
    @Override
    public void run() {
        try {
            //Espera pelo IP do Multicast
            InetAddress multicastIP = (InetAddress) input.readObject();
            //Envia para o client
            this.client.iniciarMultCast(multicastIP);
        } catch (IOException | ClassNotFoundException ex) {
           
        }
    }
    
}
