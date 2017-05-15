package br.uefs.ecomp.jogodamesada.conexao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Gilvanei
 */
public class EmissorMulticast {

    private DatagramSocket socket = null;
    private DatagramPacket pacoteEnviado = null;
    private InetAddress endereco;
    private String IP;
    private int porta;
    
    public EmissorMulticast() {
        try {
            socket = new DatagramSocket();
        }catch(SocketException se){
            System.out.println(se.toString());
        }
    }
    
    public void configurar(String IP, int porta){
        this.IP = IP;
        this.porta = porta;
    }

    public void iniciar(String msg) {
        byte[] saidaBuffer;

        try {

            //while (true) {
                saidaBuffer = msg.getBytes();

                //Enviar para endereço IP multicast e porta
                endereco = InetAddress.getByName(IP);//"224.2.2.3"
                pacoteEnviado = new DatagramPacket(saidaBuffer, saidaBuffer.length, endereco, porta);

                socket.send(pacoteEnviado);
            //}
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
    }
}
