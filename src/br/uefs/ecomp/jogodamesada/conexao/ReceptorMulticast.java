package br.uefs.ecomp.jogodamesada.conexao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Gilvanei
 */
public class ReceptorMulticast implements Runnable{

    private String msgRecebida;
    MulticastSocket socket;
    DatagramPacket pacoteRecebido;
    InetAddress endereco;
    private int porta;
    private String ip;

    public ReceptorMulticast() {
        msgRecebida = null;

    }

    public void configurar(String IP, int porta) {
        this.porta = porta;
        this.ip = IP;
    }
    
    public void iniciar(){
        byte[] inBuf = new byte[256];
        try {
            //Prepara para participar do grupo multicast
            socket = new MulticastSocket(this.porta);
            endereco = InetAddress.getByName(this.ip); //Ex.: "224.2.2.3".
            socket.joinGroup(endereco);

            while (true) {
                pacoteRecebido = new DatagramPacket(inBuf, inBuf.length);
                socket.receive(pacoteRecebido);
                msgRecebida = new String(inBuf, 0, pacoteRecebido.getLength());
                //pacoteRecebido.getAddress() -> IP de origem;

                System.out.println(msgRecebida);
                System.out.println("rodando...");
            }
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
    }

    public String getMensagem() {
        return msgRecebida;
    }

    @Override
    public void run() {
        this.iniciar();
    }
}
